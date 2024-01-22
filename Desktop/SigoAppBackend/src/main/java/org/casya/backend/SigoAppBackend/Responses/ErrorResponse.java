package org.casya.backend.SigoAppBackend.Responses;

public class ErrorResponse {
    private final int status;
    private final String message;
    private final Object data;

    public ErrorResponse(int status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    // Métodos de obtención...

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }
}

