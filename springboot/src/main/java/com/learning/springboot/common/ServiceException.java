package com.learning.springboot.common;

public class ServiceException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    private String message;

    public String getMessage(){ return message;}

    public void setMessage(String message){this.message = message;}

    public ServiceException(final String message,Throwable th){
        super(message,th);
        this.message = message;
    }

    public ServiceException(final String message){
        this.message = message;
    }

    public static void throwEx(String message){
        throw new ServiceException(message);
    }

}
