package com.bolsadeideas.springboot.cliente.app.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Document(collection = "shoppingCart")
public class ShoppingCart {

	private String id;
	private String idClient;
	private String idSession;
	private List<ProviderProducts> providerProducts = new ArrayList<ProviderProducts>();
	private Date createCartDate;

	public ShoppingCart() {
	};
}
