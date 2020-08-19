package com.bolsadeideas.springboot.cliente.app.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bolsadeideas.springboot.cliente.app.models.request.RegisterClientTO;
import com.bolsadeideas.springboot.cliente.app.models.response.ResponseTO;
import com.bolsadeideas.springboot.cliente.app.service.ClientServiceImplement;
import com.mx.yoconsumo.commons.session.security.model.Notification;

@RestController
@RequestMapping("/register")
public class ClientController {

	// private static final Logger
	// log=LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private ClientServiceImplement clientServiceImplement;

	@RequestMapping(path = "/newClient", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseTO> registerNewClient(HttpServletRequest httpServletRequest,
			@Valid @RequestBody RegisterClientTO request,
			@RequestHeader(name = "x-header", required = false, defaultValue = "7894") String header) {

		clientServiceImplement.registerClient(request);

		Notification notification = new Notification();
		ResponseTO response = new ResponseTO();

		notification.setCodigo("000000040");
		notification.setDescripcion("Cliente registrado");
		response.addNotification(notification);
		httpServletRequest.getSession();
		HttpHeaders hr = new HttpHeaders();
		hr.set("x-hr", "12345");
		ResponseEntity<ResponseTO> responseEntity = new ResponseEntity<ResponseTO>(response, hr, HttpStatus.CREATED);
		return responseEntity;

	}

}
