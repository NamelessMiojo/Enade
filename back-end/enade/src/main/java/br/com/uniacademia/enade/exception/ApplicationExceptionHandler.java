package br.com.uniacademia.enade.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import br.com.uniacademia.enade.to.ResponseTO;

@ControllerAdvice
public class ApplicationExceptionHandler {
	
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<?> handlerException(MethodArgumentTypeMismatchException e){
		e.printStackTrace();
		ResponseTO response = new ResponseTO(2,400,"Parâmetro enviado incorretamente");
		return ResponseEntity.status(response.getHttpStatus()).body(response);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handlerException(Exception e){
		e.printStackTrace();
		ResponseTO response = new ResponseTO(2,500,"Erro ao processar sua requisição");
		return ResponseEntity.status(response.getHttpStatus()).body(response);
	}
}
