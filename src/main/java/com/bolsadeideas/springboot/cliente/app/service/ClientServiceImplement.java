package com.bolsadeideas.springboot.cliente.app.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bolsadeideas.springboot.cliente.app.controllers.LoginController;
import com.bolsadeideas.springboot.cliente.app.dao.UsersDao;
<<<<<<< Updated upstream
=======
import com.bolsadeideas.springboot.cliente.app.models.Cliente;
import com.bolsadeideas.springboot.cliente.app.models.request.RegisterClientTO;
import com.mx.yoconsumo.commons.session.security.utils.NotificationUtil;
>>>>>>> Stashed changes

@Service
public class ClientServiceImplement implements ClientService {

	@Autowired
	UsersDao usersDao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	private static final Logger log=LoggerFactory.getLogger(LoginController.class);

	
	
	
	

}
