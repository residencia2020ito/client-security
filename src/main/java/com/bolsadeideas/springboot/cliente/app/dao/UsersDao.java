package com.bolsadeideas.springboot.cliente.app.dao;

import com.bolsadeideas.springboot.cliente.app.models.Cliente;

public interface UsersDao {

	/**
	 * Valida si el cliente existe en la BD
	 * 
	 * @param u Usuario
	 * @param p Contraseña
	 * @return
	 */
	public Cliente validateLogin(String u, String p);

	
	
}
