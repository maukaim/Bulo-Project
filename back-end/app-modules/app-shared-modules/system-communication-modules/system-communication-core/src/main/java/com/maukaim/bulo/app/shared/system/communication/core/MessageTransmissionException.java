package com.maukaim.bulo.app.shared.system.communication.core;

public class MessageTransmissionException extends RuntimeException {
    public MessageTransmissionException(Exception e) {
        super(e);
    }
    public MessageTransmissionException(String msg) {
        super(msg);
    }
}
