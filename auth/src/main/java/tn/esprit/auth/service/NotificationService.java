package tn.esprit.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import tn.esprit.auth.user.model.User;

@Service
public class NotificationService {
	
	private JavaMailSender javaMailSender ;
	
	@Autowired
	public NotificationService(JavaMailSender javaMailSender){
		this.javaMailSender = javaMailSender;
	}
	
	public void sendNotification(User user,String Text) throws MailException{
		SimpleMailMessage mail =new SimpleMailMessage();
		mail.setTo(user.getEmail());
		mail.setFrom("projetbibliotheque21@gmail.com");
		mail.setSubject("Suivis de Votre Commande");
		mail.setText(Text);
		
		javaMailSender.send(mail);
	}

}
