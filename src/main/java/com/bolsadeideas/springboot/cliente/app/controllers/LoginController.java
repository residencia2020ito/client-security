package com.bolsadeideas.springboot.cliente.app.controllers;



import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bolsadeideas.springboot.cliente.app.dao.UsersDao;
<<<<<<< Updated upstream
import com.bolsadeideas.springboot.cliente.app.models.Cliente;
import com.bolsadeideas.springboot.cliente.app.models.Notification;
=======

>>>>>>> Stashed changes
import com.bolsadeideas.springboot.cliente.app.models.request.LoginTO;
import com.bolsadeideas.springboot.cliente.app.models.response.ResponseTO;
import com.mx.yoconsumo.commons.session.security.model.Notification;
import com.mx.yoconsumo.commons.session.security.utils.NotificationUtil;






@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class LoginController {

	private static final Logger log=LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private UsersDao usersDao;
	


	@PostMapping("/login")
	public ResponseTO login(HttpServletRequest httpServletRequest,@Valid @RequestBody LoginTO request){
		
		Cliente cliente=  usersDao.validateLogin(request.getUsername(), request.getPassword());
		Notification notification = new Notification();
		ResponseTO response = new ResponseTO();
		
		if(Objects.nonNull(cliente)) {
			notification.setCodigo("0000000003");
			notification.setDescripcion("Acceso concedido al cliente ");
			HttpSession httpSession = httpServletRequest.getSession();
			response.addNotification(notification);
			httpSession.setAttribute("idClient", cliente.getId());
			log.debug("Cookie ingresada a la session: "+cliente.getId());
			
			return response;
		}
			NotificationUtil.send("0000000004", "Datos incorrectos ingresados en el login");
			return null;
		
	}
	
	@PostMapping("/prueba")
	public String prueba() {
		return "retorno un string";
	}
	
	
}
