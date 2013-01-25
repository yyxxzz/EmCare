package com.company.emcare.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;

public class MailUtil {
	private static Log log = LogFactory.getLog(MailUtil.class);

	public static void setMailTemplatePath(String mailTemplatePath){
		Properties p = new Properties();
		p.setProperty("file.resource.loader.path", mailTemplatePath);
		Velocity.init(p);
	}
	
	public static void sendMail(String to, String cc, String subjectTemplate,
			String bodyTemplate) throws Exception {
		/*Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");*/
		
		Properties props = new Properties();
		InputStream inStream =  MailUtil.class.getClassLoader().getResourceAsStream("mail.properties");
		try {
			props.load(inStream);
		} catch (IOException e1) {
			log.error("error in read mail.properties "+e1);
			return;
		}
		final String username = props.getProperty("mail.user");
		final String password = props.getProperty("mail.password");
		Session session=null;
		if(username!=null&&password!=null){
			session = Session.getInstance(props,new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
				
			});
		}else{
			 session = Session.getInstance(props);
		}
		
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.addRecipients(Message.RecipientType.TO,
					InternetAddress.parse(to));
			if(cc!=null){
				message.addRecipients(Message.RecipientType.CC,
						InternetAddress.parse(cc));
			}
			message.setSubject(subjectTemplate);

			/*
			 * Html body
			 */
			Multipart mp = new MimeMultipart();
			MimeBodyPart htmlPart = new MimeBodyPart();
			htmlPart.setContent(bodyTemplate, "text/html;charset=UTF-8");
			mp.addBodyPart(htmlPart);

			message.setContent(mp);
			log.info("sending mail to "+to);
			Transport.send(message);
		} catch (MessagingException e) {
			log.error(e);
			throw e;
		}
	}

	public static String renderMail(Map<?, ?> context, String mailTemplateName) {
		Template template = null;
		try {
			template = Velocity.getTemplate(mailTemplateName);
		} catch (Exception e) {
			log.error(e);
			//log.error("can not find mail template in " + mailTemplateName);
			return null;
		}
		// template
		VelocityContext vCtx = new VelocityContext(context);
		StringWriter contentWriter = new StringWriter();
		template.merge(vCtx, contentWriter);
		return contentWriter.toString();
	}
}
