package br.com.adocao.api.exceptions.exceptionhandler;

import br.com.adocao.api.exceptions.EventBadRequestException;
import br.com.adocao.api.exceptions.EventInternalServerErrorException;
import br.com.adocao.api.exceptions.EventNotFoundException;
import br.com.adocao.api.exceptions.EventTimeOutException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExcpetionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EventTimeOutException.class)
    private ResponseEntity<RestErrorMensagem> eventTimeOutHandler(EventTimeOutException exception){
        RestErrorMensagem threatResponse = new RestErrorMensagem(HttpStatus.REQUEST_TIMEOUT, 408,exception.getMessage(), false);
        return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).body(threatResponse);
    }

    @ExceptionHandler(EventInternalServerErrorException.class)
    private ResponseEntity<RestErrorMensagem> eventServiceUnavailableHandler(EventInternalServerErrorException exception){
        RestErrorMensagem threatResponse = new RestErrorMensagem(HttpStatus.INTERNAL_SERVER_ERROR, 503, exception.getMessage(),false);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(threatResponse);
    }

    @ExceptionHandler(EventNotFoundException.class)
    private ResponseEntity<RestErrorMensagem> eventNotFoundHandler(EventNotFoundException exception){
        RestErrorMensagem threatResponse = new RestErrorMensagem(HttpStatus.NOT_FOUND, 404, exception.getMessage(), false );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(threatResponse);
    }

    @ExceptionHandler(EventBadRequestException.class)
    private ResponseEntity<RestErrorMensagem> eventBadRequestHandler(EventBadRequestException exception){
        RestErrorMensagem threatResponse = new RestErrorMensagem(HttpStatus.BAD_REQUEST, 400, exception.getMessage(), false );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(threatResponse);
    }


}
