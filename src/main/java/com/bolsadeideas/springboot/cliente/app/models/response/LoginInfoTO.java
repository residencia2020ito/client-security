package com.bolsadeideas.springboot.cliente.app.models.response;

import java.util.Date;

import lombok.Data;

@Data
public class LoginInfoTO {
	
	private String photo;//Foto
	private String name;//nombre
	private String lastName;//primer apellido
	private String secondLastName;//segundo apellido

}
