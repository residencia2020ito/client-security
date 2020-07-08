package com.bolsadeideas.springboot.cliente.app.utils;

import com.bolsadeideas.springboot.cliente.app.exception.NotificationException;


public final class NotificationUtil {
	public static void send(String code, String message) {
		NotificationException n = new NotificationException();
		n.setCode(code);
		n.setMessage(message);
		throw n;
	}
}
