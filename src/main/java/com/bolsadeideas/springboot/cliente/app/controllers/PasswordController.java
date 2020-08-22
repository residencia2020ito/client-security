package com.bolsadeideas.springboot.cliente.app.controllers;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bolsadeideas.springboot.cliente.app.models.Cliente;
import com.bolsadeideas.springboot.cliente.app.models.request.ModifyPasswordTO;
import com.bolsadeideas.springboot.cliente.app.models.request.VerificAccountTO;
import com.bolsadeideas.springboot.cliente.app.service.ClientServiceImplement;
import com.mx.yoconsumo.commons.session.security.annotation.NonValidateSession;
import com.mx.yoconsumo.commons.session.security.model.Notification;
import com.mx.yoconsumo.commons.session.security.response.ResponseTO;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/security")
public class PasswordController {
	
	private static final Logger log = LoggerFactory.getLogger(PasswordController.class);

	@Autowired
	ClientServiceImplement clientServiceImplement;

	/**
	 * ENVIO DE CORREO PARA RECUPERAR CONTRASEÑA OLVIDADA DEL CLIENTE
	 * 
	 * @param httpServletRequest
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/sendingEmailPassword", method = RequestMethod.POST)
	@NonValidateSession
	public ResponseEntity<ResponseTO> recoverPassword(HttpServletRequest httpServletRequest,@RequestBody VerificAccountTO request) {

		Notification notification = new Notification();
		ResponseTO response = new ResponseTO();
		Cliente cliente = clientServiceImplement.resendModifyPassword(request.getEmail());
		
		if (Objects.nonNull(cliente)) {

			notification.setCodigo("00000");
			notification.setDescripcion("correo enviado para restablecer contraseña");
			response.addNotification(notification);
		} else {
			notification.setCodigo("00000");
			notification.setDescripcion("Cuenta no verficada o no existe el correo registrado");
			response.addNotification(notification);
		}

		HttpHeaders hr = new HttpHeaders();
		ResponseEntity<ResponseTO> responseEntity = new ResponseEntity<ResponseTO>(response, hr, HttpStatus.ACCEPTED);
		return responseEntity;
	}
	
	/**
	 * ACTUALIZA LA CONTRASEÑA DEL CLIENTE EN LA BD
	 * 
	 * @param httpServletRequest
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
	@NonValidateSession
	public ResponseEntity<ResponseTO> updatePassword(HttpServletRequest httpServletRequest, @RequestBody ModifyPasswordTO request) {

		log.info(request.getId());
		log.info(request.getCodVerification());
		log.info(request.getPassword());
		
		clientServiceImplement.updatePassword(request.getId(),request.getCodVerification(),request.getPassword());
		Notification notification = new Notification();
		ResponseTO response = new ResponseTO();
		
			notification.setCodigo("00200");
			notification.setDescripcion("Contraseña actualizada del cliente");
			response.addNotification(notification);
		
		HttpHeaders hr = new HttpHeaders();
		ResponseEntity<ResponseTO> responseEntity = new ResponseEntity<ResponseTO>(response, hr, HttpStatus.ACCEPTED);
		return responseEntity;
	}
	

}
