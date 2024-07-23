package com.ee06.wooms.global.oauth.exception;

public class NotFoundPlatformException extends RuntimeException {
    public NotFoundPlatformException() {
        super();
    }

    public NotFoundPlatformException(String message) {
        super(message);
    }

    public NotFoundPlatformException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundPlatformException(Throwable cause) {
        super(cause);
    }
}
