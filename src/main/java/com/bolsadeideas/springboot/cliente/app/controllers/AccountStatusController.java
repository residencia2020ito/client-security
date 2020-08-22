package com.bolsadeideas.springboot.cliente.app.controllers;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bolsadeideas.springboot.cliente.app.dao.UsersDao;
import com.bolsadeideas.springboot.cliente.app.models.Cliente;
import com.bolsadeideas.springboot.cliente.app.models.request.VerificAccountTO;
import com.bolsadeideas.springboot.cliente.app.service.ClientServiceImplement;
import com.mx.yoconsumo.commons.session.security.annotation.NonValidateSession;
import com.mx.yoconsumo.commons.session.security.model.Notification;
import com.mx.yoconsumo.commons.session.security.response.ResponseTO;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/secutity")
public class AccountStatusController {
	
	@Autowired
	private UsersDao usersDao;
	
	@Autowired
	ClientServiceImplement  clientServiceImplement;

	/**
	 * CAMBIA EL CAMPO DE VERIFICACION(verificEmail) DEL CLIENTE EN LA BD
	 * @param idC ID DEL CLIENTE
	 * @param codV CODIGO DE VERIFICACION
	 * @return RESPUESTA SI CAMBIO EL ESTADO
	 */
	@RequestMapping(path = "/verificationEmail", method = RequestMethod.POST)
	@NonValidateSession
	public ResponseEntity<ResponseTO>  verificationEmail(HttpServletRequest httpServletRequest,
			@Valid @RequestBody VerificAccountTO request){
			
		boolean status =clientServiceImplement.statusAccount(request.getId(), request.getCodVerification());
		
		Notification notification = new Notification();
		ResponseTO response = new ResponseTO();
		if(status) {
			notification.setCodigo("0000000000");
			notification.setDescripcion("Cuenta verificada del cliente con exito");
			response.addNotification(notification);	
		}else {
			notification.setCodigo("0000000000");
			notification.setDescripcion("Error en verificar cuenta del cliente");
			response.addNotification(notification);	
		}

		HttpHeaders hr = new HttpHeaders();
		hr.set("x-hr", "00000");
		ResponseEntity<ResponseTO> responseEntity = new ResponseEntity<ResponseTO>(response, hr, HttpStatus.OK);
		return responseEntity;
	}
	
	/**
	 * REENVIO DE CORREO PARA VERIFICACION DE LA CUENTA
	 * @param httpServletRequest
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/resendVerifEmail", method = RequestMethod.POST)
	@NonValidateSession
	public ResponseEntity<ResponseTO> resendVerifEmail(HttpServletRequest httpServletRequest,
			@Valid @RequestBody VerificAccountTO request) {
		Notification notification = new Notification();
		ResponseTO response = new ResponseTO();
		
		Cliente cliente= clientServiceImplement.resendVerificEmail(request.getEmail());
		if(Objects.nonNull(cliente)) {
			notification.setCodigo("0000000000");
			notification.setDescripcion("Envio de correo para verificar cuenta");
			response.addNotification(notification);	
		}else {
			notification.setCodigo("0000000000");
			notification.setDescripcion("Correo no existente a ningun usuario");
			response.addNotification(notification);	
		}

		HttpHeaders hr = new HttpHeaders();
		hr.set("x-hr", "00000");
		ResponseEntity<ResponseTO> responseEntity = new ResponseEntity<ResponseTO>(response, hr, HttpStatus.OK);
		return responseEntity;
	}
	
	
}
