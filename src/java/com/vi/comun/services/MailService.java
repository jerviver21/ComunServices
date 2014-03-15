/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vi.comun.services;

import com.vi.comun.dominio.AudMail;
import com.vi.comun.locator.ParameterLocator;
import com.vi.comun.util.Encriptador;
import java.util.Date;
import java.util.Properties;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jerviver21
 */
@Stateless
@LocalBean
public class MailService {
    @PersistenceContext(unitName = "PUComun")
    private EntityManager em;
    ParameterLocator locator;
    
    public MailService(){
        locator = ParameterLocator.getInstance();
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public boolean enviarMail(final AudMail datosMail) throws MessagingException{
        System.out.println("Init Sending mail..."+locator.getParameter("smtp_user"));
        
        Properties props = new Properties();
        props.setProperty("mail.smtp.host", locator.getParameter("smtp_host"));
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.port", locator.getParameter("smtp_port"));
        props.setProperty("mail.smtp.user", locator.getParameter("smtp_user"));
        props.setProperty("mail.smtp.auth", "true");
        
        Session mailSession = Session.getDefaultInstance(props);
        mailSession.setDebug(true);
        
        MimeMessage message = new MimeMessage(mailSession);
        message.setFrom(new InternetAddress(locator.getParameter("smtp_user")));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(datosMail.getDestinatario()));
        message.setSubject(datosMail.getAsunto());
        message.setText(datosMail.getMensaje());
        
        Transport transport = mailSession.getTransport("smtp");
        transport.connect(locator.getParameter("smtp_user"),Encriptador.decrypt(locator.getParameter("smtp_clave")));
        transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
        transport.close();
        
        System.out.println("Finish Sending mail...");
        datosMail.setFechaHora(new Date());
        em.persist(datosMail);
        return false;
    }

}
