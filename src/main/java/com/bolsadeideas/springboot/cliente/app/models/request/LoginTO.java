package com.bolsadeideas.springboot.cliente.app.models.request;



import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;


@Data
public class LoginTO {


	@NotBlank
	private String username;
	@NotBlank
	private String password;
	
}
