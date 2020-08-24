package com.bolsadeideas.springboot.cliente.app.service;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bolsadeideas.springboot.cliente.app.dao.UsersDao;
import com.bolsadeideas.springboot.cliente.app.models.Cliente;
import com.bolsadeideas.springboot.cliente.app.models.SendEmail;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mx.yoconsumo.commons.session.security.model.Notification;
import com.mx.yoconsumo.commons.session.security.utils.NotificationUtil;

@Service
public class ClientServiceImplement implements ClientService {

	@Autowired
	UsersDao usersDao;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Value(value = "${timesendemail.value}")
	private long time;

	@Autowired
	private RabbitTemplate rabbitTemplate;

	private static final Logger log = LoggerFactory.getLogger(ClientServiceImplement.class);

	/**
	 * @throws IOException @throws JsonProcessingException @throws IOException
	 *                     Recibe un email para buscar al cliente por el,retornar su
	 *                     informacion y se envia el email @throws
	 */
	@Override
	public String resendVerificEmail(String email) {

		Cliente cliente = usersDao.searchClientEmail(email);

		if (Objects.nonNull(cliente)) {
			Date d = cliente.getVerficationTime();
			Date h = new Date();
			long diferenciaEn_ms = h.getTime() - d.getTime();
			log.info(String.valueOf("Tiempo transcurrido en ms: " + diferenciaEn_ms));

			if (diferenciaEn_ms > time) {
				usersDao.updateVerficationTime(cliente.getId());
				ObjectMapper om = new ObjectMapper();
				SendEmail emailverifit = new SendEmail();
				emailverifit.setIdPlantilla("verificacionCorreo");
				emailverifit.setPara(cliente.getEmail());
				emailverifit.setAsunto("Confirmacion de correo electronico");
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("name", cliente.getName());
				map.put("username", cliente.getUsername());
				map.put("liga", "http://localhost:8080/secutity/verificationEmail/?id=" + cliente.getId()
						+ "&codVerification=" + cliente.getCodVerification());
				emailverifit.setMap(map);

				String t = "";
				try {
					t = om.writeValueAsString(emailverifit);
				} catch (JsonProcessingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				rabbitTemplate.convertAndSend("notification", "email", t);
				return cliente.getEmail();

			}

			NotificationUtil.send("00109",
					"El correo de verificacion ya fue enviado, esperar 5 minutos para intentarlo de nuevo");
			return null;
		}
		NotificationUtil.send("00110", "Correo no registrado en la plataforma para enviar correo de verificacion");
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
	public String resendModifyPassword(String email) {

		Cliente cliente = usersDao.searchClientEmail(email);

		if (Objects.nonNull(cliente)) {

			if (cliente.isVerificEmail()) {
				ObjectMapper om = new ObjectMapper();
				SendEmail emailModifyPass = new SendEmail();
				emailModifyPass.setIdPlantilla("modificarPassword");
				emailModifyPass.setPara(cliente.getEmail());
				emailModifyPass.setAsunto("Restablecer contraseña");
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("name", cliente.getName());
				map.put("username", cliente.getUsername());
				map.put("liga", "http://localhost:8080/secutity/updatePassword/?id=" + cliente.getId()
						+ "&codVerification=" + cliente.getCodVerification());
				emailModifyPass.setMap(map);

				String t = "";
				try {
					t = om.writeValueAsString(emailModifyPass);
				} catch (JsonProcessingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				rabbitTemplate.convertAndSend("notification", "email", t);
				return cliente.getEmail();
			}
			NotificationUtil.send("00111", "Correo no verificado, verificar correo para cambiar contraseña");
			return null;
		}
		NotificationUtil.send("00112", "Correo no registrado en la plataforma para enviar correo de restablecer contraseña");
		return null;
	}

	/**
	 * Modifica la contraseña del cliente validando el id con el codigo de
	 * verificacion e inserta la nueva contraseña incriptada
	 */
	@Override
	public void updatePassword(String idC, String codV, String password) {

		usersDao.resetPassword(idC, codV, passwordEncoder.encode(password));

	}

}
