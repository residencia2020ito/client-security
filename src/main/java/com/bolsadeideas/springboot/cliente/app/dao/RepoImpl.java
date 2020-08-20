package com.bolsadeideas.springboot.cliente.app.dao;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import com.bolsadeideas.springboot.cliente.app.models.Cliente;


@Repository
public class RepoImpl implements UsersDao {

	private static final Logger log=LoggerFactory.getLogger(RepoImpl.class);
	
	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public Cliente validateLogin(String u, String p) {
		Query query = new Query();
		Criteria criterioBusqueda = Criteria.where("username").is(u);
		query.addCriteria(criterioBusqueda);
		Cliente a = mongoTemplate.findOne(query, Cliente.class);
		if (Objects.nonNull(a)) {
			boolean isPasswordMatch = passwordEncoder.matches(p, a.getPassword());
//			log.debug("ID cliente: " + a.getId());
//			log.debug("Password : " + p + "   isPasswordMatch    : " + isPasswordMatch);
			if(isPasswordMatch) {
				return a;
			} 
		}
		
		return null;

	}

	@Override
	public void registerClient(Cliente c) {
		mongoTemplate.insert(c);
		
	}

	@Override
	public boolean existUsername(String u) {
		 Query query=new Query();
			Criteria criterioBusqueda= Criteria.where("username").is(u);
			query.addCriteria(criterioBusqueda);
			return mongoTemplate.exists(query, Cliente.class);
		
	}

	@Override
	public void updateClient(String u) {
		Query query=new Query();
		Criteria criterioBusqueda= Criteria.where("username").is(u);
		query.addCriteria(criterioBusqueda);
		mongoTemplate.findOne(query, Cliente.class);
	}

	

}
