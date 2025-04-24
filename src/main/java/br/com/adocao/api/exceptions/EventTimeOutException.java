package br.com.adocao.api.exceptions;

public class EventTimeOutException extends RuntimeException {

    public EventTimeOutException(){
        super("Timeout");
    }

    public EventTimeOutException(String message){
        super(message);
    }
}
