package com.bolsadeideas.springboot.cliente.app.service;

import com.bolsadeideas.springboot.cliente.app.models.Cliente;
import com.mx.yoconsumo.commons.session.security.model.Notification;

public interface ClientService {

	public String resendVerificEmail(String email);
	
	public String resendModifyPassword(String email);
	
	public void updatePassword(String idC,String codV,String password);
	
	public boolean statusAccount (String idC,String codV);
	
}
