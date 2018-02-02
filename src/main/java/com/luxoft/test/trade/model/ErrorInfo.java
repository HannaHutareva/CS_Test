package com.luxoft.test.trade.model;

public class ErrorInfo {
    public final String message;

    public ErrorInfo(Exception message) {
        this.message = message.getLocalizedMessage();
    }
}