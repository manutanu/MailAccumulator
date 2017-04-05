

import java.util.Properties;
import javax.mail.Session;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SimpleMailSend {
 
    public static void main(String args[]){
    
        Properties pr=new Properties();
        pr.put("mail.smtp.auth", "true");
 pr.put("mail.smtp.starttls.enable", "true");
     pr.put("mail.smtp.host","smtp.gmail.com");
     pr.put("mail.smtp.port", "25"); 
      //pr.put("mail.smtp.port", "465"); 
        
        Session s = Session.getDefaultInstance(pr,  
   new javax.mail.Authenticator() {  
   protected PasswordAuthentication getPasswordAuthentication() {  
   return new PasswordAuthentication("a9412446617526244@gmail.com","Slietians"); 
   }  
   });    
        
        try{
        MimeMessage m=new MimeMessage(s);
        m.setFrom(new InternetAddress("a9412446617526244@gmail.com"));
        m.setRecipient(Message.RecipientType.TO,new InternetAddress("9878023889abcd@gmail.com"));
        m.setSubject("SECOND ");
        m.setText("HELLOW EVERY ONE FRIENDS");
        
        
        Transport.send(m);
        System.out.println("DONE!!!!");
        }catch(Exception e){
        System.err.println(e);
        }
    
    }
 
}
