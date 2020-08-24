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


	/**
	 * Cambia el estado de la cuenta del cliente si su correo es verificado
	 * @param c ID cliente
	 * @param cv Codigo de verificacion
	 */
	public boolean statusAccountEmail(String idC,String cv);
	
	/**
	 * Reenvio de correo verficacion al cliente
	 * Devuelve:
	 * id_,name,username,email,verificEmail,codVerification,verficationTime,verificEmail
	 * @param email
	 * @return
	 */
	public Cliente searchClientEmail(String email);
	
	/**
	 * Actualiza la fecha en el reenvio de correo de verificacion
	 * @param idClient
	 */
	public void updateVerficationTime(String idClient);
	
	/**
	 * Actualiza la contraseña con una nueva
	 */
	public void resetPassword(String idClient,String codVerifit,String password);
}
