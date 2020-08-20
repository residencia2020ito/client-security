package com.bolsadeideas.springboot.cliente.app.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bolsadeideas.springboot.cliente.app.controllers.LoginController;
import com.bolsadeideas.springboot.cliente.app.dao.UsersDao;
import com.bolsadeideas.springboot.cliente.app.models.Cliente;
import com.bolsadeideas.springboot.cliente.app.models.request.RegisterClientTO;
import com.mx.yoconsumo.commons.session.security.utils.NotificationUtil;

import java.util.Date;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
@Service
public class ClientServiceImplement implements ClientService {

	@Autowired
	UsersDao usersDao;

	@Autowired
	private PasswordEncoder passwordEncoder;

	private static final Logger log = LoggerFactory.getLogger(LoginController.class);

	@Override
	public void registerClient(RegisterClientTO r) {

		boolean e = usersDao.existUsername(r.getUsername());
		if (e) {
			NotificationUtil.send("0000000002", "Usuario ya existente en la BD");
		} else {
			Mapper mapper = new DozerBeanMapper();
			Cliente target = mapper.map(r, Cliente.class);
			target.setDate(new Date());
			usersDao.registerClient(target);
		}

	}

	@Override
	public void updateInfoClient(RegisterClientTO r) {

	}

}
