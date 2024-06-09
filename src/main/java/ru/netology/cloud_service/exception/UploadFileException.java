package ru.netology.cloud_service.exception;

public class UploadFileException extends RuntimeException {

    public UploadFileException() {
    }

    public UploadFileException(String message) {
        super(message);
    }
}
