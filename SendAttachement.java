
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
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


public class SendAttachement {
    
    public static void main(String args[]) throws MessagingException{
    
     Properties props = new Properties();
     props.put("mail.smtp.auth", "true");
     props.put("mail.smtp.starttls.enable", "true");
     props.put("mail.smtp.host","smtp.gmail.com");
     props.put("mail.smtp.port", "25"); 
     // Get the Session object. 
     Session s = Session.getInstance(props, new javax.mail.Authenticator()
     { protected PasswordAuthentication getPasswordAuthentication() 
     { return new PasswordAuthentication("a9412446617526244@gmail.com", "Slietians"); } });
        
        Message m=new MimeMessage(s);
        m.setFrom(new InternetAddress("a9412446617526244@gmail.com"));
        m.setRecipient(Message.RecipientType.TO,new InternetAddress("9878023889abcd@gmail.com"));
        m.setSubject("ATTACHEMENT");
        
        Multipart mu=new MimeMultipart();
        BodyPart bp1=new MimeBodyPart();
        bp1.setText("THIS MAIL CARRIES ATTACHMENT");
        BodyPart bp2=new MimeBodyPart();
        String filename="C:\\Users\\mjy\\Documents\\NetBeansProjects\\MailAccumulator\\maxresdefault.jpg";
        DataSource ds=new FileDataSource(filename);
        bp2.setDataHandler(new DataHandler(ds));
        bp2.setFileName(filename);
        
        mu.addBodyPart(bp1);
        mu.addBodyPart(bp2);
        
        m.setContent(mu);
        Transport.send(m);
        System.out.println("MESSAGE SENT PROPERLY");
    }
}
