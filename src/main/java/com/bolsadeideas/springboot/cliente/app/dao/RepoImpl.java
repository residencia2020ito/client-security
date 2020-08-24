package com.bolsadeideas.springboot.cliente.app.dao;

import java.util.Date;
import java.util.Objects;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.bolsadeideas.springboot.cliente.app.models.Cliente;

@Repository
public class RepoImpl implements UsersDao {

	private static final Logger log = LoggerFactory.getLogger(RepoImpl.class);

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private PasswordEncoder passwordEncoder;

	/**
	 * Retorna un cliente si los datos recibidos del login son validos
	 */
	@Override
	public Cliente validateLogin(String u, String p) {
		Query query = new Query();
		Criteria criterioBusqueda = Criteria.where("username").is(u);
		query.addCriteria(criterioBusqueda);
		Cliente a = mongoTemplate.findOne(query, Cliente.class);
		if (Objects.nonNull(a)) {
			boolean isPasswordMatch = passwordEncoder.matches(p, a.getPassword());
			if (isPasswordMatch) {
				return a;
			}
		}
		return null;
	}

	/**
	 * Retorna un boolean si el id del cliente y el codigo son validos
	 */
	@Override
	public boolean statusAccountEmail(String idC, String cv) {
		Criteria criterioBusqueda = Criteria.where("id_").is(new ObjectId(idC)).and("codVerification").is(cv);
		Query query = new Query();
		query.addCriteria(criterioBusqueda);
		boolean t = mongoTemplate.exists(query, Cliente.class);
		if (t) {
			Update update = new Update();
			if (Objects.nonNull(idC) && Objects.nonNull(cv)) {
				update.set("verificEmail", true);
				update.set("verficationTime", new Date());
			}
			mongoTemplate.updateFirst(query, update, Cliente.class);
			return t;
		}
		return t;
	}

	/**
	 * Busca datos del cliente por el email recibido
	 * Devuelve:
	 * id_,name,username,email,verificEmail,codVerification,verficationTime,verificEmail
	 */
	@Override
	public Cliente searchClientEmail(String email) {

		Criteria criterioBusqueda = Criteria.where("email").is(email);
		Query query = new Query();
		query.addCriteria(criterioBusqueda);
		query.fields().include("_id");
		query.fields().include("name");
		query.fields().include("username");
		query.fields().include("email");
		query.fields().include("verificEmail");
		query.fields().include("codVerification");
		query.fields().include("verficationTime");
		query.fields().include("verificEmail");
		Cliente e = mongoTemplate.findOne(query, Cliente.class);
		if (Objects.nonNull(e)) {
			return e;
		}
		return null;
	}

	/**
	 * Actualiza la fecha de cuando se envio el ultimo correo de verificacion de cuenta
	 */
	@Override
	public void updateVerficationTime(String idClient) {
		Criteria criterioBusqueda = Criteria.where("_id").is(new ObjectId(idClient));
		Query query = new Query();
		query.addCriteria(criterioBusqueda);
		Update update = new Update();

		update.set("verficationTime", new Date());

		mongoTemplate.updateFirst(query, update, Cliente.class);

	}

	/**
	 * Actualiza la contraseña del cliente por una nueva
	 */
	@Override
	public void resetPassword(String idClient, String codVerifit, String password) {
		Criteria criterioBusqueda = Criteria.where("_id").is(new ObjectId(idClient)).and("codVerification").is(codVerifit);
		Query query = new Query();
		query.addCriteria(criterioBusqueda);
		Update update = new Update();
		
		update.set("password", password);
		
		mongoTemplate.updateFirst(query, update, Cliente.class);
		
	}
	
	

}
