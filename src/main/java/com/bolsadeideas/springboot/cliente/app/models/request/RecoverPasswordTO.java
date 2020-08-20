package com.bolsadeideas.springboot.cliente.app.models.request;

import lombok.Data;

@Data
public class RecoverPasswordTO {

	/**
	 * Correo del cliente
	 */
	private String email;
	
}
