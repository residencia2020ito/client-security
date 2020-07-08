package com.bolsadeideas.springboot.cliente.app.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.bolsadeideas.springboot.cliente.app.models.Cliente;

@Repository
public class RepoImpl implements UsersDao{

	 @Autowired
	private MongoTemplate mongoTemplate;
	 
	 @Override
	 public boolean validateLogin(String u,String p) {
		 Query query=new Query();
		Criteria criterioBusqueda= Criteria.where("username").is(u).and("password").is(p);
		query.addCriteria(criterioBusqueda);
		return mongoTemplate.exists(query, Cliente.class);
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
