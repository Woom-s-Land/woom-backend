package com.ee06.wooms.global.ai.dto;

import java.io.InputStream;

public interface RequestCallback {
    void onComplete(InputStream audioStream);
}
