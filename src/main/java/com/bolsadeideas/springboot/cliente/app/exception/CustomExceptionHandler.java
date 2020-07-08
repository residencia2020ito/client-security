package com.bolsadeideas.springboot.cliente.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.bolsadeideas.springboot.cliente.app.models.Notification;
import com.bolsadeideas.springboot.cliente.app.models.response.ResponseTO;

@ControllerAdvice
public class CustomExceptionHandler {

	@ExceptionHandler(Exception.class)
    public final ResponseEntity<ResponseTO> handleAllExceptions(Exception ex, WebRequest request) {
       
		Notification notification = new Notification();
		ResponseTO response = new ResponseTO();
        
		notification.setCodigo("0000000001");
        notification.setDescripcion("Error no controlado");
        response.addNotification(notification);
        return new ResponseEntity<ResponseTO>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
 
    @ExceptionHandler(NotificationException.class)
    public final ResponseEntity<ResponseTO> handleUserNotFoundException(NotificationException ex,
                                                WebRequest request) {
    	
    	Notification notification = new Notification();
		ResponseTO response = new ResponseTO();
    	
       
        notification.setCodigo(ex.getCode());
        notification.setDescripcion(ex.getMessage());
        response.addNotification(notification);
        return new ResponseEntity<ResponseTO>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        
    }
	
}
