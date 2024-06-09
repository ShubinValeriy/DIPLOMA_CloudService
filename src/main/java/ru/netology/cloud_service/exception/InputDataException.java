package ru.netology.cloud_service.exception;

public class InputDataException extends RuntimeException{
    public InputDataException() {
    }

    public InputDataException(String message) {
        super(message);
    }

}
