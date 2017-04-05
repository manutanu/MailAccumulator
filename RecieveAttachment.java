
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;



public class RecieveAttachment {
    static byte[] b=new byte[1024*1024];
public static void main(String args[]) throws NoSuchProviderException, MessagingException, IOException{
    File f1=new File("D:\\MailBox\\dn.txt");
    FileOutputStream fos=new FileOutputStream(f1);
    BufferedOutputStream bos=new BufferedOutputStream(fos);
    
    Properties p=new Properties();
    p.put("mail.imap.host","imap.gmail.com" );
    //p.put("mail.pop3.port","995");
    p.put("mail.pop3.starttls.enable",true);
    p.put("mail.store.protocol","imaps");
    
    
    Session s = Session.getDefaultInstance(p,  
   new javax.mail.Authenticator() {  
   protected PasswordAuthentication getPasswordAuthentication() {  
   return new PasswordAuthentication("9878023889abcd@gmail.com","9412446617"); 
   }  
   });
    
    Store st=s.getStore("imaps");
    st.connect("imap.gmail.com","9878023889abcd@gmail.com","9412446617");
    
    Folder f=st.getFolder("INBOX");
    f.open(Folder.READ_WRITE);
    System.out.println(" "+f.getMessageCount());
    Message[] m=f.getMessages();
    //for(int i=0;i<m.length;i++){
    Message ms=m[1830];
    System.out.println("h");
    if(ms.isMimeType("multipart/*")){
    Multipart mu=(Multipart)ms.getContent();
    System.out.println("h1");
    for(int j=0;j<mu.getCount();j++){
        BodyPart bp=mu.getBodyPart(j);
        if(bp.isMimeType("text/plain")){
           System.out.println( (String)bp.getContent());
        }else{
        InputStream is=bp.getInputStream();
        BufferedInputStream bis=new BufferedInputStream(is);
        while(bis.read(b,0,b.length)!=-1){
            bos.write(b,0,b.length);
            bos.flush();
        
        }
        }
        }
    //}
    
    }else if(ms.isMimeType("text/string")){
   System.out.println("here");
    }
    
}    
}
