package br.com.adocao.api.exceptions;

public class EventInternalServerErrorException extends RuntimeException {

    public EventInternalServerErrorException(){
        super("Erro no servidor.");

    }

    public EventInternalServerErrorException(String message){
        super(message);
    }
}
