package com.bolsadeideas.springboot.cliente.app.models.request;

import lombok.Data;

@Data
public class VerificAccountTO {

	private String id;
	private String email;
	private String codVerification;
	private String verficationTime;
	private boolean verificEmail;
	
}
