package com.bolsadeideas.springboot.cliente.app.models;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Document(collection = "client")
public class Cliente implements Serializable{
	
	private static final long serialVersionUID = 6935996654610189071L;
	public Cliente() {}

	@Id
	private String id;
	private String photo;//Foto
	private String username;//usuario
	private String password;//contraseña
	private String email;//email
	private String name;//nombre
	private String lastName;//primer apellido
	private String secondLastName;//segundo apellido
	private String cp;//codigo postal
	private String state;//estado
	private String city;//ciudad
	private String suburb;//colonia
	private String street;//Calle
	private String houseNumberI;//numero de casa interno
	private String houseNumberE;//numero de casa externo
	private String numberPhone;//numero de telefono
	private String numberPhoneLocal;//numero de telefono local
	private Date date;//Fecha de registro
	private String descriptionHouse;//Descripcion de casa
	private String firstParStreet;//Primera calle paralela
	private String secondParStreet;//Segunda calle paralela
	private String idDevice;//ID firebase
	
}
