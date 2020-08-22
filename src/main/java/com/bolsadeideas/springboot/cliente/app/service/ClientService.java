package com.bolsadeideas.springboot.cliente.app.service;

import com.bolsadeideas.springboot.cliente.app.models.Cliente;

public interface ClientService {

	public Cliente resendVerificEmail(String email);
	
	public Cliente resendModifyPassword(String email);
	
	public void updatePassword(String idC,String codV,String password);
	
	public boolean statusAccount (String idC,String codV);
	
}
