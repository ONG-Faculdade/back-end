package br.com.adocao.api.exceptions;

public class EventBadRequestException extends RuntimeException {

    public EventBadRequestException(){
        super("Erro na Requisição");
    }

    public EventBadRequestException(String message){
        super(message);
    }
}
