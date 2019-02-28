package Main.Util;

import javax.mail.*;
import javax.mail.internet.*;

import org.apache.log4j.Level;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileInputStream;
import java.util.Properties;

//Protocolo de mail 
/*
 * Descargar la libreria javax.mail, soltarla en la carpeta WEB-INF
 * agragar package javax.mail y javax.mail.internet
 * Crear la clase como singleton.
 * Ejemplo 11 del 2017, para email y archivos de propiedades
 */

public class Email {
	public static Email instance;
	private Properties props;
	
	public static Email getInstance() throws LogException {
		if(instance == null) {
			try {
				instance = new Email();
			}
			catch(IOException exIO) {
				return null;
			}
		}
		return instance;
	}
	
	private Email() throws LogException, FileNotFoundException {
		try {
			FileInputStream inputStream = new FileInputStream("C:\\Users\\Tomas\\git\\TPIJavaUTN2018\\ProyectoWev\\Resource\\app.properties");
			
			props = new Properties();
			props.load(inputStream);
			
			props.put("mail.smtp.auth", "true");
		 	props.put("mail.smtp.starttls.enable", "true");
		 	props.put("mail.smtp.host", "smtp.gmail.com");
		 	props.put("mail.smtp.port", "587");
		 	props.put("mail.smtp.user", "pruebajava2018@gmail.com");
		 	props.put("mail.smtp.password","1234Abcd");
		}
		catch(IOException exIO) {
			LogException log = new LogException(exIO,"Error al buscar archivo proporties para envio de mails",Level.ERROR);
			throw log;
		}
		catch(Exception ex) {
			LogException log = new LogException(ex,"Error al instanciar clase Email",Level.ERROR);
			throw log;
		}
	}
	
	public void send(String para,String asunto,String mensaje) {
		Session session = Session.getDefaultInstance(props);
		
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(props.getProperty("mail.smtp.user")));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(para));
			message.setSubject(asunto);
			message.setText(mensaje);
			
			Transport t = session.getTransport("smtp");
			t.connect((String)props.getProperty("mail.smtp.user"), (String)props.getProperty("mail.smtp.password"));
			t.sendMessage(message, message.getAllRecipients());
			t.close();
		}
		catch(MessagingException exMsg) {

			exMsg.printStackTrace();
			throw new RuntimeException(exMsg);
		}
		catch(Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
	}
}
