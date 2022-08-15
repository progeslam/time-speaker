package com.smartbear.timespeaker.exception;


public class ServiceUnprocessableException extends RuntimeException {


    public ServiceUnprocessableException(final String message) {

        super(message);
    }

    public ServiceUnprocessableException(final Throwable cause) {

        super(cause);
    }

}
