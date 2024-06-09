package ru.netology.cloud_service.exception;

public class DeleteFileException extends RuntimeException{

    public DeleteFileException() {
    }

    public DeleteFileException(String message) {
        super(message);
    }
}
