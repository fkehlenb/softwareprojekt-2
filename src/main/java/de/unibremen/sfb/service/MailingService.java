package de.unibremen.sfb.service;

import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.Serializable;

/** This class handles the mailing service */
@Slf4j
public class MailingService implements Serializable {

    @Resource(mappedName = "java:jboss/mail/Default")
    private Session session;

    /** Send an email address to a user
     * @param addr - the target's email address
     * @param message - the email content message
     * @param subject - the email subject */
    public void sendmail(String addr,String message,String subject){
        try {
            MimeMessage m = new MimeMessage(session);
            Address sender = new InternetAddress("SFB.de");
            Address[] recipient = new InternetAddress[]{new InternetAddress(addr)};
            m.setFrom("SFB-UserService");
            m.setRecipients(Message.RecipientType.TO,recipient);
            m.setSubject(subject);
            m.setContent(message,"text/plain");
            Transport.send(m);
            log.info("SENT EMAIL WITH SUBJECT " + subject + " TO " + addr);
        }
        catch (Exception e){
            e.printStackTrace();
            log.error("COULDN'T SEND EMAIL!");
        }
    }
}
