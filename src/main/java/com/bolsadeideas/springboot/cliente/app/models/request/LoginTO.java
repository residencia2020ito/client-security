package com.bolsadeideas.springboot.cliente.app.models.request;



import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;


@Data
public class LoginTO {


	//@NotBlank(message = "Campo username vacio")
	private String username;
	//@NotBlank(message = "Campo password vacio")
	private String password;
	
}
