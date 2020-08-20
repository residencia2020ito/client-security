package com.bolsadeideas.springboot.cliente.app.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mx.yoconsumo.commons.session.security.annotation.NonValidateSession;
import com.mx.yoconsumo.commons.session.security.model.Notification;
import com.mx.yoconsumo.commons.session.security.response.ResponseTO;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/security")
public class RecoverPasswordController {
	
	@RequestMapping(value = "/recoverPassword", method = RequestMethod.POST)
	@NonValidateSession
	public ResponseEntity<ResponseTO> recoverPassword(HttpServletRequest httpServletRequest,RecoverPasswordController request){
		Notification notification = new Notification();
		ResponseTO response = new ResponseTO();
		notification.setCodigo("0000000003");
		notification.setDescripcion("Acceso concedido al cliente ");
		response.addNotification(notification);
		
		HttpHeaders hr = new HttpHeaders();
		ResponseEntity<ResponseTO> responseEntity = new ResponseEntity<ResponseTO>(response, hr, HttpStatus.ACCEPTED);
		return responseEntity;
	}

}
