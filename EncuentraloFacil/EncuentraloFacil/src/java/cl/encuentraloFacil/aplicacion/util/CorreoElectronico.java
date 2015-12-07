/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.encuentraloFacil.aplicacion.util;

import java.io.Serializable;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.log4j.Logger;

/**
 *
 * @author Mario
 */
public class CorreoElectronico implements Serializable {

    final static Logger logger = Logger.getLogger(CorreoElectronico.class);

    public void enviarCorreo() {
        // La dirección de envío (to)
        String para = "mario.gonzalez.22@outlook.com";
        final String password = "elsudoneal16";
        logger.info("Email envio : " + para);

        // La dirección de la cuenta de envío (from)
        final String de = "contacto.encuentralofacil@gmail.com";

        // Obtenemos las propiedades del sistema
        java.util.Properties propiedades = System.getProperties();

        // Configuramos el servidor de correo
        propiedades.put("mail.smtp.host", "smtp.gmail.com");
        propiedades.put("mail.stmp.user", de);

        //To use TLS
        propiedades.put("mail.smtp.auth", "true");
        propiedades.put("mail.smtp.starttls.enable", "true");
        propiedades.put("mail.smtp.password", password);
        //To use SSL
        propiedades.put("mail.imap.ssl.enable", "true"); 
        propiedades.put("mail.imap.auth.mechanisms", "XOAUTH2");
        propiedades.put("mail.smtp.socketFactory.port", "587");
        propiedades.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        propiedades.put("mail.smtp.auth", "true");
        propiedades.put("mail.smtp.port", "587");

        // Obtenemos la sesión por defecto
        //Session sesion = Session.getDefaultInstance(propiedades);
        Session sesion = Session.getInstance(propiedades, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(de, password);
            }
        });

        try {
            // Creamos un objeto mensaje tipo MimeMessage por defecto.
            MimeMessage mensaje = new MimeMessage(sesion);

            // Asignamos el “de o from” al header del correo.
            mensaje.setFrom(new InternetAddress(de));

            // Asignamos el “para o to” al header del correo.
            mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(para));

            // Asignamos el asunto
            mensaje.setSubject("Registro EncuentraloFacil");

            // Asignamos el mensaje como tal
            mensaje.setText("El mensaje de nuestro primer correo");

            // Enviamos el correo
            Transport.send(mensaje, de, password);
            // Transport.send(mensaje);
            logger.info("Mensaje Enviado");
        } catch (MessagingException e) {
            logger.error("Error enviar email a "+para+" : " + e.getMessage());
        } catch (Exception e) {
            logger.error("Error no identificado emial : " + e.getMessage());
        }
    }

}
