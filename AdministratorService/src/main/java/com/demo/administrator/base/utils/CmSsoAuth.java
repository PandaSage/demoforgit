package com.demo.administrator.base.utils;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/*
import com.initech.eam.nls.CookieManager;
import com.initech.eam.smartenforcer.SECode;*/

@SuppressWarnings({ "rawtypes", "unchecked" })
public class CmSsoAuth {
	/***[SSO CONFIGURATION]**]***********************************************************************
	* 
	* NLS URL 값을 개발/운영에 따라 다음과 같이 설정한다.(운영적용 시에는 운영 URL로 설정되어야 함)
	* 개발 : http://ssodev.lgchem.com
	* 운영 : http://sso.lgchem.com
	*************************************************************************************************/

	//private	static	String NLS_URL	= "http://ssodev.lgchem.com"; //SSO 개발서버
	private	static	String NLS_URL	= "http://sso.lgchem.com"; //SSO 운영서버
	
	/***************************************************************************************
	 * 다음은 기본 설정을 유지합니다.
	 ***************************************************************************************/
	private static	String	NLS_PORT		=	"8001";
	private	String	NLS_LOGIN_URL			=	NLS_URL + ":" + NLS_PORT + "/nls3/clientLogin.jsp";
	private static	String	NLS_ERROR_URL	=	NLS_URL + ":" + NLS_PORT + "/nls3/error.jsp";
	private	static	Vector	PROVIDER_LIST					=	new	Vector();
 	private	static	final	int	COOKIE_SESSTION_TIME_OUT	=	3000000;

	private static String TOA = "1";
	private static String SSO_DOMAIN = ".lgchem.com";
	
	static{
		PROVIDER_LIST.add("sso.lgchem.com");
	}

	/*public static String getSsoId(HttpServletRequest request) {
		String encF = CookieManager.getCookieValue("InitechEamCookieEnc" , request);
		if("T".equals(encF)) { 
			CookieManager.setEncStatus(true);
		}else{
			CookieManager.setEncStatus(false);
		}
		
		String sso_id = null;
		sso_id = CookieManager.getCookieValue(SECode.USER_ID, request);
		
		return sso_id;
	}

	public static String getEamSessionCheck(HttpServletRequest request,HttpServletResponse response){
		String retCode = "";
		try {
			retCode = CookieManager.verifyNexessCookie(request, response, 10, COOKIE_SESSTION_TIME_OUT, PROVIDER_LIST);
		} catch(Exception npe) {
			npe.printStackTrace();
		}
		return retCode;
	}
	
	public static boolean doSsoAuth(HttpServletRequest request,HttpServletResponse response) throws IOException {
		boolean	ssoAuth	=	false;
		*//***************************************************************************************
		 * 여기부터 SSO 프로세스가 시작됩니다.
		 ***************************************************************************************//*

		String uurl = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort() + request.getRequestURI();

		String sso_id = getSsoId(request); 
		System.out.println("[SSOAuth.jsp] sso_id=" + sso_id);
		if (sso_id == null) { // sso_id가 null인 경우는 SSO 로그인하지 않은 사용자가 접속했을 경우이다.
			CookieManager.addCookie(SECode.USER_URL, uurl, SSO_DOMAIN, response);
			CookieManager.addCookie(SECode.R_TOA, TOA, SSO_DOMAIN, response);
			//SSO 로그인 페이지로 이동
			//response.sendRedirect(NLS_LOGIN_URL + "?UURL=" + uurl + "&RTOA=" + TOA);
			return	ssoAuth;
		
		} else { //sso_id가 null이 아닌경우로 SSO 인증된 사용자가 접속했을 경우이다.
			
			//SSO 인증된 사용자의 인증토큰을 검증한다.(위변조 검증)
			String retCode = getEamSessionCheck(request,response);	
			System.out.println("[SSOAuth.jsp] retCode=" + retCode);	
		
			if(!retCode.equals("0")){ // 검증결과가 0이 아닐경우는 에러처리를 위해 SSO 에러페이지로 redirect
				response.sendRedirect(NLS_ERROR_URL + "?errorCode=" + retCode);
				return ssoAuth; 
				
			}else{ // 검증결과가 0인경우로 SSO 인증토큰도 정상이므로 sso_id를 어플리케이션에서 이용할 수 있다.
				//debug result
				System.out.println("SSO 인증 성공!!(retCode:" + retCode +") <br>");
				System.out.println("SSO ID=" + sso_id);
				
				ssoAuth	=	true;			// sso 연동 성공
				HttpSession	session	=	request.getSession();
				
				session.setAttribute("SSOID", sso_id); //어플리케이션에서 사용자의 아이디가 필요할 경우 세션에서 획득할 수 있다.
				
				*//****[개발 담당자 확인]***************************************************
				 *
				 * 1. SSO 인증 이후 어플리케이션의 인증페이지로 redirect 
				 * 2. session에 저장한 SSOID 값을 이용하여 어플리케이션 세션을 생성한다.
				 *
				 *************************************************************************//*
				 
				 response.sendRedirect("어플리케이션 세션을 생성하는 URL 기입!!");
				 return ssoAuth;
			}//end if(!retCode.equals("0")){
		}//end if (sso_id == null)
	}*/
}
