package com.bolsadeideas.springboot.cliente.app.service;

import com.bolsadeideas.springboot.cliente.app.models.request.RegisterClientTO;

public interface ClientService {

    public void registerClient(RegisterClientTO r);
	
	
	
	public void updateInfoClient(RegisterClientTO r);
	
	
}
