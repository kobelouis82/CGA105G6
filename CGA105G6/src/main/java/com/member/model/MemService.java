package com.member.model;

import java.sql.Date;
import java.util.List;

import java.util.Map;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class MemService {

	private Mem_interface dao;

	public MemService() {
		dao = new MemJDBCDAO();
	}

	public MemVO addMem(String memName, String memTel, String memCity,
			Date memBirth, String memDist, String memAdd,String memMail,
			String memAccount, String memPassword,byte[] memPhoto,Integer memAccess,
			Integer articleReport, Integer messageReport) {

		MemVO memVO = new MemVO();
		memVO.setMemName(memName);
		memVO.setMemTel(memTel);
		memVO.setMemCity(memCity);
		memVO.setMemBirth(memBirth);
		memVO.setMemDist(memDist);
		memVO.setMemAdd(memAdd);
		memVO.setMemMail(memMail);
		memVO.setMemAccount(memAccount);
		memVO.setMemPassword(memPassword);
		memVO.setMemPhoto(memPhoto);
		memVO.setMemAccess(memAccess);
		memVO.setArticleReport(articleReport);
		memVO.setMessageReport(messageReport);
		
		dao.insert(memVO);

		return memVO;
	}

	public MemVO updatemem(Integer memNo, String memName, String memTel, String memCity,
			Date memBirth, String memDist, String memAdd,String memMail,
			String memAccount, String memPassword,byte[] memPhoto,Integer memAccess,
			Integer ArticleReport, Integer messageReport) {

		MemVO memVO = new MemVO();
		memVO.setMemNo(memNo);
		memVO.setMemName(memName);
		memVO.setMemTel(memTel);
		memVO.setMemCity(memCity);
		memVO.setMemBirth(memBirth);
		memVO.setMemDist(memDist);
		memVO.setMemAdd(memAdd);
		memVO.setMemMail(memMail);
		memVO.setMemAccount(memAccount);
		memVO.setMemPassword(memPassword);
		memVO.setMemPhoto(memPhoto);
		memVO.setMemAccess(memAccess);
		memVO.setArticleReport(ArticleReport);
		memVO.setMessageReport(messageReport);
		dao.update(memVO);

		return memVO;
	}

	public void deleteMem(Integer memNo) {
		dao.delete(memNo);
	}

	public MemVO getOneMem(Integer memNo) {
		return dao.findByPrimaryKey(memNo);
	}

	public List<MemVO> getAll() {
		return dao.getAll();
	}
	public List<MemVO> getAllMem(Map<String, String[]> map){
		return dao.getAllMem(map);
	}
	
	//寄送驗證信
	public void sendMail(String to, String subject, String messageText) {
		
		   try {

			   Properties props = new Properties();
			   props.put("mail.smtp.host", "smtp.gmail.com");
			   props.put("mail.smtp.socketFactory.port", "465");
			   props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
			   props.put("mail.smtp.auth", "true");
			   props.put("mail.smtp.port", "465");


		     final String myGmail = "tmpss91015@gmail.com";
		     final String myGmail_password = "wzmnginphoyygads";
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
		     e.printStackTrace();
	     }
	   }

	// 取得驗證亂碼
	public String getAuthCode() {
		StringBuilder s = new StringBuilder(8);

		for (int i = 1; i <= 8; i++) {
			int num = (int) (Math.random() * 3);
			if (num == 0) {
				s.append((char) ('A' + (int) (Math.random() * 26)));
			} else if (num == 1) {
				s.append((char) ('a' + (int) (Math.random() * 26)));
			} else {
				s.append((0 + (int) (Math.random() * 10)));
			}

		}
		return s.toString();
	}
	
	public void updateStatus(Integer mem_id, Integer state) {
		
		dao.updateStatus(mem_id,state);
	}

    

    public MemVO checkAccount(String mem_account){
        return dao.checkAccount(mem_account);
    };
          
    public MemVO findPassword(String mem_account,String mem_email){
	    return dao.findPassword(mem_account, mem_email);
    };
    public MemVO findByAcAndPwd(String memAccount, String memPassword) {
    	return dao.findByAcAndPwd(memAccount, memPassword);
    	};
    	public static void main(String[] args) {

    		MemJDBCDAO dao = new MemJDBCDAO();
    		MemService memSV=new MemService();
    		MemVO memVO=memSV.getOneMem(5);
    	memVO.setMemAccess(3);
    	dao.update(memVO);
    		System.out.print(memVO.getMemNo() + ",");
   		System.out.print(memVO.getMemName() + ",");
   		System.out.print(memVO.getMemAccess() + ",");
    		System.out.print(memVO.getMessageReport() + ",");
    	}
}

