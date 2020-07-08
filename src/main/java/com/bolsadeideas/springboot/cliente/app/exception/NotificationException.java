package com.bolsadeideas.springboot.cliente.app.exception;

import lombok.Data;

@Data
public class NotificationException extends RuntimeException {

	private static final long serialVersionUID = 8952408314203805402L;
	private String code;
	private String message;
}
