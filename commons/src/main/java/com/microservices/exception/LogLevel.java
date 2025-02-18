package com.microservices.exception;

public enum LogLevel {
    ERROR("ERROR"),
    WARNING("WARNING"),
    INFO("INFO");

    private final String level;

    LogLevel(String level) {
        this.level = level;
    }

    public String getLevel() {
        return level;
    }
}
