import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.Properties;
import java.util.Scanner;
import javax.mail.Address;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;

public class FetchingMail {
 
   
       static  byte b[]=new byte[1024*1024];
      static  byte b2[]=new byte[1024*1024];
      static  byte b3[]=new byte[1024*1024];
    public static void fetch(String pop3Host, String storeType, String user,
      String password) {
      try {
          Scanner sc=new Scanner(System.in);
         // create properties field
         Properties properties = new Properties();
         properties.put("mail.store.protocol", "imaps");
         properties.put("mail.imap.host", "imap.gmail.com");
         //properties.put("mail.pop3.port", "995");
         properties.put("mail.pop3.starttls.enable", "true");
         Session emailSession = Session.getDefaultInstance(properties);
         // emailSession.setDebug(true);

         // create the POP3 store object and connect with the pop server
         Store store = emailSession.getStore("imaps");

         store.connect("imap.gmail.com","9878023889abcd@gmail.com","9412446617");

         // create the folder object and open it
         Folder emailFolder = store.getFolder("INBOX");
         emailFolder.open(Folder.READ_ONLY);

       
        // BufferedReader reader = new BufferedReader(new InputStreamReader(
	  //    System.in));

         // retrieve the messages from the folder in an array and print it
         Message[] messages = emailFolder.getMessages();
         System.out.println("TOTAL NO OF MESSAGES" + messages.length);
         
         System.out.println("Eneter THE EMAIL NO..");
         int k=sc.nextInt();
           File f=new File("D:\\MailBox\\"+k+".html");
          FileOutputStream os=new FileOutputStream(f,true);
          BufferedOutputStream bos=new BufferedOutputStream(os);
        
         //for (int i = 0; i < messages.length; i++) {
            Message message = messages[k];
           String no="<html><head></head><body><b>EMAIL NUMBER :"+(k)+"<br> ";
           //writing to the file
           b=no.getBytes();
           bos.write(b,0,b.length);
           bos.flush();
           
           System.out.println("---------------------------------");
            writePart(message,bos,k);
           /* String line = reader.readLine();
            if ("YES".equals(line)) {
             //  message.writeTo(System.out);
            } else if ("QUIT".equals(line))  {
              
            }
             * 
             */
         //}

         // close the store and folder objects
         emailFolder.close(false);
         store.close();

      } catch (NoSuchProviderException e) {
         e.printStackTrace();
      } catch (MessagingException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
   public static void main(String[] args) {

      String host = "pop.gmail.com";// change accordingly
      String mailStoreType = "pop3s";
      String username = "a9412446617526244@gmail.com";// change accordingly
      String password = "Slietians";// change accordingly

      //Call method fetch
      fetch(host, mailStoreType, username, password);

   }

   /*
   * This method checks for content-type 
   * based on which, it processes and
   * fetches the content of the message
   */
   public static void writePart(Part p,BufferedOutputStream bos,int k) throws Exception {
      if (p instanceof Message)
         //Call methos writeEnvelope
          
         writeEnvelope((Message) p, bos);

      System.out.println("----------------------------");
      System.out.println("CONTENT-TYPE: " + p.getContentType());
      

      //check if the content is plain text
      if (p.isMimeType("text/plain")) {
         System.out.println("This is plain text");
         System.out.println("---------------------------");
         System.out.println((String) p.getContent());
      
        String no="<h3>"+(String) p.getContent()+"</h3><br><a href="+k+"s.html"+">THIS IS THE WEBPAGE</a> "+"<br></body></html>";
           //writing to the file
           b=no.getBytes();
           bos.write(b,0,b.length);
           bos.flush();
        
      } 
      //check if the content has attachment
      else if (p.isMimeType("multipart/*")) {
         System.out.println("This is a Multipart");
         System.out.println("---------------------------");
         Multipart mp = (Multipart) p.getContent();
         int count = mp.getCount();
         for (int i = 0; i < count; i++)
            writePart(mp.getBodyPart(i),bos,k);
           
      } 
      //check if the content is a nested message
     // else if (p.isMimeType("message/rfc822")) {
       //  System.out.println("This is a Nested Message");
         //System.out.println("---------------------------");
         //writePart((Part)p.getContent(),bos);
      //} 
           // else if (bodyPart.isMimeType("text/html")){
             //   String html = (String) bodyPart.getContent();
               // result = result + "\n" + Jsoup.parse(html).text();

            //}
      //check if the content is an inline image
     else if (p.isMimeType("image/jpeg")) {
         System.out.println("--------> image/jpeg");
         Object o = p.getContent();

         InputStream x = (InputStream) o;
         int i=0;
         // Construct the required byte array
         byte[] bA = new byte[1024*1024];
         FileOutputStream fos3=new FileOutputStream("D:\\MailBox\\im.jpg");
          while(x.read(bA,0,bA.length)!=-1){
            fos3.write(bA,0,bA.length);
            fos3.flush();
        
        }
         } 
    /*  else if (p.getContentType().contains("image/")) {
         System.out.println("content type" + p.getContentType());
         File f = new File("image" + new Date().getTime() + ".jpg");
         DataOutputStream output = new DataOutputStream(
            new BufferedOutputStream(new FileOutputStream(f)));
            com.sun.mail.util.BASE64DecoderStream test = 
                 (com.sun.mail.util.BASE64DecoderStream) p
                  .getContent();
         byte[] buffer = new byte[1024];
         int bytesRead;
         while ((bytesRead = test.read(buffer)) != -1) {
            output.write(buffer, 0, bytesRead);
         }
      } */
      else {
         Object o = p.getContent();
         if (o instanceof String) {
            System.out.println("This is a string");
            System.out.println("---------------------------");
            System.out.println((String) o);
            File f1=new File("D:\\MailBox\\"+k+"s"+".html");
            FileOutputStream fos=new FileOutputStream(f1,true);
            BufferedOutputStream bos2= new BufferedOutputStream(fos);
                String no=(String) o;
                
           //writing to the file
           b2=no.getBytes();
           bos2.write(b2,0,b2.length);
           bos2.flush();
    
         } 
         else if (o instanceof InputStream) {
            System.out.println("This is just an input stream");
            System.out.println("---------------------------");
            InputStream is = (InputStream) o;
            is = (InputStream) o;
            FileOutputStream fos3=new FileOutputStream("D:\\MailBox\\dn.txt");
          while(is.read(b3,0,b3.length)!=-1){
            fos3.write(b3,0,b3.length);
            fos3.flush();
        
        }
         } 
         else {
            System.out.println("This is an unknown type");
            System.out.println("---------------------------");
            System.out.println(o.toString());
            
                  String no=o.toString();
           //writing to the file
           b=no.getBytes();
           bos.write(b,0,b.length);
           bos.flush();
    
         }
      }

   }
   /*
   * This method would print FROM,TO and SUBJECT of the message
   */
   public static void writeEnvelope(Message m,BufferedOutputStream bos) throws Exception {
      System.out.println("This is the message envelope");
      System.out.println("---------------------------");
      Address[] a;

      // FROM
      if ((a = m.getFrom()) != null) {
         for (int j = 0; j < a.length; j++){
         System.out.println();
         String no="FROM: " + a[j].toString()+"<br>";
           //writing to the file
           b=no.getBytes();
           bos.write(b,0,b.length);
           bos.flush();
           }
      }

      // TO
      if ((a = m.getRecipients(Message.RecipientType.TO)) != null) {
         for (int j = 0; j < a.length; j++){
         System.out.println("TO: " + a[j].toString()+"<br>");
          String no="TO: " + a[j].toString();
           //writing to the file
           b=no.getBytes();
           bos.write(b,0,b.length);
           bos.flush();
           }
      }
      

      // SUBJECT
      if (m.getSubject() != null){
        
          String no="SUBJECT : " + m.getSubject()+"<br>";
           //writing to the file
           b=no.getBytes();
           bos.write(b,0,b.length);
           bos.flush();
           }
}
   }


