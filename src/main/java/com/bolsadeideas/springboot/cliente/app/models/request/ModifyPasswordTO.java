package com.bolsadeideas.springboot.cliente.app.models.request;

import lombok.Data;

@Data
public class ModifyPasswordTO {

	private String id;
	private String password;
	private String codVerification;
	
	
}
