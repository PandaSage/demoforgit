package com.demo.administrator.base.utils;

import java.io.IOException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.io.Resources;

import com.demo.administrator.base.object.CmMap;

public class CmMailNoCert {
	
	
	private static  final	Log			logger		= LogFactory.getLog("com.iShift.framework.util.CmMailNoCert.class");
	private static	Properties	mailProp;
	
	private static	String		mailServerIp;
	
	
	private static boolean initMailConfig() {
		boolean result		= true;
		
		try {
			
			if (mailServerIp == null || mailServerIp.equals(""))
				result	= getProperties();
			
		} catch (Exception e) {
			System.out.println("### CmMailNoCert.initMailConfig ###");
			e.printStackTrace();
		}
		
		return result;
	}
	
	private static boolean initMailer() {
		
		boolean	result	= true;
		
		try {
			mailProp	= new Properties();
	
			mailProp.put("mail.smtp.host", mailServerIp);
			mailProp.put("mail.smtp.port", "25");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
	
	public static boolean getProperties() {
		boolean result		= true;
		
		try {
			
			Properties	props	= Resources.getResourceAsProperties("config.properties");

			mailServerIp	= CmFunction.getStringValue(props.getProperty("MAILER_SERVER"));
			
		} catch (Exception e) {
			System.out.println("### CmMailNoCert.getProperties ###");
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * 메일 보내기
	 * @param fromName
	 * @param fromEmail
	 * @param to
	 * @param cc
	 * @param title
	 * @param content
	 * @return
	 */
	public static CmMap send(String fromName, String fromEmail, String to, String cc, String title, String content) {
		return send(fromName, fromEmail, to, cc, title, content, null);
	}
	
	/**
	 * 메일 보내기
	 * @param fromName
	 * @param fromEmail
	 * @param to
	 * @param cc
	 * @param title
	 * @param content
	 * @return
	 */
	public static CmMap send(String to, String cc, String title, String content) {
		return send("", "", to, cc, title, content, null);
	}
	
	/**
	 * 메일 보내기
	 * @param fromName
	 * @param fromEmail
	 * @param to
	 * @param cc
	 * @param title
	 * @param content
	 * @param fileName
	 * @return
	 */
	public static CmMap send(String fromName, String fromEmail, String to, String cc, String title, String content, String[] fileName) {
		
		if (logger.isInfoEnabled())
		{
			logger.info("########## real sendMailApproval ##########");
			logger.info("# title : " + title);
			logger.info("# from user : " + fromName);
			logger.info("# from mail : " + fromEmail);
			logger.info("# to mail : " + to);
			logger.info("# cc mail : " + cc);
			logger.info("######################################");
		}
		
		CmMap		rvo		= new CmMap();
		rvo.put("isSend", true);
		
		if (!initMailConfig())
		{
			rvo.put("isSend", false);
			rvo.put("errorMessage", CmFunction.getBundleString("pms.class.noemailconfigfile"));
			return rvo;
		}
		
		if (!initMailer()) {
			rvo.put("isSend", false);
			rvo.put("errorMessage", CmFunction.getBundleString("pms.class.notcompletedmailserversetting"));
			return rvo;
		}
		
		CmMailAuthenticator		mailAuth		= new CmMailAuthenticator(null, null);
		Session					session			= null;
		try {
			
			session	= Session.getInstance(mailProp, mailAuth);
			
		} catch (Exception e) {
			logger.error("### CmMailNoCert.send ###");
			e.printStackTrace();
			
			rvo.put("isSend", false);
			rvo.put("errorMessage", CmFunction.getBundleString("pms.class.errormailinit"));
			return rvo;
		}
		
		try {
			
			MimeMessage		msg			= new MimeMessage(session);
			Multipart		mp			= new MimeMultipart();
			MimeBodyPart	msg_part	= new MimeBodyPart();
			
			
			msg.setContentLanguage(new String[]{"EUC-KR", "8859_1", "UTF-8"});
			
			/*String		sessionUserNm	= "";
			String		sessionEmail	= "";
			
			try {
				HttpSession	httpSsession	= CmFunction.getCurrentRequest().getSession();
				sessionUserNm	= CmFunction.getStringValue((String)httpSsession.getAttribute("v_usernm"));
				sessionEmail	= CmFunction.getStringValue((String)httpSsession.getAttribute("v_email"));
			} catch (Exception e) {}*/
			
//			if ( fromName == null || fromName.equals("") ) {
//				fromName = !sessionUserNm.equals("")? sessionUserNm : CmPathInfo.getMAIL_FROM_NAME();
//			}
//			
//			if (fromEmail == null || fromEmail.equals("")) {
//				fromEmail = !sessionEmail.equals("")? sessionEmail : CmPathInfo.getMAIL_FROM_EMAIL();
//			}
			
			//메일 무조건 SYSTEM 대표계정으로 보내기
			fromName	= CmPathInfo.getMAIL_FROM_NAME();
			fromEmail	= CmPathInfo.getMAIL_FROM_EMAIL();
			
			//fromName	= new String(CmFunction.replace(fromName, " ", "%20").getBytes("UTF-8"), "8859_1");
			fromName	= MimeUtility.encodeText(fromName, "EUC-KR", "Q");
			msg.setFrom(new InternetAddress(fromName + "<" + fromEmail + ">"));
			
			// 스펨메일 방지용으로 추가
			InternetAddress[] address = {new InternetAddress(fromEmail)};
			msg.setReplyTo(address);
			
			// 받는 사람 주소
			if (to != null && !to.equals("")) 
			{
				to	= new String(CmFunction.replace(to, " ", "%20").getBytes("UTF-8"), "8859_1");
				msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
			} 
			else 
			{
				rvo.put("isSend", false);
				rvo.put("errorMessage", CmFunction.getBundleString("pms.class.notoaddress"));
				return rvo;
			}

			if (cc != null && !"".equals(cc)) 
			{
				cc	= new String(CmFunction.replace(cc, " ", "%20").getBytes("UTF-8"), "8859_1");
				msg.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(cc, false));
			}

			// 메일 제목
			if (title != null && !"".equals(title)) 
			{
				msg.setSubject(MimeUtility.encodeText(title, "EUC-KR", "Q"));
			} 
			else 
			{
				rvo.put("isSend", false);
				rvo.put("errorMessage", CmFunction.getBundleString("pms.class.nomailtitle"));
				return rvo;
			}

			// 메일 내용
			if (content != null && !"".equals(content)) 
			{
				msg_part.setContent(content, "text/html; charset=utf-8");
			} 
			else 
			{
				rvo.put("isSend", false);
				rvo.put("errorMessage", CmFunction.getBundleString("pms.class.nomailcontent"));
				return rvo;
			}
			
			mp.addBodyPart(msg_part);
			
			if (fileName != null)
			{
				MimeBodyPart[]	file_part	= new MimeBodyPart[fileName.length];
				
				for (int i = 0; i < fileName.length; i++) 
				{
					if ("".equals(CmFunction.getStringValue(fileName[i]))) 
					{
						continue;
					}
					file_part[i] = new MimeBodyPart();
					          
					FileDataSource	fds	= new FileDataSource(fileName[i]);

					file_part[i].setDataHandler(new DataHandler(fds));
					
					file_part[i].setFileName(MimeUtility.encodeText(fds.getName(), "EUC-KR", "Q"));

					mp.addBodyPart(file_part[i]); 
				}
			}
			
			msg.setContent(mp);
			
			if (rvo.getBoolean("isSend")) {
				Transport	tr	= session.getTransport("smtp");
				tr.connect(mailServerIp, to);
				tr.sendMessage(msg, msg.getAllRecipients());
				tr.close();
			}
		
		} catch (MessagingException e) {
			System.out.println("### CmMailNoCert.send(MessagingException) ###");
			e.printStackTrace();
			rvo.put("isSend", false);
			rvo.put("errorMessage", CmFunction.getBundleString("pms.class.errormailsend"));
			return rvo;
		} catch (IOException e) {
			System.out.println("### CmMailNoCert.send(IOException) ###");
			e.printStackTrace();
			rvo.put("isSend", false);
			rvo.put("errorMessage", CmFunction.getBundleString("pms.class.errormailsend"));
			return rvo;
		} catch (Exception e) {
			System.out.println("### CmMailNoCert.send(Exception) ###");
			e.printStackTrace();
			rvo.put("isSend", false);
			rvo.put("errorMessage", CmFunction.getBundleString("pms.class.errormailsend"));
			return rvo;
		}
		
		return rvo;
	}
}
