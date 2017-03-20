package Interfaccia;

import java.util.Properties;
import java.util.regex.Pattern;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Classe che permette di inviare automaticamente una mail all'utente che si è appena registrato a Facedoor
 * 
 * @author Marco Mandelli e Mirko Longhi
 *
 */
public class Email {

	/**
	 * Metodo che permette di inviare l'email all'utente appena registrato. 
	 * 
	 * @param destinatario utente che si e' appena registrato
	 * @param mittente mail di facedoor
	 * @param password password della mail di facedoor
	 * @param object oggetto della mail
	 * @param descrizione testo della mail
	 * @throws MessagingException errore messaggio
	 */
	public static void sendMail(String destinatario,final String mittente, final String password,String object, String descrizione) throws MessagingException{
		
		  Properties props = new Properties();
	      props.put("mail.smtp.auth", "true");
	      props.put("mail.smtp.starttls.enable", "true");
	      props.put("mail.smtp.host", "smtp.gmail.com");
	      props.put("mail.smtp.port", "587");
	      props.put("mail.smtp.user", mittente);
	      props.put("mail.smtp.password", password);
	      
	      Session session = Session.getInstance(props, new javax.mail.Authenticator() {
	    	         protected PasswordAuthentication getPasswordAuthentication() {
	    	            return new PasswordAuthentication(mittente, password);
	    	         }
	    	      });
	      
	      //Creazione del messaggio
	      MimeMessage msg = new MimeMessage(session);
	      msg.setSubject(object);
	      msg.setText(descrizione);
	      
	      //Aggiungo indirizzi mittente e destinatario
	      InternetAddress from = new InternetAddress(mittente);
	      Address[] to = {new InternetAddress(destinatario), new InternetAddress(mittente)};
	      msg.setFrom(from);
	      msg.setRecipients(Message.RecipientType.BCC, to);
	      
	      //INVIO
	      Transport.send(msg);

	}
	
	/**
	 * Metodo che permette di controllare l'esistenza dell'e-mail inserita dall'utente durante la registrazione. 
	 * 
	 * @param input e-mail da controllare
	 * @return boolean ritorna true se la mail inserita e' corretta(esiste) altrimenti false.
	 */
	public static boolean checkEmail(String input){
		String regex = "[a-zA-Z0-9._%-]+@[a-zA-Z0-9._%-]+\\.[a-zA-Z]{2,4}";
		if(Pattern.matches(regex, input)){
			return true;
		}else{
			return false;
		}
		
		
	}
}
