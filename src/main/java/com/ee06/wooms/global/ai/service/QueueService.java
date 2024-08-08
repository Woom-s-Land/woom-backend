package com.ee06.wooms.global.ai.service;

import com.ee06.wooms.global.ai.dto.RequestCallback;
import com.ee06.wooms.global.ai.dto.TTSRequest;
import com.ee06.wooms.global.ai.exception.ex.FailedSwitchStateException;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.Optional;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
@Service
@RequiredArgsConstructor
public class QueueService {
    private final AIService aiService;

    private final Queue<TTSRequest> requestQueue = new LinkedBlockingQueue<>();
    private static final Integer MAX_CONCURRENT_REQUESTS = 3;
    private static final long PROCESSING_DELAY = 60000; // 처리 후 대기 시간 (60초)
    private static final long POLL_INTERVAL = 3000; // 작업 큐 체크 주기 (3초)

    private final AtomicInteger currentConcurrentRequests = new AtomicInteger(0);
    private final Lock lock = new ReentrantLock(); // 큐 접근을 위한 Lock
    private final Condition queueNotEmpty = lock.newCondition(); // 큐가 비어있는지 확인

    @PostConstruct
    public void init() {
        new Thread(this::processQueue).start();
    }

    /**
     * 요청을 큐에 추가하고, 작업이 있음을 알림
     *
     * @param script   처리할 스크립트
     * @param callback 요청 완료 후 호출될 콜백
     */
    public void enqueueRequest(String script, RequestCallback callback) {
        requestQueue.add(new TTSRequest(script, callback));
        queueNotEmpty.signal();
    }

    /**
     * TTS 변환 작업 쓰레드 시작 메서드
     */
    private void processQueue() {
        while (true) {
            lock.lock();
            try {
                checkThreadIsNoneProcessing(); // 큐에 요청이 있을 때까지 대기
                Optional.ofNullable(requestQueue.poll()).ifPresent(request -> {
                    currentConcurrentRequests.incrementAndGet();
                    new Thread(() -> {
                        convertProcess(request);
                        afterConvertProcessing();
                    }).start();
                });
            } finally {
                lock.unlock();
            }
        }
    }

    /**
     * 주기적으로 큐에 남은 작업이 있는지 확인하는 메서드
     * 작업이 남아있다면, 쓰레드 활성(signal)
     */
    @Scheduled(fixedRate = POLL_INTERVAL)
    private void periodicQueueCheck() {
        lock.lock();
        if (!requestQueue.isEmpty()) queueNotEmpty.signal();
        lock.unlock();
    }

    // ============================= 추출 메서드 시작 ============================= //

    /**
     * TTS 변환 메서드
     * @param request 처리할 TTS 요청
     */
    private void convertProcess(TTSRequest request) {
        InputStream audioStream = aiService.convertMP3File(request.getScript());
        request.getCallback().onComplete(audioStream);
    }

    /**
     * 변환 완료 후 실행되는 메서드
     */
    private void afterConvertProcessing() {
        try {
            Thread.sleep(PROCESSING_DELAY); // 처리 후 대기 시간 부여
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new FailedSwitchStateException();
        } finally {
            currentConcurrentRequests.decrementAndGet(); // 동시 처리 요청 수 감소
        }
    }

    /**
     * 큐에 요청이 없거나 이미 최대 동시 작업 중이라면 쓰레드 대기(await)
     */
    private void checkThreadIsNoneProcessing() {
        while (requestQueue.isEmpty() || currentConcurrentRequests.get() >= MAX_CONCURRENT_REQUESTS) {
            try {
                queueNotEmpty.await(); // 큐에 요청이 추가될 때까지 대기
            } catch (InterruptedException e) {
                throw new FailedSwitchStateException();
            }
        }
    }
}
