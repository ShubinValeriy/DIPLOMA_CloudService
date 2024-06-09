package ru.netology.cloud_service.logger;

public interface Logger {
    void log(LogStatus logStatus, String msg);

    static Logger getInstance() {
        return null;
    }
}
