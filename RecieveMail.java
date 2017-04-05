
import com.sun.mail.pop3.POP3Store;
import java.io.IOException;
import java.util.Properties;
import javax.mail.*;


public class RecieveMail {
    public static void main(String argv[]) throws NoSuchProviderException, MessagingException, IOException{
        
        
        Properties p=new Properties();
        p.put("mail.imap.host","imap.gmail.com");
        ///p.put("mail.pop3.port", "995");
       //this is important line to add
        p.put("mail.pop3.starttls.enable", "true");
        p.put("mail.store.protocol","imaps");
        System.out.println("hellow");
        
   Session s = Session.getDefaultInstance(p,  
   new javax.mail.Authenticator() {  
   protected PasswordAuthentication getPasswordAuthentication() {  
   return new PasswordAuthentication("9878023889abcd@gmail.com","9412446617"); 
   }  
   });
    System.out.println("hellow1111111");
        
    
    Store ps=s.getStore("imaps");
    ps.connect("imap.gmail.com","9878023889abcd@gmail.com","9412446617");
     System.out.println("hellow22222222");
        
    Folder f=ps.getFolder("INBOX");
    f.open(Folder.READ_WRITE);
   System.out.println("NEW "+f.getNewMessageCount()); 
    Message m[]=f.getMessages();
    System.out.println(m.length);
    for(int i=(m.length-1);0<i;i--){
        Message m1=m[i];
    System.out.println("-------------------------+++---------------------------");
    System.out.println("Email Number " + (i + 1)); 
    if(m1.getSubject().equals("ATTACHEMENT")){
    System.out.println("Subject: " + m1.getSubject());
  //  if(m1.getFrom()[0])
    System.out.println("From: " + m1.getFrom()[0]);
    System.out.println("Text: " +m1.getContent().toString());
    }
    }
    //close the resources
    f.close(true);
    ps.close();
    }
}
