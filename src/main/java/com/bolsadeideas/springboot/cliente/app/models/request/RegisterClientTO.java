package com.bolsadeideas.springboot.cliente.app.models.request;

import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

@Data
public class RegisterClientTO {

	/*
	 * Minimo 6 caracteres, Maximo 15, Al menos una letra mayúscula, Al menos una letra
	 * minucula, Al menos un dígito
	 */
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{6,15}$", message = "*Minimo 6 caracteres *Maximo 15 *Al menos una letra mayúscula *Al menos una letra minucula *Al menos un dígito")
	private String username;// Usuario
	/*
	 * Minimo 6 caracteres Maximo 15 Al menos una letra mayúscula Al menos una letra
	 * minucula Al menos un dígito
	 */
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{6,15}$", message = "*Minimo 6 caracteres *Maximo 15 *Al menos una letra mayúscula *Al menos una letra minucula *Al menos un dígito")
	private String password;// contraseña
	@Email
	private String email;// correo
	@NotBlank
	@NotEmpty
	private String names;// nombre
	@NotBlank
	@NotEmpty
	private String lastName;// primer apellido
	@NotBlank
	@NotEmpty
	private String secondLastname;// segundo apellido
	@NotBlank
	private String state;// estado
	@NotBlank
	private String city;// ciudad
	@NotBlank
	private String Suburb;// colonia
	@NotBlank
	private String street;// calle
	/*
	 * Solo numeros
	 */
	@NotBlank
	@Pattern(regexp = "^[0-9a-zA-Z]\\S{0,10}$",message = "Solo numeros y letra")
	private String houseNumberI;// numero de casa interno
	/*
	 * Solo numeros
	 */
	@NotBlank
	@Pattern(regexp = "^[0-9a-zA-z]\\S{0,10}$",message = "Solo numeros y letra")
	private String houseNumberE;// numero de casa externo
	/*
	 * Solo numeros
	 */
	@NotBlank
	@Pattern(regexp = "^[0-9]\\S{0,10}$",message = "Solo numeros")
	private String cp;// codigo postal
	/*
	 * Solo numeros
	 */
	@NotBlank
	@Pattern(regexp = "^[0-9]\\S{9}$",message = "Solo numeros")
	private String phoneNumber;// numero de telefono
	
}
