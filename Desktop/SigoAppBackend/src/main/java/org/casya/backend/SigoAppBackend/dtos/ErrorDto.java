package org.casya.backend.SigoAppBackend.dtos;


public class ErrorDto {
    private String errorCode;
    private String errorMessage;

    // Constructor
    public ErrorDto(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    // Getters y setters (puedes generarlos automáticamente en tu IDE)
    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
