package com.bolsadeideas.springboot.cliente.app.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Products {

	private String id;
	private String idProduct;
	private String nameProduct;
	private String photoProduct;
	private double price;
	private double quantity;
	private String measure;
	
}
