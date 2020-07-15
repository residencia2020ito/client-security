package com.bolsadeideas.springboot.cliente.app.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bolsadeideas.springboot.cliente.app.controllers.LoginController;
import com.bolsadeideas.springboot.cliente.app.dao.UsersDao;

@Service
public class ClientServiceImplement implements ClientService {

	@Autowired
	UsersDao usersDao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	private static final Logger log=LoggerFactory.getLogger(LoginController.class);

	
	
	
	

}
