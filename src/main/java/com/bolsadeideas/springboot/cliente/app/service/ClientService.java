package com.bolsadeideas.springboot.cliente.app.service;

import com.bolsadeideas.springboot.cliente.app.models.ShoppingCart;

public interface ClientService {

	public String resendVerificEmail(String email);
	
	public String resendModifyPassword(String email);
	
	public void updatePassword(String idC,String codV,String password);
	
	public boolean statusAccount (String idC,String codV);
	
	public void crateShoppingCart(ShoppingCart cart);
	
}
