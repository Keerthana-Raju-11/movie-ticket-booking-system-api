package com.example.mdb.exception;

import ch.qos.logback.classic.spi.IThrowableProxy;

public class SomeException extends Exception {

    public SomeException(){
        super();
    }

    public SomeException(String message){
        super(message);
    }

    public SomeException(String message , Throwable cause){
        super(message , cause);
    }

    public SomeException(Throwable cause){
        super(cause);
    }
}
