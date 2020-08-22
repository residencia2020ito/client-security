package com.bolsadeideas.springboot.cliente.app.service;

import java.util.Date;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bolsadeideas.springboot.cliente.app.dao.UsersDao;
import com.bolsadeideas.springboot.cliente.app.models.Cliente;

@Service
public class ClientServiceImplement implements ClientService {

	@Autowired
	UsersDao usersDao;

	@Autowired
	private PasswordEncoder passwordEncoder;

	private static final Logger log = LoggerFactory.getLogger(ClientServiceImplement.class);

	/**
	 * Recibe un email para buscar al cliente por el y retornar su informacion
	 */
	@Override
	public Cliente resendVerificEmail(String email) {

		Cliente cliente = usersDao.searchClientEmail(email);

		if (Objects.nonNull(cliente)) {
			Date d = cliente.getVerficationTime();
			Date h = new Date();
			long diferenciaEn_ms =  h.getTime() - d.getTime();
			log.info(String.valueOf("Tiempo transcurrido en ms: " + diferenciaEn_ms));

			if (diferenciaEn_ms > 300000) {
				log.info("Se envia el correo por que ya an pasado 5 minutos");
				usersDao.updateVerficationTime(cliente.getId());
				// se reenvia el correo al cliente
				return cliente;

			}
			log.info("No se envia el correo por que no an pasado 5 minutos");
			return cliente;
		}

		return null;
	}

	/**
	 * Recibe el id del cliente y el codigo de verificacion para cambiar el estado
	 * de la cuenta
	 */
	@Override
	public boolean statusAccount(String idC, String codV) {

		boolean t = usersDao.statusAccountEmail(idC, codV);
		if (t) {
			return t;
		}

		return t;
	}

	/**
	 * Envia un correo de restablecer contraseña solo si la cuenta esta verificada
	 */
	@Override
	public Cliente resendModifyPassword(String email) {
		
		Cliente cliente = usersDao.searchClientEmail(email);

		if (Objects.nonNull(cliente)){
			
			if(cliente.isVerificEmail()){
				//si esta verificada la cuenta se envia el correo para cambiar contraseña
				return cliente;
			}
		}
		return null;
	}

	/**
	 * Modifica la contraseña del cliente validando el id con el codigo de verificacion e inserta la nueva contraseña incriptada
	 */
	@Override
	public void updatePassword(String idC, String codV,String password) {
		
		usersDao.resetPassword(idC, codV,passwordEncoder.encode(password));
		
	}

}
