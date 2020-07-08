package com.bolsadeideas.springboot.cliente.app.controllers;



import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bolsadeideas.springboot.cliente.app.dao.UsersDao;
import com.bolsadeideas.springboot.cliente.app.models.Notification;
import com.bolsadeideas.springboot.cliente.app.models.request.LoginTO;
import com.bolsadeideas.springboot.cliente.app.models.response.ResponseTO;
import com.bolsadeideas.springboot.cliente.app.utils.NotificationUtil;





@RestController
@RequestMapping("/api")
public class LoginController {

	private static final Logger log=LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private UsersDao usersDao;
	

	@PostMapping("/login")
	public ResponseTO login(HttpServletRequest httpServletRequest,@Valid @RequestBody LoginTO request){
		
		boolean exist = usersDao.validateLogin(request.getUsername(), request.getPassword());
		Notification notification = new Notification();
		ResponseTO response = new ResponseTO();
		
		if(exist) {
			notification.setCodigo("0000000003");
			notification.setDescripcion("Acceso concedido al cliente ");
			
			response.addNotification(notification);
			httpServletRequest.getSession();
			log.debug(httpServletRequest.getSession().getId());
			
			return response;
		}else {
			NotificationUtil.send("0000000004", "Datos incorrectos ingresados en el login");
			return null;
		}
		
		
		
	}
	
	
}
