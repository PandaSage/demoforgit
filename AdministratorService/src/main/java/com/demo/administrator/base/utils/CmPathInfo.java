package com.demo.administrator.base.utils;

import java.util.Properties;

import org.apache.ibatis.io.Resources;

public class CmPathInfo {

	private static String	REAL_SERVER_YN;
	private static String	ROOT_PATH;
	private static String	ROOT_FULL_URL;
	private static String	IMG_PATH;
	private static String	PDF_IMG_PATH;
	//private static String	IMG_AMORE_PATH;
	private static String	CSS_PATH;
	private static String	FLASH_PATH;
	private static String	SCRIPT_PATH;
	private static String	EDITOR_PATH;
	private static String	CHARTS_PATH;
	private static String	UPLOAD_PATH;
	private static String	UPLOAD_PDF_IMAGE_PATH;
	private static String	UPLOAD_FILE_PATH;
	private static String	UPLOAD_FILE_TEMP_PATH;
	private static String	UPLOAD_IMAGE_PATH;
	private static String	UPLOAD_IMAGE_TEMP_PATH;
	private static String	CHARSET		= "UTF-8";
	private static String	UPLOAD_USERIMAGE_FILE_PATH;
	private static String	TEST_RECEIVED_EMAIL;
	private static String	UPLOAD_IMG_PATH;

	private static final String	MAIL_FROM_NAME	= "Mail_admin";
	private static final String	MAIL_FROM_EMAIL	= "lgcqmid@lgchem.com";
	
	private static String	TEST_ADAUTH_DOMAIN;
	private static String	ADAUTH_DOMAIN;
	private static String	ADAUTH_ADMIN;
	private static String	ADAUTH_PWD;
	
	private static String	MAIN_IMAGE_UPLOAD_PATH;
	
	static {
		if (ROOT_PATH == null)
		{
			new CmPathInfo();
		}
	}
	
	public CmPathInfo() {
		if (ROOT_PATH == null)
			this.setPath();
	}
	
	public void setPath() {
		try {
			
			Properties	props			= Resources.getResourceAsProperties("config.properties");
			
			REAL_SERVER_YN				= CmFunction.getStringValue(props.getProperty("REAL_SERVER_YN")).toUpperCase().trim();
			ROOT_PATH					= CmFunction.getStringValue(props.getProperty("ROOT_PATH"));
			ROOT_FULL_URL				= CmFunction.getStringValue(props.getProperty("ROOT_FULL_URL"));
			setIMG_PATH(ROOT_FULL_URL	+ "IMG/");//+CmFunction.getLanguage().toUpperCase()+"/";
			setCSS_PATH(ROOT_FULL_URL	+ "CSS/");
			SCRIPT_PATH					= ROOT_FULL_URL	+ "js/";
			EDITOR_PATH					= ROOT_FULL_URL	+ "editor/";
			CHARTS_PATH					= ROOT_FULL_URL	+ "charts/";
			FLASH_PATH					= ROOT_PATH	+ "SWF/";
			
			UPLOAD_PATH					= CmFunction.getStringValue(props.getProperty("UPLOAD_PATH"));
			UPLOAD_PDF_IMAGE_PATH		= UPLOAD_PATH + "UPLOAD_PDF_IMAGE/";
			UPLOAD_FILE_PATH			= UPLOAD_PATH + "UPLOAD_FILE/";
			UPLOAD_FILE_TEMP_PATH		= UPLOAD_PATH + "UPLOAD_FILE_TEMP/";
			UPLOAD_IMAGE_PATH			= UPLOAD_PATH + "UPLOAD_IMAGE/";
			UPLOAD_IMAGE_TEMP_PATH		= UPLOAD_PATH + "UPLOAD_IMAGE_TEMP/";
			UPLOAD_USERIMAGE_FILE_PATH  = UPLOAD_PATH + "UPLOAD_USERIMAGE";
			UPLOAD_IMG_PATH				= ROOT_PATH + "UPLOAD/";
			
			MAIN_IMAGE_UPLOAD_PATH		= CmFunction.getStringValue(props.getProperty("MAIN_IMAGE_UPLOAD_PATH"));
			TEST_RECEIVED_EMAIL			= CmFunction.getStringValue(props.getProperty("TEST_RECEIVED_EMAIL"));
			
			TEST_ADAUTH_DOMAIN			= CmFunction.getStringValue(props.getProperty("TEST_ADAUTH_DOMAIN"));
			ADAUTH_DOMAIN				= CmFunction.getStringValue(props.getProperty("ADAUTH_DOMAIN"));
			ADAUTH_ADMIN				= CmFunction.getStringValue(props.getProperty("ADAUTH_ADMIN"));
			ADAUTH_PWD					= CmFunction.getStringValue(props.getProperty("ADAUTH_PWD"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getROOT_PATH() {
		return ROOT_PATH;
	}

	public static String getROOT_FULL_URL() {
		return ROOT_FULL_URL;
	}

	public static String getIMG_PATH() {
		return ROOT_FULL_URL + "IMG/" + CmFunction.getLanguage().toUpperCase() + "/";
	}

	public static String getCSS_PATH() {
		return ROOT_FULL_URL + "CSS/" + CmFunction.getLanguage().toUpperCase() + "/";
		//return CSS_PATH;
	}

	public static String getFLASH_PATH() {
		return FLASH_PATH;
	}

	public static String getSCRIPT_PATH() {
		return SCRIPT_PATH;
	}

	public static String getEDITOR_PATH() {
		return EDITOR_PATH;
	}

	public static String getCHARTS_PATH() {
		return CHARTS_PATH;
	}

	public static String getUPLOAD_PATH() {
		return UPLOAD_PATH;
	}

	public static String getUPLOAD_FILE_PATH() {
		return UPLOAD_FILE_PATH;
	}

	public static String getUPLOAD_FILE_TEMP_PATH() {
		return UPLOAD_FILE_TEMP_PATH;
	}

	public static String getUPLOAD_IMAGE_PATH() {
		return UPLOAD_IMAGE_PATH;
	}

	public static String getUPLOAD_IMAGE_TEMP_PATH() {
		return UPLOAD_IMAGE_TEMP_PATH;
	}

	public static String getCHARSET() {
		return CHARSET;
	}

	public static String getMAIL_FROM_NAME() {
		return MAIL_FROM_NAME;
	}

	public static String getMAIL_FROM_EMAIL() {
		return MAIL_FROM_EMAIL;
	}

	public static String getUPLOAD_USERIMAGE_FILE_PATH() {
		return UPLOAD_USERIMAGE_FILE_PATH;
	}

	public static String UPLOAD_IMG_PATH() {
		return UPLOAD_IMG_PATH;
	}
	
	public static String getTEST_RECEIVED_EMAIL() {
		return TEST_RECEIVED_EMAIL;
	}
	public static String getPDF_IMG_PATH() {
		return PDF_IMG_PATH;
	}
	public static String getUPLOAD_PDF_IMAGE_PATH() {
		return UPLOAD_PDF_IMAGE_PATH;
	}
	public static String getMAIN_IMAGE_UPLOAD_PATH() {
		return MAIN_IMAGE_UPLOAD_PATH;
	}
	
	public static String getREAL_SERVER_YN() {
		return REAL_SERVER_YN;
	}

	public static String getTEST_ADAUTH_DOMAIN() {
		return TEST_ADAUTH_DOMAIN;
	}

	public static String getADAUTH_DOMAIN() {
		return ADAUTH_DOMAIN;
	}

	public static String getADAUTH_ADMIN() {
		return ADAUTH_ADMIN;
	}

	public static String getADAUTH_PWD() {
		return ADAUTH_PWD;
	}

	public static void setCSS_PATH(String cSS_PATH) {
		CSS_PATH = cSS_PATH;
	}

	public static void setIMG_PATH(String iMG_PATH) {
		IMG_PATH = iMG_PATH;
	}
}
