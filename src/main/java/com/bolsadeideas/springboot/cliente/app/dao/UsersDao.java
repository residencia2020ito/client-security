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
	public boolean validateLogin(String u, String p);

	public void registerClient(Cliente c);

	/**
	 * Comprueba si el usuario ya existe
	 * 
	 * @param u Usuario
	 */
	public boolean existUsername(String u);

	
	public void updateClient(String u);
}
