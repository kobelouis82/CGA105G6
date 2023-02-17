package com.mailService.model;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {
	
	// 設定傳送郵件:至收信人的Email信箱,Email主旨,Email內容
	public void sendMail(String to, String subject, String messageText) {
			
	   try {
		   // 設定使用SSL連線至 Gmail smtp Server
		   Properties props = new Properties();
		   props.put("mail.smtp.host", "smtp.gmail.com");
		   props.put("mail.smtp.socketFactory.port", "465");
		   props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		   props.put("mail.smtp.auth", "true");
		   props.put("mail.smtp.port", "465");

       // ●設定 gmail 的帳號 & 密碼 (將藉由你的Gmail來傳送Email)
       // ●1) 登入你的Gmail的: 
       // ●2) 點選【管理你的 Google 帳戶】
       // ●3) 點選左側的【安全性】
       
       // ●4) 完成【兩步驟驗證】的所有要求如下:
       //     ●4-1) (請自行依照步驟要求操作之.....)
       
       // ●5) 完成【應用程式密碼】的所有要求如下:
       //     ●5-1) 下拉式選單【選取應用程式】--> 選取【郵件】
       //     ●5-2) 下拉式選單【選取裝置】--> 選取【Windows 電腦】
       //     ●5-3) 最後按【產生】密碼
	     final String myGmail = "tmpss91015@gmail.com";//改
//	     final String myGmail_password = "ddjomltcnypgcstn";
	     final String myGmail_password = "wzmnginphoyygads";//改自己
		   Session session = Session.getInstance(props, new Authenticator() {
			   protected PasswordAuthentication getPasswordAuthentication() {
				   return new PasswordAuthentication(myGmail, myGmail_password);
			   }
		   });

		   Message message = new MimeMessage(session);
		   message.setFrom(new InternetAddress(myGmail));
		   message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to));
		  
		   //設定信中的主旨  
		   message.setSubject(subject);
		   //設定信中的內容 
		   message.setText(messageText);

		   Transport.send(message);
		   System.out.println("傳送成功!");
     }catch (MessagingException e){
	     System.out.println("傳送失敗!");
//	     e.printStackTrace();
     }
   }
	
	 public static void main (String args[]){

      String to = "tmpss91015@gmail.com";
      
      
      String subject = "忘記密碼驗證信";
      
      String ch_name = "使用者，";
      String passRandom = "541450";
      String alert = "請在120秒內將驗證碼輸入!" + "\n" + "逾時驗證碼將會失效且頁面將會重新跳轉至忘記密碼頁面!";
      String messageText = "Hello! " + ch_name + " 您的密碼為: " + passRandom + "\n" + alert; 
       
      SendMail mailService = new SendMail();
      mailService.sendMail(to, subject, messageText);

   }


}
