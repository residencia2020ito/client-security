package com.bolsadeideas.springboot.cliente.app.controllers;

import java.util.Arrays;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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

import com.bolsadeideas.springboot.cliente.app.dao.UsersDao;
import com.bolsadeideas.springboot.cliente.app.models.Cliente;
import com.bolsadeideas.springboot.cliente.app.models.request.LoginTO;
import com.bolsadeideas.springboot.cliente.app.models.response.LoginInfoTO;
import com.bolsadeideas.springboot.cliente.app.service.ClientServiceImplement;
import com.mx.yoconsumo.commons.session.security.annotation.NonValidateSession;
import com.mx.yoconsumo.commons.session.security.model.Notification;
import com.mx.yoconsumo.commons.session.security.model.PrincipalUser;
import com.mx.yoconsumo.commons.session.security.response.ResponseTO;
import com.mx.yoconsumo.commons.session.security.utils.NotificationUtil;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/security")
public class LoginController {

	private static final Logger log = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private UsersDao usersDao;
	
	@Autowired
	ClientServiceImplement  clientServiceImplement;

	/**
	 * ACCESO A LA CUENTA DEL CLIENTE
	 * 
	 * @param httpServletRequest
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/loginClient", method = RequestMethod.POST)
	@NonValidateSession
	public ResponseEntity<ResponseTO> login(HttpServletRequest httpServletRequest,
			@Valid @RequestBody LoginTO request) {

		Cliente cliente = usersDao.validateLogin(request.getUsername(), request.getPassword());
		Notification notification = new Notification();
		ResponseTO response = new ResponseTO();

		if (Objects.nonNull(cliente)) {

			if (cliente.isVerificEmail()) {
				LoginInfoTO loginInfoTO = new LoginInfoTO();
				loginInfoTO.setPhoto(cliente.getPhoto());
				loginInfoTO.setName(cliente.getName());
				loginInfoTO.setLastName(cliente.getLastName());
				loginInfoTO.setSecondLastName(cliente.getSecondLastName());

				notification.setCodigo("0000000003");
				notification.setDescripcion("Acceso concedido al cliente ");
				HttpSession httpSession = httpServletRequest.getSession();
				response.addNotification(notification);
				response.setData(loginInfoTO);

				PrincipalUser user = new PrincipalUser();
				user.setId(cliente.getId());
				user.setName(cliente.getName());
				user.setLastName(cliente.getLastName());
				user.setMiddleName(cliente.getSecondLastName());
				user.setRoles(Arrays.asList("CLIENTE"));
				httpSession.setAttribute(PrincipalUser.ATTRIBUTE_SESSION_NAME, user);
				log.debug("ID cliente ingresado a la session: " + cliente.getId());

			} else {
				notification.setCodigo("0000000000");
				notification.setDescripcion("Acceso denegado, verificar cuenta de correo");
				response.addNotification(notification);
			}

			HttpHeaders hr = new HttpHeaders();
			ResponseEntity<ResponseTO> responseEntity = new ResponseEntity<ResponseTO>(response, hr,
					HttpStatus.ACCEPTED);
			return responseEntity;
		}
		NotificationUtil.send("0000000004", "Datos incorrectos ingresados en el login");
		return null;

	}


	/**
	 * CIERRA SESION DE LA CUENTA ACTIVA
	 * 
	 * @param httpServletRequest
	 * @return
	 */
	@RequestMapping(value = "/cerrarSession", method = RequestMethod.GET)
	public ResponseTO CerrarSession(HttpServletRequest httpServletRequest) {
		httpServletRequest.getSession().invalidate();
		Notification notification = new Notification();
		ResponseTO response = new ResponseTO();
		notification.setCodigo("0000000000");
		notification.setDescripcion("Sesión cerrada correctamente cliente");
		response.addNotification(notification);
		return response;
	}

}
