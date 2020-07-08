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
	private String username;//usuario
	private String password;//contraseña
	private String names;//nombre
	private String lastName;//primer apellido
	private String secondLastname;//segundo apellido
	private String state;//estado
	private String city;//ciudad
	private String suburb;//colonia
	private String street;//Calle
	private String houseNumberI;//numero de casa interno
	private String houseNumberE;//numero de casa externo
	private String cp;//codigo postal
	private String numberPhone;//numero de telefono
	private Date date;//Fecha de registro
	private String latitudeLocation;// Ubicacion latitud
	private String lengthLocation;// Ubicacion longitud
	private String descriptionHouse;//Descripcion de casa
	private String firstParStreet;//Primera calle paralela
	private String secondParStreet;//Segunda calle paralela
	
}
