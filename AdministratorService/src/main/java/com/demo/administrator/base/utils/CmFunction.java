package com.demo.administrator.base.utils;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.channels.FileChannel;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.SimpleTimeZone;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.ImageIcon;

import org.apache.ibatis.io.Resources;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.demo.administrator.base.object.CmMap;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class CmFunction {
	private static final int	MAX_FRACTION_FIELD	= 64;
	private static final double	DEFAULT_CHANGE_UNIT	= 1000000.0d;

	// 1881년 ~ 2050년 테이블
	private static final String[]	LTBL
			= {"1212122322121", "1212121221220", "1121121222120", "2112132122122", "2112112121220",
				"2121211212120", "2212321121212", "2122121121210", "2122121212120", "1232122121212",
				"1212121221220", "1121123221222", "1121121212220", "1212112121220", "2121231212121",
				"2221211212120", "1221212121210", "2123221212121", "2121212212120", "1211212232212",
				"1211212122210", "2121121212220", "1212132112212", "2212112112210", "2212211212120",
				"1221412121212", "1212122121210", "2112212122120", "1231212122212", "1211212122210",
				"2121123122122", "2121121122120", "2212112112120", "2212231212112", "2122121212120",
				"1212122121210", "2132122122121", "2112121222120", "1211212322122", "1211211221220",
				"2121121121220", "2122132112122", "1221212121120", "2121221212110", "2122321221212",
				"1121212212210", "2112121221220", "1231211221222", "1211211212220", "1221123121221",
				"2221121121210", "2221212112120", "1221241212112", "1212212212120", "1121212212210",
				"2114121212221", "2112112122210", "2211211412212", "2211211212120", "2212121121210",
				"2212214112121", "2122122121120", "1212122122120", "1121412122122", "1121121222120",
				"2112112122120", "2231211212122", "2121211212120", "2212121321212", "2122121121210",
				"2122121212120", "1212142121212", "1211221221220", "1121121221220", "2114112121222",
				"1212112121220", "2121211232122", "1221211212120", "1221212121210", "2121223212121",
				"2121212212120", "1211212212210", "2121321212221", "2121121212220", "1212112112210",
				"2223211211221", "2212211212120", "1221212321212", "1212122121210", "2112212122120",
				"1211232122212", "1211212122210", "2121121122210", "2212312112212", "2212112112120",
				"2212121232112", "2122121212110", "2212122121210", "2112124122121", "2112121221220",
				"1211211221220", "2121321122122", "2121121121220", "2122112112322", "1221212112120",
				"1221221212110", "2122123221212", "1121212212210", "2112121221220", "1211231212222",
				"1211211212220", "1221121121220", "1223212112121", "2221212112120", "1221221232112",
				"1212212122120", "1121212212210", "2112132212221", "2112112122210", "2211211212210",
				"2221321121212", "2212121121210", "2212212112120", "1232212122112", "1212122122120",
				"1121212322122", "1121121222120", "2112112122120", "2211231212122", "2121211212120",
				"2122121121210", "2124212112121", "2122121212120", "1212121223212", "1211212221220",
				"1121121221220", "2112132121222", "1212112121220", "2121211212120", "2122321121212",
				"1221212121210", "2121221212120", "1232121221212", "1211212212210", "2121123212221",
				"2121121212220", "1212112112220", "1221231211221", "2212211211220", "1212212121210",
				"2123212212121", "2112122122120", "1211212322212", "1211212122210", "2121121122120",
				"2212114112122", "2212112112120", "2212121211210", "2212232121211", "2122122121210",
				"2112122122120", "1231212122212", "1211211221220", "2121121321222", "2121121121220",
				"2122112112120", "2122141211212", "1221221212110", "2121221221210", "2114121221221"};

	private static final String[]	YUK
			= {"갑", "을", "병", "정", "무", "기", "경", "신", "임", "계"};

	private static final String[]	GAP
			= {"자", "축", "인", "묘", "진", "사", "오", "미", "신", "유", "술", "해"};

	private static final String[]	DDI
			= {"쥐", "소", "호랑이", "토끼", "용", "뱀", "말", "양", "원숭이", "닭", "개", "돼지"};

	private static final String[]	WEEK
			= {"일", "월", "화", "수", "목", "금", "토"};
	
	private static final String[]	MONTH
			= {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

	public static String getStringValue(Object i_oSource) {
		if ((null == i_oSource)) {
			return "";
		}

		return getStringValue(i_oSource.toString());
	}

	public static String getStringValue(String i_sSource) {
		if ((null == i_sSource) || "".equals(i_sSource)) {
			return "";
		} else if ((null == i_sSource.trim()) || "".equals(i_sSource.trim())) {
			return "";
		}

		return i_sSource.trim();
	}

	// String => int 로 변환
	public static int getIntValue(String i_sSource) {
		int	iResult = 0;
		
		if (i_sSource != null && !i_sSource.equals("") )
		{
			i_sSource	= getOnlyNumber(i_sSource);
			
			if ( !i_sSource.equals("") )
			{
				iResult		= Integer.parseInt(i_sSource);
			}
		}
		return iResult;
	}
	
	// String => int 로 변환
	public static int getIntValue2(String i_sSource) {
		int	iResult = -1;
		
		if (i_sSource != null && !i_sSource.equals("") )
		{
			i_sSource	= getOnlyNumber(i_sSource);
			
			if ( !i_sSource.equals("") )
			{
				iResult		= Integer.parseInt(i_sSource);
			}
		}
		return iResult;
	}
	
	// String => long 로 변환
	public static long getLongValue(String i_sSource) {
		long	iResult = 0;
		
		if (i_sSource != null && !i_sSource.equals("") )
		{
			i_sSource	= getOnlyNumber(i_sSource);
			
			if ( !i_sSource.equals("") )
			{
				iResult		= Long.parseLong(i_sSource);
			}
		}
		return iResult;
	}
	
	public static String getOnlyNumber(String i_sSource) {
		String sResult	= "";
		
		if (i_sSource != null)
		{
			int len		= i_sSource.length();
			
			for (int i = 0; i < len; i++)
			{
				char	c	= i_sSource.charAt(i);
				
				if (c >= '0' && c <= '9') {
					sResult += c;
				}				
			}
		}
		
		return sResult;
	}
	
	public static String getOnlyDouble(String i_sSource) {
		StringBuffer 	sb	= new StringBuffer();
		boolean isPoint	= true;
		
		if (i_sSource != null)
		{
			int len		= i_sSource.length();
			
			for (int i = 0; i < len; i++)
			{
				char	c	= i_sSource.charAt(i);
				
				if (c >= '0' && c <= '9' || (isPoint && c == '.')) {
					sb.append(c);
					
					if (c == '.') {
						isPoint	= false;
					}
				}
			}
		}
		return sb.toString();
	}

	// 등록일시 [YYYYMMDDHHMMSS]
	public static String getRegDate14() {
		return getRegDate8() + getRegTime();
	}

	// 현재 년월일 [YYYYMMDD]
	public static String getRegDate8() {
		GregorianCalendar	calendar	= new GregorianCalendar();

		String	sYear	= Integer.toString(calendar.get(Calendar.YEAR));
		String	sMonth	= Integer.toString(calendar.get(Calendar.MONTH) + 1);
		String	sDate	= Integer.toString(calendar.get(Calendar.DATE));

		if (sMonth.length() < 2)	sMonth	= "0" + sMonth;
		if (sDate.length()  < 2)	sDate	= "0" + sDate;

		return sYear + sMonth + sDate;	
	}
	
	// 현재 년월일 [YYYYMMDD]
	public static String getNowRegDate8( String chr ) {
		GregorianCalendar	calendar	= new GregorianCalendar();

		String	sYear	= Integer.toString(calendar.get(Calendar.YEAR));
		String	sMonth	= Integer.toString(calendar.get(Calendar.MONTH) + 1);
		String	sDate	= Integer.toString(calendar.get(Calendar.DATE));

		if (sMonth.length() < 2)	sMonth	= "0" + sMonth;
		if (sDate.length()  < 2)	sDate	= "0" + sDate;
		
		return sYear+ chr + sMonth + chr + sDate;	 
	}
	// 현재년 1월 1일 [YYYYMMDD]
	public static String getNowYear1Month1Day() {
		GregorianCalendar	calendar	= new GregorianCalendar();
		
		String	sYear	= Integer.toString(calendar.get(Calendar.YEAR));
		String	sMonth	= Integer.toString(1);
		String	sDate	= Integer.toString(1);
		
		if (sMonth.length() < 2)	sMonth	= "0" + sMonth;
		if (sDate.length()  < 2)	sDate	= "0" + sDate;
		
		return sYear+ sMonth + sDate;	 
	}
	
	// chr : YEAR 일경우 현재 년도
	// chr : MONTH 일경우 월 ( 예:02)
	// chr : DATE 일 경우 일자 
	public static String getNowDate( String chr ) {
		GregorianCalendar	calendar	= new GregorianCalendar();
		
		String	sYear	= Integer.toString(calendar.get(Calendar.YEAR));
		String	sMonth	= Integer.toString(calendar.get(Calendar.MONTH) + 1);
		String	sDate	= Integer.toString(calendar.get(Calendar.DATE));
		String  sReturn = "";

		if (sMonth.length() < 2)	sMonth	= "0" + sMonth;
		if (sDate.length()  < 2)	sDate	= "0" + sDate;

		if ("YEAR".equals(chr.toUpperCase())){
			sReturn = sYear;	 
		} else if ("MONTH".equals(chr.toUpperCase())){
			sReturn = sMonth;	
		} else if ("DATE".equals(chr.toUpperCase())){
			sReturn =  sDate;
		}

		return sReturn;
	}
	
	public static String getAgoRegDate8(String chr){
		GregorianCalendar	calendar	= new GregorianCalendar();
		String				sYear	= "";
		String				sMonth	= "";
		String				sDate	= "";

		calendar.add(Calendar.MONTH, -12);
		sYear	= Integer.toString(calendar.get(Calendar.YEAR));

		if ((calendar.get(Calendar.MONTH) + 1) < 10) {
			sMonth	= ("0" + Integer.toString(calendar.get(Calendar.MONTH) + 1));
		} else {
			sMonth	= Integer.toString(calendar.get(Calendar.MONTH) + 1);
		}

		if (calendar.get(Calendar.DATE) < 10) {
			sDate	= ("0" + Integer.toString(calendar.get(Calendar.DATE)));
		} else {
			sDate	= Integer.toString(calendar.get(Calendar.DATE));
		}
		return sYear+ chr + sMonth + chr + sDate;
	}
	
	public static String getmAgoRegDate8(String chr){
		GregorianCalendar	calendar	= new GregorianCalendar();
		String				sYear	= "";
		String				sMonth	= "";
		String				sDate	= "";

		calendar.add(Calendar.MONTH, -1);
		sYear	= Integer.toString(calendar.get(Calendar.YEAR));

		if ((calendar.get(Calendar.MONTH) + 1) < 10) {
			sMonth	= ("0" + Integer.toString(calendar.get(Calendar.MONTH) + 1));
		} else {
			sMonth	= Integer.toString(calendar.get(Calendar.MONTH) + 1);
		}

		if (calendar.get(Calendar.DATE) < 10) {
			sDate	= ("0" + Integer.toString(calendar.get(Calendar.DATE)));
		} else {
			sDate	= Integer.toString(calendar.get(Calendar.DATE));
		}
		return sYear+ chr + sMonth + chr + sDate;
	}
	
	public static String getAgoDate(int year, int month, int day) {
		Calendar calendar = Calendar.getInstance();	 
		
		calendar.add( Calendar.YEAR ,  -(year) );		
		calendar.add( Calendar.MONTH ,  -(month) );		
		calendar.add( Calendar.DATE ,  -(day) );
		
		String	sMonth	= Integer.toString(calendar.get(Calendar.MONTH) + 1);
		String	sDate	= Integer.toString(calendar.get(Calendar.DATE));

		if (sMonth.length() < 2)	sMonth	= "0" + sMonth;
		if (sDate.length()  < 2)	sDate	= "0" + sDate;
		
		return calendar.get(Calendar.YEAR) + sMonth + sDate;		
	}
	
	public static String getAMonthAgo() {
		Calendar calendar = Calendar.getInstance(); 
		calendar.add( Calendar.MONTH ,  -1 ); 
		
		String	sMonth	= Integer.toString(calendar.get(Calendar.MONTH) + 1);
		String	sDate	= Integer.toString(calendar.get(Calendar.DATE));

		if (sMonth.length() < 2)	sMonth	= "0" + sMonth;
		if (sDate.length()  < 2)	sDate	= "0" + sDate;
		
		return calendar.get(Calendar.YEAR) + sMonth + sDate;		
	}
	
	public static String getCurDateAsType(String type){
		GregorianCalendar	calendar	= new GregorianCalendar();
		
		if ( type.equals("year"))
			return Integer.toString(calendar.get(Calendar.YEAR));
		else if ( type.equals("month")) 
			return Integer.toString(calendar.get(Calendar.MONTH) + 1);
		else if ( type.equals("day"))
			return Integer.toString(calendar.get(Calendar.DATE));
		else return "";
	}
	
	public static String getdAgoRegDate8(String chr){
		GregorianCalendar	calendar	= new GregorianCalendar();
		
		calendar.add(Calendar.DATE, -7);
		
		String	sMonth	= Integer.toString(calendar.get(Calendar.MONTH) + 1);
		String	sDate	= Integer.toString(calendar.get(Calendar.DATE));

		if (sMonth.length() < 2)	sMonth	= "0" + sMonth;
		if (sDate.length()  < 2)	sDate	= "0" + sDate;
		
		return calendar.get(Calendar.YEAR)+ chr + sMonth + chr + sDate;
	}

	public static String getdWeekDate8(){
		GregorianCalendar	calendar	= new GregorianCalendar();
		calendar.add(Calendar.DATE, 7);
		
		String	sMonth	= Integer.toString(calendar.get(Calendar.MONTH) + 1);
		String	sDate	= Integer.toString(calendar.get(Calendar.DATE));

		if (sMonth.length() < 2)	sMonth	= "0" + sMonth;
		if (sDate.length()  < 2)	sDate	= "0" + sDate;
		
		return calendar.get(Calendar.YEAR)+ sMonth + sDate;
	}
	
	public static String getdAfterRegDate8(String chr){
		GregorianCalendar	calendar	= new GregorianCalendar();
		calendar.add(Calendar.DATE, 14);
		
		String	sMonth	= Integer.toString(calendar.get(Calendar.MONTH) + 1);
		String	sDate	= Integer.toString(calendar.get(Calendar.DATE));

		if (sMonth.length() < 2)	sMonth	= "0" + sMonth;
		if (sDate.length()  < 2)	sDate	= "0" + sDate;
		
		return calendar.get(Calendar.YEAR)+ sMonth + sDate;
	}

	// 현재 년월 [YYYYMM]
	public static String getRegDate6() {
		GregorianCalendar	calendar	= new GregorianCalendar();

		String	sYear	= Integer.toString(calendar.get(Calendar.YEAR));
		String	sMonth	= Integer.toString(calendar.get(Calendar.MONTH) + 1);

		if (sMonth.length() < 2)	sMonth	= "0" + sMonth;

		return sYear + sMonth;
	}

	// 현재 시분초 [HHMMSS]
	public static String getRegTime() {
		GregorianCalendar	calendar	= new GregorianCalendar();

		String	sHour	= Integer.toString(calendar.get(Calendar.HOUR_OF_DAY));
		String	sMinute	= Integer.toString(calendar.get(Calendar.MINUTE));
		String	sSecond	= Integer.toString(calendar.get(Calendar.SECOND));

		if (sHour.length()	< 2)	sHour	= "0" + sHour;
		if (sMinute.length() < 2)	sMinute	= "0" + sMinute;
		if (sSecond.length() < 2)	sSecond	= "0" + sSecond;

		return sHour + sMinute + sSecond;	
	}
	
	// 년월일 [YYYYMMDD -> DD MM YYYY] ex : 20090712	- > 12 Jul 2009
	public static String getUsDate(String i_sSource)
	{
		String sResult	= "";
		
		if (i_sSource != null && i_sSource.length() >= "YYYYMMDD".length() )
		{
			String	sYear		= i_sSource.substring(0, 4);
			String 	sMonth		= i_sSource.substring(4, 6);
			String 	sDate		= i_sSource.substring(6, 8);
			
			int		iMonth		= getIntValue(sMonth);
			
			if (iMonth > 0 && iMonth < 13 )
			{
				sResult	= sDate + " " + MONTH[iMonth - 1] + " " + sYear;
			}
		}
		return sResult;
	}
	
	// 년월일 [YYYYMMDD -> DD MM YYYY] ex : 20090712	- > 12 Jul 2009
	public static String getUsDate1(String i_sSource)
	{
		String sResult	= "";
		
		if (i_sSource != null && i_sSource.length() >= "YYYYMMDD".length() )
		{
			String	sYear		= i_sSource.substring(0, 4);
			String 	sMonth		= i_sSource.substring(4, 6);
			String 	sDate		= i_sSource.substring(6, 8);
			
			int		iMonth		= getIntValue(sMonth);
			
			if (iMonth > 0 && iMonth < 13 )
			{
				sResult	= sDate + MONTH[iMonth - 1] + sYear;
			}
		}
		return sResult;
	}
	
	public static String getUsDateTime(String i_sSource)
	{
		String sResult	= "";
		
		if (i_sSource != null && i_sSource.length() >= "YYYYMMDDHHMI".length() )
		{
			String	sDate		= getUsDate(i_sSource);
			
			String 	sHour		= i_sSource.substring(8, 10);
			String 	sMinute		= i_sSource.substring(10, 12);
			
			if ( !sDate.equals("") )
			{
				sResult	= sDate + " " + sHour + ":" + sMinute; 
			}
		}
		
		return sResult;
	}
	
	public static String getUsDateSecond(String i_sSource)
	{
		String sResult	= "";
		
		if (i_sSource != null && i_sSource.length() >= "YYYYMMDDHHMISS".length() )
		{
			String	sDate		= getUsDate(i_sSource);
			
			String 	sHour		= i_sSource.substring(8, 10);
			String 	sMinute		= i_sSource.substring(10, 12);
			String 	sSecond		= i_sSource.substring(12, 14);
			
			if ( !sDate.equals("") )
			{
				sResult	= sDate + " " + sHour + ":" + sMinute + ":" + sSecond; 
			}
		}
		return sResult;
	}	

	// 년월일 [YYYYMMDD -> YYYY.MM.DD]
	public static String getPointDate(String i_sSource) {
		if (getStringValue(i_sSource).length() == "YYYYMM".length()) {
			return i_sSource.substring(0, 4)
				+ "." + i_sSource.substring(4, 6);
		}
		else if (getStringValue(i_sSource).length() < "YYYYMMDD".length()) { 
			return i_sSource;
		}

		return i_sSource.substring(0, 4)
					+ "." + i_sSource.substring(4, 6)
					+ "." + i_sSource.substring(6, 8);		
	}
	
	// 시분초 [HHMMSS -> HH:MM:SS]
	public static String getPointTime(String i_sSource) {
		if (getStringValue(i_sSource).length() < "HHMMSS".length()) { 
			return i_sSource;
		}
		
		return i_sSource.substring(0, 2)
		+ ":" + i_sSource.substring(2, 4)
		+ ":" + i_sSource.substring(4, 6);		
	}
	
	// 시분 [HHMM -> HH:MM]
	public static String getPointTime1(String i_sSource) {
		if (getStringValue(i_sSource).length() < "HHMM".length()) { 
			return i_sSource;
		}
		
		return i_sSource.substring(0, 2)
		+ ":" + i_sSource.substring(2, 4);	  
	}
	
	// 시분 [HHMM -> HH:MM]
	public static String getPointTime2(String i_sSource) {
		if (getStringValue(i_sSource).length() < "HHMM".length()) { 
			return i_sSource;
		}
		
		return i_sSource.substring(8, 10)
		+ ":" + i_sSource.substring(10, 12);	  
	}

	public static String getPointDate(String i_sSource, String sDelimeter) {
		if (getStringValue(i_sSource).length() < "YYYYMMDD".length()) { 
			return i_sSource;
		}

		sDelimeter	= getStringValue(sDelimeter);

		return i_sSource.substring(0, 4)
					+ sDelimeter + i_sSource.substring(4, 6)
					+ sDelimeter + i_sSource.substring(6, 8);		
	}

	// 년월일시분초 [YYYYMMDDHHMMSS]
	public static String getPointDtm(String i_sSource) {
		if (getStringValue(i_sSource).length() < "YYYYMMDDHHMMSS".length()) { 
			return i_sSource;
		}

		return i_sSource.substring( 0,  4)
					+ "."		+ i_sSource.substring( 4,  6)
					+ "."		+ i_sSource.substring( 6,  8)
					+ " &nbsp; " + i_sSource.substring( 8, 10)
					+ ":"		+ i_sSource.substring(10, 12)
					+ ":"		+ i_sSource.substring(12, 14);		
	}
	
	// 년월일시분 [YYYYMMDDHHMM]
	public static String getPointDtm1(String i_sSource) {
		if (getStringValue(i_sSource).length() < "YYYYMMDDHHMM".length()) { 
			return i_sSource;
		}

		return i_sSource.substring( 0,  4)
					+ "."			+ i_sSource.substring( 4,  6)
					+ "."			+ i_sSource.substring( 6,  8)
					+ " &nbsp; " 	+ i_sSource.substring( 8, 10)
					+ ":"			+ i_sSource.substring(10, 12);	  
	}
	
	// 년월일
	public static String getDatePatten(String i_sSource, String sChar) {
		if (getStringValue(i_sSource).length() < "YYYYMMDD".length()) { 
			return i_sSource;
		}
		
		if (sChar == null || sChar.equals(""))
			sChar	= "-";
		
		return i_sSource.substring(0, 4)
		+ sChar + i_sSource.substring(4, 6)
		+ sChar + i_sSource.substring(6, 8);		
	}

	public static String getToday() {

		java.util.Date		dateNow		=	Calendar.getInstance(new SimpleTimeZone(0x1ee6280, "KST")).getTime();
		SimpleDateFormat	formatter	=	new	SimpleDateFormat("yyyyMMdd", Locale.getDefault());

		String	toDay	=	formatter.format(dateNow);

		return toDay;
	}
	
	// chart date
	public static String getChartDate(String i_sSource , String sChar) {
		
		if (getStringValue(i_sSource).length() < "YYYYMMDD".length()) { 
			i_sSource	= getRegDate8();
		}
		
		return getDatePatten(i_sSource, sChar);
	}

	// 년월일 [YYYYMM -> YYYY?MM]
	public static String getPointYYYYMM(String i_sSource, String i_sDelimiter) {
		if (getStringValue(i_sSource).length() < "YYYYMM".length()) { 
			return i_sSource;
		}

		return i_sSource.substring(0, 4) + i_sDelimiter + i_sSource.substring(4, 6);		
	}

	// 숫자 세 자리마다 ','를 표시
	public static String setNumComma(int i_iSource) {
		return setNumComma(Integer.toString(i_iSource));
	}

	public static String setNumComma(int i_iSource, int i_iFractionLength) {
		return setNumComma(Integer.toString(i_iSource), i_iFractionLength);
	}

	public static String setNumComma(long i_iSource) {
		return setNumComma(Long.toString(i_iSource));
	}

	public static String setNumComma(long i_iSource, int i_iFractionLength) {
		return setNumComma(Long.toString(i_iSource), i_iFractionLength);
	}

	public static String setNumComma(float i_dSource) {
		return setNumComma(Float.toString(i_dSource));
	}

	public static String setNumComma(float i_dSource, int i_iFractionLength) {
		return setNumComma(Float.toString(i_dSource), i_iFractionLength);
	}

	public static String setNumComma(double i_dSource) {
		return setNumComma(Double.toString(i_dSource));
	}

	public static String setNumComma(double i_dSource, int i_iFractionLength) {
		return setNumComma(Double.toString(i_dSource), i_iFractionLength);
	}

	public static String setNumComma(String i_sSource) {
		if ("".equals(getStringValue(i_sSource))) {
			return "";
		}

		return setNumComma(i_sSource, MAX_FRACTION_FIELD);
	}

	public static String setNumComma(String i_sSource, int i_iFractionLength) {
		String			result	= "";
		DecimalFormat	dFormat	= new DecimalFormat();

		if (MAX_FRACTION_FIELD != i_iFractionLength) {
			dFormat.setMinimumFractionDigits(i_iFractionLength);
			dFormat.setMaximumFractionDigits(i_iFractionLength);
		}

		result	= dFormat.format(Double.parseDouble(i_sSource));

		return result;
	}

	// 숫자 입력시 변환되는 단위를 결정
	public static long getFormatChangeUnit(int i_iSource) {
		return getFormatChangeUnit(i_iSource, DEFAULT_CHANGE_UNIT);
	}

	public static long getFormatChangeUnit(int i_iSource, double i_dChangeUnit) {
		return (long)(i_iSource * i_dChangeUnit);
	}

	public static long getFormatChangeUnit(long i_iSource) {
		return getFormatChangeUnit(i_iSource, DEFAULT_CHANGE_UNIT);
	}

	public static long getFormatChangeUnit(long i_iSource, double i_dChangeUnit) {
		return (long)(i_iSource * i_dChangeUnit);
	}

	public static double getFormatChangeUnit(float i_dSource) {
		return getFormatChangeUnit(i_dSource, DEFAULT_CHANGE_UNIT);
	}

	public static double getFormatChangeUnit(float i_dSource, double i_dChangeUnit) {
		return (i_dSource * i_dChangeUnit);
	}

	public static double getFormatChangeUnit(double i_dSource) {
		return getFormatChangeUnit(i_dSource, DEFAULT_CHANGE_UNIT);
	}

	public static double getFormatChangeUnit(double i_dSource, double i_dChangeUnit) {
		return (i_dSource * i_dChangeUnit);
	}

	public static String getFormatChangeUnit(String i_sSource) {
		return getFormatChangeUnit(i_sSource, DEFAULT_CHANGE_UNIT);
	}

	public static String getFormatChangeUnit(String i_sSource, double i_dChangeUnit) {
		BigDecimal	dFormatUnit		= new BigDecimal(i_dChangeUnit);		// 변환 단위

		if ("".equals(CmFunction.getStringValue(i_sSource))) {
			return CmFunction.getStringValue(i_sSource);
		}

		if (i_dChangeUnit < 0) {
			return CmFunction.getStringValue(i_sSource);
		}

		BigDecimal	dSourceValue	= new BigDecimal(i_sSource);

		dSourceValue	= dSourceValue.multiply(dFormatUnit);

		return dSourceValue.toString();
	}

	// 숫자 출력시 출력되는 단위를 결정
	public static String setFormatChangeUnit(int i_iSource) {
		return setFormatChangeUnit(Integer.toString(i_iSource));
	}

	public static String setFormatChangeUnit(int i_iSource, double i_dChangeUnit) {
		return setFormatChangeUnit(Integer.toString(i_iSource), i_dChangeUnit);
	}

	public static String setFormatChangeUnit(long i_iSource) {
		return setFormatChangeUnit(Long.toString(i_iSource));
	}

	public static String setFormatChangeUnit(long i_iSource, double i_dChangeUnit) {
		return setFormatChangeUnit(Long.toString(i_iSource), i_dChangeUnit);
	}

	public static String setFormatChangeUnit(float i_dSource) {
		return setFormatChangeUnit(Float.toString(i_dSource));
	}

	public static String setFormatChangeUnit(float i_dSource, double i_dChangeUnit) {
		return setFormatChangeUnit(Float.toString(i_dSource), i_dChangeUnit);
	}

	public static String setFormatChangeUnit(double i_dSource) {
		return setFormatChangeUnit(Double.toString(i_dSource));
	}

	public static String setFormatChangeUnit(double i_dSource, double i_dChangeUnit) {
		return setFormatChangeUnit(Double.toString(i_dSource), i_dChangeUnit);
	}

	public static String setFormatChangeUnit(String i_sSource) {
		return setFormatChangeUnit(i_sSource, DEFAULT_CHANGE_UNIT);
	}

	public static String setFormatChangeUnit(String i_sSource, double i_dChangeUnit) {
		BigDecimal	dFormatUnit		= new BigDecimal(i_dChangeUnit);		// 변환 단위

		if ("".equals(i_sSource)) {
			return i_sSource;
		}

		if (i_dChangeUnit < 0) {
			return i_sSource;
		}

		BigDecimal	dSourceValue	= new BigDecimal(i_sSource);
		int			iPointIndex		= i_sSource.indexOf(".");

		if (iPointIndex > -1) {
			iPointIndex	= i_sSource.length() - iPointIndex - 1;
		} else {
			iPointIndex	= 0;
		}

		long	lDigitValue		= (long)Math.floor(dSourceValue.doubleValue() / dFormatUnit.doubleValue());
		long	lPointValue		= 0L;

		dSourceValue	= dSourceValue.subtract(dFormatUnit.multiply(new BigDecimal(lDigitValue)));

		lPointValue		= Math.round(dSourceValue.doubleValue() * Math.pow(10, iPointIndex));

		String	result	= Long.toString(lDigitValue);

		if (lPointValue > 0) {
			result	= result + "." + lPointValue;
		}

		return result;
	}

	// HTML 문자열 그대로 출력
	public static String getHTMLEncode(String i_sSource) {
		String	result	= "";

		if ((null == i_sSource) || "".equals(i_sSource)) {
			return "";
		}

		result	=replace( replace(replace(replace(replace(replace(replace(i_sSource, "<", "&lt;"), ">", "&gt;"), "\"", "&quot;"),"&" ,"＆"),"#","＃"),"'","′"),"%","％");

		return result;
	}
	
	/**
	 * 문자열의 byte 값을 가져온다.
	 * @param i_sSource
	 * @return
	 */
	public static int getStringByte(String i_sSource) {
		int		iResult		= 0;

		if (i_sSource != null && !i_sSource.equals(""))
		{
			try {
				
				char 	cTmp;
				byte[]	bTmp;
				
				for (int i = 0 ; i < i_sSource.length(); i++ )
				{
					cTmp	= i_sSource.charAt(i);
					
					bTmp		= ("" + cTmp).getBytes("UTF-8");
					iResult		+= bTmp.length;
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return iResult;
	}
	
	public static String getByteString(String i_sSource, String i_sLength) {
		return	getByteString(i_sSource, getIntValue(i_sLength));
	}

	// 문자열을 Byte 수 만큼 자르고 "..." 을 붙여 잘린 문자열임을 표시
	public static String getByteString(String i_sSource, int i_iLength) {
		String	result	= "";

		if ((null == i_sSource) || "".equals(i_sSource)) {
			return result;
		}

		if (i_iLength <= 0) {
			return i_sSource;
		}

		try {
			
			char 	cTmp;
			byte[]	bTmp;
			int		nowLength	= 0;
			int		strLemgth	= 0;
			
			for (int i = 0 ; i < i_sSource.length(); i++ )
			{
				cTmp	= i_sSource.charAt(i);
				
				bTmp		= ("" + cTmp).getBytes("UTF-8");
				strLemgth	= bTmp.length;
				
				if (strLemgth == 3)
					nowLength += 2;
				else
					nowLength += strLemgth;
				
				if (nowLength <= i_iLength)
				{
					result	+= cTmp;
				}
				else
				{
					break;
				}
			}
			
			if (i_sSource.length() > result.length())
				result += "...";
			
		} catch (Exception e) {
			result	= i_sSource;
		}

		return result;
	}
	
	public static String getByteString2(String i_sSource, String i_iLength) {
		return getByteString2(i_sSource, Integer.parseInt(i_iLength));
		
	}


	// 문자열을 Byte 수 만큼자른다
	public static String getByteString2(String i_sSource, int i_iLength) {
		String	result	= "";
		
		if ((null == i_sSource) || "".equals(i_sSource)) {
			return result;
		}
		
		if (i_iLength <= 0) {
			return i_sSource;
		}
		
		try {
			
			char 	cTmp;
			byte[]	bTmp;
			int		nowLength	= 0;
			int		strLemgth	= 0;
			
			for (int i = 0 ; i < i_sSource.length(); i++ )
			{
				cTmp	= i_sSource.charAt(i);
				
				bTmp		= ("" + cTmp).getBytes("UTF-8");
				strLemgth	= bTmp.length;
				
				if (strLemgth == 3)
					nowLength += 2;
				else
					nowLength += strLemgth;
				
				if (nowLength <= i_iLength)
				{
					result	+= cTmp;
				}
				else
				{
					break;
				}
			}
		} catch (Exception e) {
			result	= i_sSource;
		}
		
		return result;
	}
	
	// 문자열에서 <br>만 남기고 모든 HTML 태그 삭제
	public static String removeHTML(String i_sHTML) {
		String	result	= "";

		if ((null == i_sHTML) || "".equals(i_sHTML)) {
			return "";
		}

		i_sHTML	= replace(i_sHTML, "<br>", "@#$%^&*");
		i_sHTML	= replace(i_sHTML, "<BR>", "@#$%^&*");
		i_sHTML	= replace(i_sHTML, "<Br>", "@#$%^&*");
		i_sHTML	= replace(i_sHTML, "<bR>", "@#$%^&*");

		result	= i_sHTML.replaceAll("<.+?>", "");

		result	= replace(result, "@#$%^&*", "<br>");

		result	= replace(result, "P {margin-top:2px;margin-bottom:2px;}", "");

		return result;
	}
	
	// 문자열에서 모든 HTML 태그 삭제
	public static String removeAllHTML(String i_sHTML) {
		String	result	= "";
		
		if ((null == i_sHTML) || "".equals(i_sHTML)) {
			return "";
		}
		
		result	= i_sHTML.replaceAll("<.+?>", "");
		
		return result;
	}

	// 문자열 교체 함수
	public static String replace(String i_sOrigin, String i_sPattern, String i_sReplace) { 
		int	sIndex	= 0; 
		int	eIndex	= 0; 

		if ((null == i_sOrigin) || "".equals(i_sOrigin)) {
			return "";
		}

		StringBuffer	result	= new StringBuffer(); 

		while ((eIndex = i_sOrigin.indexOf(i_sPattern, sIndex)) >= 0) { 
			result.append(i_sOrigin.substring(sIndex, eIndex)); 
			result.append(i_sReplace); 

			sIndex	= eIndex + i_sPattern.length(); 
		}

		result.append(i_sOrigin.substring(sIndex)); 

		return result.toString(); 
	}

	// 파일 경로 생성
	public static boolean makeFilePath(String i_sFullPathWithFileName) {
		boolean	result	= true;

		if ((null == i_sFullPathWithFileName) || "".equals(i_sFullPathWithFileName)) {
			result	= false;
			return result;
		}

		String[]	arrFullPath	= i_sFullPathWithFileName.split("/", -1);
		String		filePath	= "";
		File		file		= null;

		if (null != arrFullPath) {
			for (int i = 0; i < arrFullPath.length - 1; i++) {
				filePath	= filePath + arrFullPath[i] + "/";

				file	= new File(filePath);
				if (!file.exists()) {
					result	= file.mkdir();
				}
			}
		}

		return result;
	}

	// 파일 복사
	public static boolean fileCopy(String i_sSourceFilePath, String i_sTargetFilePath) {
		boolean	result	= true;

		FileInputStream 	inputStream 	= null;		 
		FileOutputStream 	outputStream 	= null;
		File				targetfile		= null;
		FileChannel 		fcin			= null;
		FileChannel 		fcout			= null;
		long 				size			= 0l;	

		makeFilePath(i_sTargetFilePath);
		
		try {
			targetfile		= new File(i_sTargetFilePath);
			
			if (targetfile.exists())
			{
				fileDelete(i_sTargetFilePath);
			}
			
			inputStream 	= new FileInputStream(i_sSourceFilePath); 
			outputStream 	= new FileOutputStream(i_sTargetFilePath);
			fcin			= inputStream.getChannel();
			fcout			= outputStream.getChannel();
			size			= fcin.size();
			
			fcin.transferTo(0, size, fcout);
			fcout.close();
			fcin.close();
			outputStream.close();
			inputStream.close();
			
		} catch (Exception e) {
			result		= false;
			e.printStackTrace();
		} 

		return result;
	}
	
	// 파일 삭제
	public static boolean fileDelete(String i_sSourceFilePath)
	{
		boolean	result	= true;
		
		File file	= new File( i_sSourceFilePath );
		
		if (file.exists()) 
		{
			try {
				file.delete();
			} catch (Exception e) {
				result		= false;
				System.out.println("### CmFunction error ###");
				System.out.println("### CmFunction.fileDelete( " + i_sSourceFilePath + " ) ###");
				System.out.println("");
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public static boolean fileMove(String i_sSourceFilePath, String i_sTargetFilePath) {
		boolean	result	= true;
		
		result		= fileCopy(i_sSourceFilePath, i_sTargetFilePath);
		
		if (result)
		{
			// result		= fileDelete(i_sSourceFilePath);
		}
		return result;
	}

	// Thumbnail 이미지 만들기
	public static boolean createThumbnail(String i_sSourceFile, String i_sTargetFile, int i_iTargetWidth) {
		return createThumbnail(i_sSourceFile, i_sTargetFile, i_iTargetWidth, 0);
	}

	// Thumbnail 이미지 만들기
	public static boolean createThumbnail(String i_sSourceFile, String i_sTargetFile,
										  int i_iTargetWidth, int i_iTargetHeight) {
		boolean	result	= true;

		Image				imgSource	= null;
		Image				imgTarget	= null;

		PixelGrabber		grabber		= null;
		BufferedImage		buffer		= null;

		FileOutputStream	fos			= null;
		JPEGImageEncoder	jpeg		= null;
		JPEGEncodeParam		param		= null;

		try {
			imgSource	= new ImageIcon(i_sSourceFile).getImage();

			int	iSourceWidth	= imgSource.getWidth(null);
			int	iSourceHeight	= imgSource.getHeight(null);

			int	iTargetWidth	= i_iTargetWidth;
			int	iTargetHeight	= 0;

			if (0 == i_iTargetHeight) {
				iTargetHeight	= (int)(((long)i_iTargetWidth * iSourceHeight) / iSourceWidth);
			} else {
				iTargetHeight	= i_iTargetHeight;
			}

			imgTarget	= imgSource.getScaledInstance(iTargetWidth, iTargetHeight, Image.SCALE_SMOOTH);

			int[]	iPixels		= new int[iTargetWidth * iTargetHeight];

			grabber	= new PixelGrabber(imgTarget, 0, 0, iTargetWidth, iTargetHeight, iPixels, 0, iTargetWidth);
			grabber.grabPixels();

			buffer	= new BufferedImage(iTargetWidth, iTargetHeight, BufferedImage.TYPE_INT_RGB);
			buffer.setRGB(0, 0, iTargetWidth, iTargetHeight, iPixels, 0, iTargetWidth);

			fos	= new FileOutputStream(i_sTargetFile);

			jpeg	= JPEGCodec.createJPEGEncoder(fos);

			param	= jpeg.getDefaultJPEGEncodeParam(buffer);
			param.setQuality(1.0f, false);

			jpeg.encode(buffer, param);
		} catch (Exception e) {
			result	= false;
		} finally {
			try {
				if (null != fos) {
					fos.close();
				}
			} catch (Exception e) {
				result	= false;
			}
		}

		return result;
	}
	
	// Thumbnail 이미지 만들기
	public static boolean createThumbnail2(String i_sSourceFile, String i_sTargetFile, int i_iTargetWidth, int i_iTargetHeight) {
		boolean	result	= true;
		
		Image				imgSource	= null;
		Image				imgTarget	= null;
		
		PixelGrabber		grabber		= null;
		BufferedImage		buffer		= null;
		
		FileOutputStream	fos			= null;
		JPEGImageEncoder	jpeg		= null;
		JPEGEncodeParam		param		= null;
		
		try {
			imgSource	= new ImageIcon(i_sSourceFile).getImage();
			
			int	iSourceWidth	= imgSource.getWidth(null);
			int	iSourceHeight	= imgSource.getHeight(null);
			
			int	iTargetWidth	= 0;
			int	iTargetHeight	= 0;
			
			if ( i_iTargetWidth == 0 &&  i_iTargetHeight == 0 )
			{
				iTargetWidth	= iSourceWidth;
				iTargetHeight	= iSourceHeight;
			}
			else
			{
				iTargetWidth	= i_iTargetWidth;
				iTargetHeight	= i_iTargetHeight;
				
				if (i_iTargetWidth == 0 && i_iTargetHeight > 0) 
				{
					iTargetWidth	= (int)(((long)i_iTargetHeight * iSourceWidth) / iSourceHeight);
				} 
				
				if (i_iTargetWidth > 0 && i_iTargetHeight == 0) 
				{
					iTargetHeight	= (int)(((long)i_iTargetWidth * iSourceHeight) / iSourceWidth);
				}
//				System.out.println(iTargetWidth + "|" + iTargetHeight);
			}
			
			imgTarget	= imgSource.getScaledInstance(iTargetWidth, iTargetHeight, Image.SCALE_SMOOTH);
			
			int[]	iPixels		= new int[iTargetWidth * iTargetHeight];
			
			grabber	= new PixelGrabber(imgTarget, 0, 0, iTargetWidth, iTargetHeight, iPixels, 0, iTargetWidth);
			grabber.grabPixels();
			
			buffer	= new BufferedImage(iTargetWidth, iTargetHeight, BufferedImage.TYPE_INT_RGB);
			buffer.setRGB(0, 0, iTargetWidth, iTargetHeight, iPixels, 0, iTargetWidth);
			
			fos	= new FileOutputStream(i_sTargetFile);
			
			jpeg	= JPEGCodec.createJPEGEncoder(fos);
			
			param	= jpeg.getDefaultJPEGEncodeParam(buffer);
			param.setQuality(1.0f, false);
			
			jpeg.encode(buffer, param);
		} catch (Exception e) {
			result	= false;
		} finally {
			try {
				if (null != fos) {
					fos.close();
				}
			} catch (Exception e) {
				result	= false;
			}
		}
		
		return result;
	}

	// 음력 -> 양력변환
	public static String convertLunar2Solar(String i_sDate) {
		return convertLunar2Solar(i_sDate, true);
	}

	// 음력 -> 양력변환
	public static String convertLunar2Solar(String i_sDate, boolean i_iIsYun) {
		if (CmFunction.getStringValue(i_sDate).length() < 8) {
			return "";
		}

		int	iYear	= Integer.parseInt(i_sDate.substring(0, 4));
		int	iMonth	= Integer.parseInt(i_sDate.substring(4, 6));
		int	iDate	= Integer.parseInt(i_sDate.substring(6, 8));

		return convertLunar2Solar(iYear, iMonth, iDate, i_iIsYun);
	}

	// 음력 -> 양력변환
	public static String convertLunar2Solar(int i_iYear, int i_iMonth, int i_iDate) {
		return convertLunar2Solar(i_iYear, i_iMonth, i_iDate, true);
	}

	// 음력 -> 양력변환
	public static String convertLunar2Solar(int i_iYear, int i_iMonth, int i_iDate, boolean i_iIsYun) {
		String	result	= "";
		int		sYear	= 0;
		int		sMonth	= 0;
		int		sDate	= 0;

		int[]	LDAY = {31, 0, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

		if ((i_iYear < 1881) && (i_iYear > 2050)) {
			return result;
		}

		if ((i_iMonth + 1) > 13) {
			return result;
		}

		int	iYearIndex		= i_iYear - 1881;
		int	iMonthIndex1	= 0;
		int	iMonthIndex2	= 0;
		int	iDayCount		= 0;
		int	iDateLeap		= 0;

		boolean	iIsYun		= false;

		if ("0".equals(LTBL[iYearIndex].substring(LTBL[iYearIndex].length() - 1))) {
			iIsYun	= false;
		} else {
			if (Integer.parseInt(LTBL[iYearIndex].substring(i_iMonth, (i_iMonth + 1))) > 2) {
				iIsYun	= true;
			} else {
				iIsYun	= false;
			}
		}

		iIsYun	&= i_iIsYun;

		iYearIndex	= -1;
		iDayCount	= 0;

		if ((i_iYear >= 1881) && (i_iYear < 2050)) {
			iYearIndex	= i_iYear - 1881;

			for (int i = 0; i < iYearIndex; i++) {
				for (int j = 0; j < LTBL[i].length(); j++) {
					iDayCount	+= Integer.parseInt(LTBL[i].substring(j, j + 1));
				}

				if ("0".equals(LTBL[i].substring(LTBL[i].length() - 1))) {
					iDayCount	+= 336;
				} else {
					iDayCount	+= 362;
				}
			}
		} else {
			result	= "0";
		}

		iYearIndex++;
		iMonthIndex1	= i_iMonth - 1;
		iMonthIndex2	= -1;

		do {
			iMonthIndex2++;

			if (Integer.parseInt(LTBL[iYearIndex].substring(iMonthIndex2, iMonthIndex2 + 1)) > 2) {
				iDayCount	= iDayCount + 26 + Integer.parseInt(LTBL[iYearIndex].substring(iMonthIndex2, iMonthIndex2 + 1));
				iMonthIndex1++;
			} else {
				if (iMonthIndex2 == iMonthIndex1) {
					if (iIsYun) {
						iDayCount	= iDayCount + 28 + Integer.parseInt(LTBL[iYearIndex].substring(iMonthIndex2, iMonthIndex2 + 1));
					}

					break;
				} else {
					iDayCount	= iDayCount + 28 + Integer.parseInt(LTBL[iYearIndex].substring(iMonthIndex2, iMonthIndex2 + 1));
				}
			}
		} while (true);

		iDayCount	= iDayCount + i_iDate + 29;
		iYearIndex	= 1880;

		do {
			iYearIndex++;
			if (((iYearIndex % 400) == 0) || ((iYearIndex % 100) != 0) && ((iYearIndex % 4) == 0)) {
				iDateLeap	= 1;
			} else {
				iDateLeap	= 0;
			}

			iMonthIndex2	= 365 + iDateLeap;

			if (iDayCount < iMonthIndex2) {
				break;
			}

			iDayCount	-= iMonthIndex2;
		} while (true);

		sYear	= iYearIndex;
		LDAY[1]	= iMonthIndex2 - 337;

		iYearIndex	= 0;
		do {
			iYearIndex++;
			if (iDayCount <= LDAY[iYearIndex - 1]) {
				break;
			}

			iDayCount	-= LDAY[iYearIndex - 1];
		} while (true);

		sMonth	= iYearIndex;
		sDate	= iDayCount;

		iDayCount	= (sYear - 1) * 365 + (sYear - 1) / 4 - (sYear - 1) / 100 + (sYear - 1) / 400;

		if (((sYear % 400) == 0) || ((sYear % 100) != 0) && ((sYear % 4) == 0)) {
			iDateLeap	= 1;
		} else {
			iDateLeap	= 0;
		}

		LDAY[1]	= 28 + iDateLeap;

		for (int i = 0; i < sMonth - 1; i++) {
			iDayCount	+= LDAY[i];
		}

		iDayCount	+= sDate;

		result	= Integer.toString(sYear)
					+ (((sMonth < 10) ? "0" : "") + Integer.toString(sMonth))
					+ (((sDate  < 10) ? "0" : "") + Integer.toString(sDate))
					+ "/" + WEEK[(iDayCount % 7)] + "요일";
System.out.println(result + "|" + iIsYun);
		return result;
	}

	// 양력 -> 음력변환
	public static String convertSolar2Lunar(String i_sDate) {
		if (CmFunction.getStringValue(i_sDate).length() < 8) {
			return "";
		}

		int	iYear	= Integer.parseInt(i_sDate.substring(0, 4));
		int	iMonth	= Integer.parseInt(i_sDate.substring(4, 6));
		int	iDate	= Integer.parseInt(i_sDate.substring(6, 8));

		return convertSolar2Lunar(iYear, iMonth, iDate);
	}

	// 양력 -> 음력변환
	public static String convertSolar2Lunar(int i_iYear, int i_iMonth, int i_iDate) {
		String	result	= "";
		int		sYear	= 0;
		int		sMonth	= 0;
		int		sDate	= 0;

		int[]	LDAY = {31, 0, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

		int		i;

		if ((i_iYear < 1881) && (i_iYear > 2050)) {
			return result;
		}

		if ((i_iMonth + 1) > 13) {
			return result;
		}

		int	iYearIndex		= i_iYear - 1881;
		int	iMonthIndex1	= 0;
		int	iMonthIndex2	= 0;

		boolean	iIsYun		= false;

		if ("0".equals(LTBL[iYearIndex].substring(LTBL[iYearIndex].length() - 1))) {
			iIsYun	= false;
		} else {
			if (Integer.parseInt(LTBL[iYearIndex].substring(i_iMonth, (i_iMonth + 1))) > 2) {
				iIsYun	= true;
			} else {
				iIsYun	= false;
			}
		}

		int[]	iDayCount	= new int[169];

		for (i = 0; i < 169; i++) {
			iDayCount[i]	= 0;

			for (int j = 0; j < LTBL[i].length(); j++) {
				switch (Integer.parseInt(LTBL[i].substring(j, j + 1))) {
					case	1 :
					case	3 :
						iDayCount[i]	+= 29;
						break;
					case	2 :
					case	4 :
						iDayCount[i]	+= 30;
						break;
				}
			}
		}

		int	iTotalDate1	= 1880 * 365 + 1880 / 4 - 1880 / 100 + 1880 / 400 + 30;
		int	iTotalDate2	= (i_iYear - 1) * 365 + (i_iYear - 1) / 4 - (i_iYear - 1) / 100 + (i_iYear - 1) / 400;

		if ((i_iYear % 400 == 0) || (i_iYear % 100 != 0) && (i_iYear % 4 == 0)) {
			LDAY[1]	= 29;
		} else {
			LDAY[1]	= 28;
		}

		for (i = 0; i < i_iMonth - 1; i++) {
			iTotalDate2	+= LDAY[i];
		}

		iTotalDate2	+= i_iDate;

		iTotalDate1	= iTotalDate2 - iTotalDate1 + 1;
		iTotalDate2	= iDayCount[0];

		for (i = 0; i < iDayCount.length - 1; i++) {
			if (iTotalDate1 <= iTotalDate2) {
				break;
			}

			iTotalDate2	+= iDayCount[i + 1];
		}

		sYear	= i + 1881;
		iTotalDate2	-= iDayCount[i];
		iTotalDate1	-= iTotalDate2;

		if ("0".equals(LTBL[i].substring(LTBL[i].length() - 1))) {
			iMonthIndex1	= 11;
		} else {
			iMonthIndex1	= 12;
		}

		iMonthIndex2	= 0;

		for (int j = 0; j <= iMonthIndex1; j++) {
			if (Integer.parseInt(LTBL[i].substring(j, j + 1)) <= 2) {
				iMonthIndex2++;
				iYearIndex	= Integer.parseInt(LTBL[i].substring(j, j + 1)) + 28;
				iIsYun		= false;
			} else {
				iYearIndex	= Integer.parseInt(LTBL[i].substring(j, j + 1)) + 26;
				iIsYun		= true;
			}

			if (iTotalDate1 <= iYearIndex) {
				break;
			}

			iTotalDate1	-= iYearIndex;
		}

		sMonth	= iMonthIndex2;
		sDate	= iTotalDate1;

		result	= Integer.toString(sYear)
					+ (((sMonth < 10) ? "0" : "") + Integer.toString(sMonth))
					+ (((sDate  < 10) ? "0" : "") + Integer.toString(sDate))
					+ "/" + (iIsYun ? "윤달" : "평달")
					+ "/" + YUK[((sYear + 6) % 10)] + GAP[(sYear + 8) % 12] + "년"
					+ "/" + DDI[(sYear + 8) % 12];

		return result;
	}
	
	// 다국어
	public static ResourceBundle getBundle(String str, HttpServletRequest request) {
		
		ResourceBundle bundle 		= null;
		String	tmpStr			 	= request.getHeader("Accept-Language");
		String	sAcceptLanguage 	= "en";
		
		if (tmpStr != null && tmpStr.length() > 1)
		{
			sAcceptLanguage 	= tmpStr.substring(0, 2).toLowerCase();
		}
		java.util.Locale locale 	= new java.util.Locale(sAcceptLanguage);

		try {
			bundle = ResourceBundle.getBundle(str, locale);
		} catch (Exception e) { 
			bundle = ResourceBundle.getBundle(str, Locale.ENGLISH);
		}
		
		return bundle;
	}
	
	public static String getBundleString(String str) {
		
		String baseName = getBaseName(str);
		
		ResourceBundle bundle = CmFunction.getBundle(baseName, String.valueOf(CmFunction.getCurrentRequest().getSession().getAttribute("language")));
		
		return CmFunction.getBundleString(str, bundle);
	}
	
	public static String getBaseName(String str) {
		
		String baseName = "message.pms.";
		
		String str3 = str.replace(".", "_");
		
		String[] strArray = str3.split("_");
		
		str3 = str3.replace("_"+strArray[strArray.length - 1], "");
		
		return baseName+str3;
	}
	

	// 다국어
	public static ResourceBundle getBundle(String str, String lenguage) {
		
		ResourceBundle bundle 		= null;
		java.util.Locale locale 	= new java.util.Locale(lenguage.toLowerCase(), "");
		
		try {
			bundle = ResourceBundle.getBundle(str, locale);
		} catch (Exception e) { 
			bundle = ResourceBundle.getBundle(str, Locale.ENGLISH);
		}
		
		return bundle;
	}
	
	
	// 다국어
	public static String getBundleString(String str, ResourceBundle bundle) {
		
		String returnStr	= "";
		
		try {
			
			returnStr = bundle.getString(str).replaceAll("\r\n", "<br/>");
			
		} catch (Exception e) {
			
			returnStr		= "<font color='red'>Text error</font>";
			
			System.out.println("#######################");
			System.out.println("# str	: " + str);
			System.out.println("#######################");
		}
		
		return  returnStr;
		
	}
	
	public static String getBundleString2(String str, ResourceBundle bundle) {
		
		String returnStr	= "";
		
		try {
			
			returnStr = bundle.getString(str);
			
		} catch (Exception e) {
			
			returnStr		= "<font color='red'>Text error</font>";
			
			System.out.println("#######################");
			System.out.println("# str	: " + str);
			System.out.println("#######################");
		}
		
		return  returnStr;
		
	}

	// 랜덤 영문 숫자 조합 ( 영문보다 숫자가 나올 확률이 2배 높다. )
	public static String getRandomString(int strLength) {
		
		String 		returnStr	= "";
		String[] 	strArr		= new String[]{"0123456789", "0123456789", "abcdefghijklmnopqrstuvwxyz"};
		int			len			= strArr.length;
		Random		random		= new Random();
		
		for (int i = 0; i < strLength; i++)
		{
			int iRan1 	= random.nextInt(len);
			int iRan2	= random.nextInt(strArr[iRan1].length());
			
			returnStr	+= strArr[iRan1].substring(iRan2, iRan2+1);
		}
		
		return returnStr;
	}
	
	// cookie
	public static String getCookie(String cookieName) {
		
		String 		resultStr	= "";
		Cookie[] 	cookies 	= getCurrentRequest().getCookies();
		
		for( int i = 0; i < cookies.length; i++ )
		{
			Cookie thisCookie =  cookies[i];

			if ( thisCookie.getName().equals(cookieName) )
			{
				resultStr	= thisCookie.getValue();
				break;
			}
		}		
		return resultStr;
	}
	
	/**
	 * Cookie 저장
	 * @param response
	 * @param cookieName
	 * @param cookieValue
	 * @param minute
	 */
	public static void setCookie(HttpServletResponse response, String cookieName, String cookieValue, int day) {
		Cookie	cookie		= new Cookie(cookieName, cookieValue);
		if (day > 0) {
			cookie.setMaxAge(day * 60 * 60 * 24);
		}
		else {
			cookie.setMaxAge(day);
		}
		response.addCookie(cookie);
	}
	
	// LPAD, RPAD
	public static String getStringPad(String type, String str, int len, char spaceChr) {
		String	sResult		= "";
		int		strLen		= 0;
		
		if (str == null)	str		= "";
		
		strLen	= str.length();
		
		if (strLen < len)
		{
			for (int i = 0; i < len - strLen ; i++)
			{
				sResult	+= spaceChr;
			}
			
			if ("LPAD".equals(type.toUpperCase()) )
			{
				sResult	+= str;
			}
			else if ("RPAD".equals(type.toUpperCase()) )
			{
				sResult	=  str + sResult;
			}
		}
		else
		{
			sResult		= str;
		}
		
		return sResult;
	}

	// 파일경로 가져오기
	public static String getAttachFileUrl(String sFullUrl ) {
		String	sReturn = "";
		
		if (sFullUrl != null && sFullUrl.indexOf("AUTOWINI") > -1 )
		{
			sReturn		= "/" + sFullUrl.substring(sFullUrl.indexOf("AUTOWINI"), sFullUrl.length());
		}
		
		return sReturn;
	}
	
	// 파일경로 가져오기
	public static String getAttachFileUrl(String sAttachId, String sAttachExt, String sAttachPath ) {
		String	sReturn = "";
		
		if (sAttachId != null && sAttachExt != null && sAttachPath != null)
		{
			sReturn		= getAttachFileUrl(sAttachPath + sAttachId + sAttachExt);
		}
		
		return sReturn;
	}
	
	// 텍스트를 * 로 변환  (ex : ("abcdebe" , 2)  = ab*****)
	public static String getStringHidden(String i_sSourcd, int length) {
		String	sReturn	= "";
		
		if (i_sSourcd != null && !i_sSourcd.equals(""))
		{
			int	strLen		= i_sSourcd.length();
			
			for (int i = 0; i < strLen; i++)
			{
				if (i < length)
				{
					sReturn		+= i_sSourcd.charAt(i);
				}
				else
				{
					sReturn		+= "*";
				}
			}
		}
		
		return sReturn;
	}
	
	// e-mail	(ex : ("abcdebe@test.com" , 2)  = ab*****@test.com)
	public static String getEmailHidden(String i_sEmail, String sLength) {
		
		int	length		= 0;
		
		try {
			length	= Integer.parseInt(sLength);
		} catch (Exception e) {
			length	= 2;
		}
	
		return getEmailHidden(i_sEmail, length);
	}
	
	// e-mail	(ex : ("abcdebe@test.com" , 2)  = ab*****@test.com)
	public static String getEmailHidden(String i_sEmail, int length) {
		String	sReturn	= "";
		
		if (i_sEmail != null && !i_sEmail.equals(""))
		{
			if (i_sEmail.indexOf("@") > -1)
			{
				String	emailFirst	= i_sEmail.substring(0, i_sEmail.indexOf("@"));
				String	emailLast	= i_sEmail.substring(i_sEmail.indexOf("@"), i_sEmail.length());
				
				sReturn	= getStringHidden(emailFirst, length) + emailLast;
			}
			else
			{
				sReturn		= getStringHidden(i_sEmail, length);
			}
		}
		return sReturn;
	}
	
	/**
	 * 해당 url의 html을 가져온다.
	 * @param i_sUrl
	 * @return
	 */
	public static String urlToString(String i_sUrl) {
		String	sReturn			= "";
		
		try {
			URL					url			= new URL(i_sUrl);
			HttpURLConnection	conn		= (HttpURLConnection) url.openConnection();
			byte[]				bTmp		= null;
			InputStream 		in			= null;
			
			conn.connect();
			
			in 			= conn.getInputStream();
			bTmp		= CmFunction.fileToByte(in);				// file => byte
			sReturn		= new String(bTmp, CmPathInfo.getCHARSET());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return sReturn;
	}
	
	/**
	 * url 주소의 파일을 byte 로 변환
	 * @param i_sUrl
	 * @return
	 */
	public static byte[] urlToByte(String i_sUrl) {
		
		byte[] 					bResult		= null;
		
		try {
			
			URL					url			= new URL(i_sUrl);
			HttpURLConnection	conn		= (HttpURLConnection) url.openConnection();
			InputStream 		in			= null;
			
			conn.connect();
			bResult		= CmFunction.fileToByte(in);				// file => byte
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return bResult;
	}
	
	// url 주소의 파일을 가져온다.
	public static File urlToFile(String i_sUrl, String i_sTarget )
	{
		File		fResult		= null;
		
		try {
			URL					url			= new URL(i_sUrl);
			HttpURLConnection	conn		= (HttpURLConnection) url.openConnection();
			byte[]				bTmp		= null;
			InputStream 		in			= null;
			
			conn.connect();
			
			in 			= conn.getInputStream();
			bTmp		= CmFunction.fileToByte(in);				// file => byte
			fResult		= CmFunction.byteToFile(bTmp, i_sTarget);	// byte => file 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return fResult;
	}
	
	// file => Byte 로
	public static byte[] fileToByte (InputStream in) throws IOException {
		
		byte[] 					bResult		= null;
		byte[] 					bTmp		= new byte[1024];
		ByteArrayOutputStream 	baos		= new ByteArrayOutputStream();
		
		try 
		{
			int		j;
			while (( j = in.read(bTmp)) != -1)
			{
				baos.write(bTmp, 0, j);
			}
			
			bResult	= baos.toByteArray();
		}
		finally 
		{
			if (in != null)
			{
				try 
				{
					in.close();
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				}
			}
		}
		
		return bResult;
	}
	
	// byte => file
	public static File byteToFile(byte[] b, String i_sTargetFile) {
		
		File					fResult	= null;
		BufferedOutputStream 	bops	= null;
		
		try
		{
			fResult						= new File(i_sTargetFile);
			FileOutputStream	fos		= new FileOutputStream(fResult);
			
			bops	= new BufferedOutputStream(fos);
			bops.write(b);
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally 
		{
			if (bops != null)
			{
				try
				{
					bops.close();
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				}
			}
		}
		
		return fResult;
	}

	// properties 속성값을 읽어 온다.
	public static String getStringProperties(String fileName, String attributeName) {
		String sReturn		= "";
		try {
			Properties	props	= Resources.getResourceAsProperties(fileName);
			
			sReturn		= CmFunction.getStringValue(props.getProperty(attributeName));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return sReturn;
	}
	
	public static String escape(String string){
			StringBuffer sb = new StringBuffer();
			String ncStr = "*+-./0123456789@ABCDEFGHIJKLMNOPQRSTUVWXYZ_abcdefghijklmnopqrstuvwxyz";
			char c;
			
			for(int i=0;i<string.length();i++){
				  c = string.charAt(i);
				  if(c>0x7f){
						 sb.append("%u"); 
						 sb.append(Integer.toHexString((int)c).toUpperCase());
				  }
				  else if(ncStr.indexOf((int)c)==-1){
						 sb.append('%');
						 if(c<=0xf)
								sb.append('0');
						 sb.append(Integer.toHexString((int)c).toUpperCase());
				  }
				  else 
						 sb.append(c);
			}
			
			return sb.toString();
	}
	
	// \n을  --> <br> 로 변경
	public static String getStrChangeBr(String tmpStr) {
		String	sReturn	= "";
		
		if (tmpStr != null && !tmpStr.equals(""))
			sReturn			= tmpStr.replaceAll("\n", "<br/>");
		
		return sReturn;
	}
	
	// 문자열에서 모든 HTML 태그 삭제 하고  \n을  --> <br> 로 변경
	public static String removeHTMLChangeBr(String i_sHTML) {
		String	result	= "";
		
		if ((null == i_sHTML) || "".equals(i_sHTML)) {
			return "";
		}
		
		result	= i_sHTML.replaceAll("<.+?>", "").replaceAll("\n", "<br/>");
		
		result = result.replaceAll("\r", "");
		
		return result;
	}
	
	public static String fnFileNameCheck(String	filePath, String fileName)
	{
		String		sReturn			= "";
		String		tmpFileName		= "";
		String		tmpExt			= "";
		File		file			= null;
		boolean		bLoop			= true;
		int			cnt				= 0;
		
		if (filePath != null && fileName != null)
		{
			if (fileName.indexOf(".") > -1)
			{
				tmpFileName			= fileName.substring(0, fileName.lastIndexOf("."));
				tmpExt				= fileName.substring(fileName.lastIndexOf("."), fileName.length());
			}
			else
			{
				tmpFileName			= fileName;
				tmpExt				= "";
			}
			
			while (bLoop)
			{
				if (cnt == 0)
					file		= new File(filePath + tmpFileName + tmpExt);
				else
					file		= new File(filePath + tmpFileName + "[" + cnt + "]" + tmpExt);
				
				if (file.exists())
				{
					cnt++;
				}
				else
				{
					bLoop		= false;
					
					if (cnt == 0)
						sReturn		= tmpFileName + tmpExt;
					else
						sReturn		= tmpFileName + "[" + cnt + "]" + tmpExt;
				}
			}
		}
		
		return sReturn;
	}
	
	/**
	 * - 가 있는 전화번호 에서 필요한 부분 가져오기
	 * ex ) getPhoneNumber("02-1234-5678", 1) => return "1234"
	 * ex ) getPhoneNumber("02-1234-5678", 2) => return "5678"
	 * @param i_sPhone
	 * @param index
	 * @return
	 */
	public static String cutPhone(String i_sPhone, String sIndex) {
		String 	sReturn		= "";
		int		index		= CmFunction.getIntValue(sIndex);
		
		if (i_sPhone != null && !i_sPhone.equals(""))
		{
			String[] arrPhone		= i_sPhone.split("-");
			
			if (index < arrPhone.length)
				sReturn	= arrPhone[index];
		}
		return sReturn;
	}
	
	/**
	 * Xml Node 값을 가져온다.
	 * @param element
	 * @param tagName
	 * @return
	 */
	public static String getXmlNodeValue(Element element, String tagName) {
		String		sReturn 	= null;
		NodeList	nList		= null;
		Element		elTmp		= null;
		
		nList 		= element.getElementsByTagName(tagName);
		
		if (nList != null)
		{
			elTmp 		= (Element) nList.item(0);
			
			if (elTmp != null && elTmp.getFirstChild() != null)
				sReturn = elTmp.getFirstChild().getNodeValue();
		}
		
		return sReturn;
	}
	
	/**
	 * 값 등록유무 체크
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty (String str) {
		
		if (str != null && !str.equals(""))
			return true;
		else
			return false;
	}
	
	/**
	 * 값 등록 유무 체크
	 * @param str
	 * @return
	 */
	public static boolean isEmpty (String str) {
		
		if (str != null && !str.equals(""))
			return false;
		else
			return true;
	}
	
	public static String getFileSize(long fileSize) {
		String		sReturn		= "";
		
		if (fileSize >= (1 * 1024 * 1024 * 1024))
			sReturn	= setNumComma(Math.ceil((double)(fileSize / 1024 / 1024 / 1024))) + "GB";
		else if (fileSize >= (1 * 1024 * 1024)) 
			sReturn	= setNumComma(Math.ceil((double)(fileSize / 1024 / 1024))) + "MB";
		else if (fileSize >= (1 * 1024)) 
			sReturn	= setNumComma(Math.ceil((double)(fileSize / 1024))) + "KB";
		else if (fileSize > 0 )
			sReturn	= "1KB";
		
		return sReturn;
	}
	
	/**
	 * 첨부파일 웹 위치
	 * @param str
	 * @return
	 */
	public static String getFilePath(String str) {
		String		sReturn		= "";
		String		tmpStr		= "/UploadFile/";
		
		if ( str != null && str.indexOf(tmpStr) > -1 ) 
		{
			sReturn		= str.substring( str.indexOf(tmpStr) + tmpStr.length() );
		}
		else if ( str != null && str.indexOf("\\UploadFile\\") > -1 )
		{
			sReturn		= str.substring( str.indexOf("\\UploadFile\\") + "\\UploadFile\\".length() );
		}
		
		return sReturn;
	}
	
	/**
	 * 첨부파일 웹 위치
	 * @param str
	 * @return
	 */
	public static String getFilePath2(String str) {
		String		sReturn		= "";
		String		tmpStr		= CmPathInfo.getUPLOAD_PATH();
		
		if ( str != null && str.indexOf(tmpStr) > -1 ) 
		{
			sReturn		= str.substring( str.indexOf(tmpStr) + tmpStr.length() );
		}
		else if ( str != null && str.indexOf("\\UploadFile\\") > -1 )
		{
			sReturn		= str.substring( str.indexOf("\\UploadFile\\") + "\\UploadFile\\".length() );
		}
		
		return sReturn;
	}
	
	public static String getDbClobCheck(String str) 
	{
		if (isNotEmpty(str))
		{
			int length		= (int)(CmFunction.getStringByte(str) * 2) / 3;
			
			if (length > 900 && length < 2500)
				return "N";
		}
		return "Y";
	}
	
	/**
	 * Database 등록 값 변경
	 * @param str
	 * @return
	 */
	public static String getDbValChange(String str) {
		
		
		
		String	sReturn		= "null";
		
		if (str != null && !str.equals(""))
		{
			str			= str.replaceAll("'", "''");		//  ' 값 변경
			
			sReturn		= "'" + str + "'";
		}
		
		return sReturn;
	}
	
	public static String getJsSpecilaChaChange(String str) {
		String	sReturn		= "";
		
		if (str != null && !str.equals(""))
		{
			str	= str.replaceAll("\\", "\\\\");
			str	= str.replaceAll("\"", "\\\"");
			str	= str.replaceAll("\n", "\\n");
			
			sReturn		= str;
		}
		
		return sReturn;
	}
	
	
	/**
	 * 잘짜간 차이 구하기
	 * @param sDt
	 * @param eDt
	 * @return
	 */
	public static String getDateGap( String sDt, String eDt ) {
		
		String		sReturn			= "";
		Calendar	startDt			= Calendar.getInstance();		
		Calendar	endDt			= Calendar.getInstance();
		long		gap;
		
		if (isNotEmpty(sDt) && isNotEmpty(eDt) )
		{
			DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
			
			try {
				
				startDt.setTime(dateFormat.parse(sDt));
				endDt.setTime(dateFormat.parse(eDt));
				
				gap		= (endDt.getTimeInMillis() - startDt.getTimeInMillis()) / (24 * 60 * 60 * 1000);
				
				if (gap >= 0)
					sReturn		= "" + (gap + 1);
				else
					sReturn		= "" + (gap);

			} catch (Exception e) {
				sReturn		= "";
			}
		}
		
		return sReturn;
	}

	/**
	 * tree 이미지
	 * @param level
	 * @param cnt
	 * @param nowCnt
	 * @param childCnt
	 * @return
	 */
	public static String getTreeImg ( String[] treeLine, String level, String cnt, String nowCnt, String childCnt)
	{
		StringBuffer	sb			= new StringBuffer();
		int 			iLevel		= getIntValue(level);
		int 			iCnt		= getIntValue(cnt);
		int 			iNowCnt		= getIntValue(nowCnt);
		int 			iChildCnt	= getIntValue(childCnt);
		
		for (int i = 0 ; i < iLevel; i++)
		{
			if ( "Y".equals(treeLine[i]) )
			{
				sb.append("<img src=\"").append(CmPathInfo.getIMG_PATH()).append("TREE/tree_line.gif\">");
			}
			else
			{
				sb.append("<img src=\"").append(CmPathInfo.getIMG_PATH()).append("TREE/tree_empty.gif\">");
			}
		}
		
		if (iCnt == iNowCnt && iChildCnt == 0)
			sb.append("<img src=\"").append(CmPathInfo.getIMG_PATH()).append("TREE/tree_joinbottom.gif\">");
		else if (iCnt == iNowCnt && iChildCnt > 0)
			sb.append("<img src=\"").append(CmPathInfo.getIMG_PATH()).append("TREE/tree_minusbottom.gif\">");
		else if (iCnt > iNowCnt && iChildCnt == 0)
			sb.append("<img src=\"").append(CmPathInfo.getIMG_PATH()).append("TREE/tree_join.gif\">");
		else if (iCnt > iNowCnt && iChildCnt > 0)
			sb.append("<img src=\"").append(CmPathInfo.getIMG_PATH()).append("TREE/tree_minus.gif\">");
		
		return sb.toString();
	}
	
	/**
	 * 
	 * @param treeLine
	 * @param level
	 * @param cnt
	 * @param nowCnt
	 * @param childCnt
	 * @return
	 */
	public static String getTreeText ( String[] treeLine, String level, String cnt, String nowCnt, String childCnt)
	{
		StringBuffer	sb			= new StringBuffer();
		int 			iLevel		= getIntValue(level);
		int 			iCnt		= getIntValue(cnt);
		int 			iNowCnt		= getIntValue(nowCnt);
		
		for (int i = 0 ; i < iLevel; i++)
		{
			if ( "Y".equals(treeLine[i]) )
			{
				sb.append(" ┃ ");
			}
			else
			{
				sb.append("	");
			}
		}
		
		if (iCnt == iNowCnt)
			sb.append(" ┗ ");
		else
			sb.append(" ┣ ");
		
		return sb.toString();
	}
	
	/**
	 * tree 이미지
	 * @param treeLine
	 * @param level
	 * @param cnt
	 * @param nowCnt
	 * @return
	 */
	public static String[] getTreeImgArr ( String[] treeLine, String level, String cnt, String nowCnt)
	{
		int 			iLevel		= getIntValue(level);
		int 			iCnt		= getIntValue(cnt);
		int 			iNowCnt		= getIntValue(nowCnt);
		
		if (treeLine == null)
			treeLine	= new String[]{"N", "Y", "Y", "Y", "Y", "Y", "Y", "Y", "Y", "Y"};
		
		if (iCnt == iNowCnt )
			treeLine[iLevel]		= "N";
		else
			treeLine[iLevel]		= "Y";	
		
		return treeLine;
	}
	
	/**
	 * 파일을 읽어서 내용을 리턴한다.
	 * @param sPath			파일의 패스+파일명
	 * @return result		 파일 내용을 담고 있는 스트링 객체
	 * @throws Exception
	 */
	public static String fileRead(String path) throws Exception
	{
		String result = "";
		try {
			FileReader fr = new FileReader(path);
			BufferedReader reader = new BufferedReader(fr);
			StringBuffer sb = new StringBuffer();
			int len = 4096; // 4k
			char[] buff = new char[len];
			while (true)
			{
				int rsize = reader.read(buff, 0, len);
				if (rsize < 0)
				{
					break;
				}
				sb.append(buff, 0, rsize);
			}
			buff = null;
			result = sb.toString();
			reader.close();
		} catch (FileNotFoundException ex){
			//throw new FileNotFoundException(ex.getMessage());
			return "파일 읽기 오류";
		}
		catch (Exception ex) {
			//throw new Exception(ex.getMessage());
			return "파일 읽기 오류";
		}
		return result;
	}
	
	/**
	 * 파일작성
	 * @param  path		파일  패스+이름
	 * @param  contents	컨텐츠 내용
	 * @throws Exception
	 */
	 public static void fileWrite(String path, String contents) throws Exception {
		 File file = null;

		 try {
			file = new File(path);
			FileWriter fw = new FileWriter(file);
			BufferedWriter owriter = new BufferedWriter( fw );
			owriter.write(contents);
			owriter.flush();
			owriter.close();
			fw.close();
		 } catch(Exception ex) {
			 throw new Exception(ex.getMessage());
		 }
	 }
	 
	 /**
	  * REQUEST 객체를 뽑아올수 없는 곳에서 리턴 시킴
	  * web.xml 에 리스너 설정
	  * @return
	  */
	 public static  HttpServletRequest getCurrentRequest() {

			ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder
					.currentRequestAttributes();

			HttpServletRequest hsr = sra.getRequest();
			return hsr;
		}
	 
	 /**
	  * 현재 언어 코드를 반환 ( ko, en ...)
	  * @return
	  */
	 public static String getLanguage() {
		 return CmFunction.getStringValue(CmFunction.getCurrentRequest().getSession().getAttribute("language"));
	 }
	 
	 public static String getDateString(String dateStr) {
		 String result = "";
		 Calendar cal = Calendar.getInstance();
		 cal.toString();
		 return result;
	 }
	 
 	/**
 	 * 
 	 * @param request
 	 * @param dataMap
 	 */
 	@SuppressWarnings("rawtypes")
	public static void setPageUrlAndPars(HttpServletRequest request, CmMap dataMap ) {
 		
 		StringBuffer 	sb 			= new StringBuffer();
 		String 			pageUrl 	= request.getRequestURI();
 		String			pagePars	= "";
 		int				len			= 0;
 		String 			key 		= "";
 		int				idx			= -1;
 		String[]		values		= null;
 		
 		try {
 			
 			for (Enumeration en = request.getParameterNames(); en.hasMoreElements(); )
 			{
 				key  	= CmFunction.getStringValue((String)en.nextElement());
 				idx		= key.indexOf("_temp");
 				if ( !key.equals("") && idx == -1 ) {
 					if ( !key.equals("i_sReturnUrl") && !key.equals("i_sReturnPars") ) {
 						
 						values			= request.getParameterValues(key);
 						len				= values.length;
 						
 						if (len > 1) {
 							for (int i = 0; i < len; i++) {
 								sb.append(key).append("=").append(URLEncoder.encode(values[i], CmPathInfo.getCHARSET())).append("&");
 							}
 						}
 						else {
 							sb.append(key).append("=").append(URLEncoder.encode(values[0], CmPathInfo.getCHARSET())).append("&");
 						}
 					}
 				}
 			}
 			pagePars	= sb.toString();
 			
 			dataMap.put("i_sPageUrl", pageUrl);
 			dataMap.put("i_sPagePars", pagePars);
 			
 		} catch (Exception e) {
 			e.printStackTrace();
 		}
 	}
 	
 	public static String getSessionStringValue(String key) {
 		return CmFunction.getStringValue(getCurrentRequest().getSession().getAttribute(key));
 	}
 	
	/**
	 * 
	 * @param request
	 * @param dataMap
	 */
	public static void setSessionValue(HttpServletRequest request, CmMap dataMap ) {
		
		if (dataMap == null)
			dataMap		= new CmMap();
		
		//JCLEE :: 2015-08-11 세션 관련해서 벽산 테이블에서 조회해오는 컬럼명으로 수정.
		HttpSession		session		= request.getSession();
		String			userId		= (String)session.getAttribute("v_userid");
		
		dataMap.put("s_userid", 		session.getAttribute("v_userid"));
		dataMap.put("s_empno", 			session.getAttribute("v_empno"));
		dataMap.put("s_usernm", 		session.getAttribute("v_usernm"));
		dataMap.put("s_positcd", 		session.getAttribute("v_positcd"));
		dataMap.put("s_positnm", 		session.getAttribute("v_positnm"));
		dataMap.put("s_positnm_en", 	session.getAttribute("v_positnm_en"));
		dataMap.put("s_positnm_cn", 	session.getAttribute("v_positnm_cn"));
		dataMap.put("s_email", 			session.getAttribute("v_email"));
		dataMap.put("s_dept_authcd", 	session.getAttribute("v_dept_authcd"));
		dataMap.put("s_sigma_deptcd", 	session.getAttribute("v_sigma_deptcd"));
		dataMap.put("s_sigma_deptnm", 	session.getAttribute("v_sigma_deptnm"));	//소속부서 한글
		dataMap.put("s_sigma_deptnm_en",session.getAttribute("v_sigma_deptnm_en"));	//소속부서 영문
		dataMap.put("s_sigma_deptnm_cn",session.getAttribute("v_sigma_deptnm_cn"));	//소속부서 중문
		dataMap.put("s_deptcd", 		session.getAttribute("v_deptcd"));
		dataMap.put("s_compcd", 		session.getAttribute("v_compcd"));
		dataMap.put("s_bgcd", 			session.getAttribute("v_bgcd"));
		dataMap.put("s_top_deptcd", 	session.getAttribute("v_top_deptcd"));
		dataMap.put("s_offinm", 		session.getAttribute("v_offinm"));
		dataMap.put("s_phoneno", 		session.getAttribute("v_phoneno"));
		dataMap.put("s_groupid", 		session.getAttribute("v_groupid"));
		dataMap.put("s_sysadmin_yn", 	session.getAttribute("v_sysadmin_yn"));
		dataMap.put("s_syssubadmin_yn", session.getAttribute("v_syssubadmin_yn"));
		dataMap.put("s_language", 		session.getAttribute("language"));
		dataMap.put("v_flag_external", 	session.getAttribute("v_flag_external"));
		dataMap.put("s_folder_cd", 		session.getAttribute("v_folder_cd"));
		dataMap.put("s_imwon_yn",		session.getAttribute("v_imwon_yn"));
		dataMap.put("s_currency",		session.getAttribute("v_currency"));
		dataMap.put("s_currencynm",		session.getAttribute("v_currencynm"));
		
		dataMap.put("s_sigm_code",		session.getAttribute("vc_sigm_code"));
		
		dataMap.put("s_usernm_en",		session.getAttribute("v_usernm_en"));	//사용자 영문명
		dataMap.put("s_usernm_cn",		session.getAttribute("v_usernm_cn"));	//사용자 중문명
		dataMap.put("s_deptnm",			session.getAttribute("v_deptnm"));		//사용자 부서명
		dataMap.put("s_postbelt",		session.getAttribute("v_postbelt"));	//사용자 postbelt
		dataMap.put("s_payxgb",			session.getAttribute("v_payxgb"));		//사용자 사무직,현장직,임원 구분
		
		if (userId == null || userId.equals(""))
			userId		= "guest";
		
		dataMap.put("i_sRegUserId", userId);
		dataMap.put("i_sUpdateUserId", userId);
	}
	
	/**
	 * 
	 * @param map
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static CmMap changeCmMapKey( CmMap map ) {
		
		if (map == null)
			return null;
		
		CmMap		resMap				= new CmMap();
		Iterator	itr					= map.keySet().iterator();
		String		filter[]			= {"v_", "n_", "c_"};
		String		arrKey[];
		String		oldKey				= "";
		String		newKey				= "";
		int			len					= filter.length;
		int			i, index;
		
		while (itr.hasNext()) 
		{
			oldKey 	= (String)itr.next();
			index	= -1;
			for (i = 0; i < len; i++) {
				if (oldKey.indexOf(filter[i]) == 0) {
					index	= i;
				}
			}
			
			switch (index) {
				case 0 :
					newKey	= "i_s";
					break;
				case 1 :
					newKey	= "i_i";
					break;
				case 2 :
					newKey	= "i_s";
					break;
				default :
					newKey	= "";
					break;
			}
			
			if (newKey.equals(""))
				continue;
			
			arrKey	= oldKey.split("_");
			
			if (arrKey == null || arrKey.length <= 1)
				continue;
				
			for (i = 1; i < arrKey.length; i++) {
				
				if (arrKey[i].length() == 0)
					continue;
				else if (arrKey[i].length() == 1)
					newKey += arrKey[i].toUpperCase();
				else
					newKey += arrKey[i].substring(0, 1).toUpperCase() + arrKey[i].substring(1, arrKey[i].length()).toLowerCase();	
			}
			
			System.out.println("oldKey " + oldKey + " => newKey " + newKey );
			
			resMap.put(newKey, map.get(oldKey));
		}
		
		return resMap;
	}

	/**
	 * 
	 * @param resMap	
	 * @param map
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static CmMap changeCmMapKey( CmMap resMap, CmMap map) {
		
		if (map == null)
			return resMap;
		
		if (resMap == null)
			resMap				= new CmMap();
		
		Iterator	itr			= map.keySet().iterator();
		String		filter[]	= {"v_", "n_", "c_"};
		String		arrKey[];
		String		oldKey		= "";
		String		newKey		= "";
		int			len			= filter.length;
		int			i, index;
		
		while (itr.hasNext()) 
		{
			oldKey 	= (String)itr.next();
			index	= -1;
			for (i = 0; i < len; i++) {
				if (oldKey.indexOf(filter[i]) == 0) {
					index	= i;
				}
			}
			
			switch (index) {
				case 0 :
					newKey	= "i_s";
					break;
				case 1 :
					newKey	= "i_i";
					break;
				case 2 :
					newKey	= "i_s";
					break;
				default :
					newKey	= "";
					break;
			}
			
			if (newKey.equals(""))
				continue;
			
			arrKey	= oldKey.split("_");
			
			if (arrKey == null || arrKey.length <= 1)
				continue;
			
			for (i = 1; i < arrKey.length; i++) {
				
				if (arrKey[i].length() == 0)
					continue;
				else if (arrKey[i].length() == 1)
					newKey += arrKey[i].toUpperCase();
				else
					newKey += arrKey[i].substring(0, 1).toUpperCase() + arrKey[i].substring(1, arrKey[i].length()).toLowerCase();	
			}
			
			System.out.println("oldKey " + oldKey + " => newKey " + newKey + " :  value => " + map.get(oldKey));
			
			resMap.put(newKey, map.get(oldKey));
		}
		
		return resMap;
	}
	
	/**
	 * 
	 * @param returnMap
	 * @param map
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static CmMap cloneCmMap( CmMap returnMap, CmMap map ) {
		if (map == null) {
			return returnMap;
		}

		if (returnMap == null) {
			returnMap				= new CmMap();
		}
		
		Iterator	itr				= map.keySet().iterator();
		String		oldKey			= "";
		
		while (itr.hasNext()) {
			oldKey 	= (String)itr.next();
			returnMap.put(oldKey, map.get(oldKey));
		}
		
		return returnMap;
	}
	
	/**
	 * CmMap 객체 복사
	 * @param map
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static CmMap cloneCmMap( CmMap map ) {
		if (map == null)
			return null;
		
		CmMap		resMap		= new CmMap();
		Iterator	itr			= map.keySet().iterator();
		String		oldKey		= "";
		
		while (itr.hasNext()) {
			oldKey 	= (String)itr.next();
			resMap.put(oldKey, map.get(oldKey));
		}
		
		return resMap;
	}
	
	//TODO POI 관련 
	/**
	 * 
	 * @param filePath
	 * @return
	 */
	/*public static List<CmMap> readXlsToCmMap (String filePath , String[] arrTitleClass) {
		FileInputStream 	fis 	= null;
		POIFSFileSystem 	fs 		= null;
		HSSFWorkbook 		wb 		= null;
		CmMap 				map 	= null;
		List<CmMap> 		list 	= new ArrayList<CmMap>();
		SimpleDateFormat	sdf		= new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			fis = new FileInputStream(filePath);
			fs = new POIFSFileSystem(fis);
			wb = new HSSFWorkbook(fs);
			
			HSSFSheet sheet = wb.getSheetAt(0);
			
			Cell cell = null;
			int firstRow = sheet.getFirstRowNum();
			int lastRow = sheet.getLastRowNum();
			int firstCell = 0;
			int lastCell = 0;
			
			for (int rowIdx = firstRow; rowIdx <= lastRow; rowIdx++) {
				
				Row row = sheet.getRow(rowIdx);
				
				if (row == null) {
					continue;
				}
				
				firstCell = row.getFirstCellNum();
				lastCell = row.getLastCellNum();
				
				map = new CmMap();
				
				for (int cellIdx = firstCell; cellIdx <= lastCell; cellIdx++) {
					
					cell = row.getCell(cellIdx);
					
					if (cell == null)
						continue;
					
					
					switch (cell.getCellType()) {
						case Cell.CELL_TYPE_BOOLEAN :
							System.out.println("" + cellIdx + " : CELL_TYPE_BOOLEAN");
							break;
						case Cell.CELL_TYPE_BLANK :
							System.out.println("" + cellIdx + " : CELL_TYPE_BLANK");
							break;
						case Cell.CELL_TYPE_ERROR :
							System.out.println("" + cellIdx + " : CELL_TYPE_ERROR");
							break;
						case Cell.CELL_TYPE_FORMULA :
							System.out.println("" + cellIdx + " : CELL_TYPE_FORMULA");
							break;
						case Cell.CELL_TYPE_NUMERIC :
							System.out.println("" + cellIdx + " : CELL_TYPE_NUMERIC");
							break;
						case Cell.CELL_TYPE_STRING :
							System.out.println("" + cellIdx + " : CELL_TYPE_STRING");
							break;
					}
					
					
					if (arrTitleClass != null && arrTitleClass.length > cellIdx && arrTitleClass[cellIdx] != null && arrTitleClass[cellIdx].indexOf("cal") > -1) {
						try {
							map.put("i_sCell" + (cellIdx + 1) , sdf.format(cell.getDateCellValue()));
						} catch (Exception e) {
							cell.setCellType(cell.CELL_TYPE_STRING);
							map.put("i_sCell" + (cellIdx + 1) , cell.getStringCellValue());
						}
					}
					else {
						cell.setCellType(cell.CELL_TYPE_STRING);
						map.put("i_sCell" + (cellIdx + 1) , cell.getStringCellValue());
					}
				}
				
				list.add(map);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	*//**
	 * 
	 * @param filePath
	 * @return
	 *//*
	public static List<CmMap> readXlsxToCmMap (String filePath , String[] arrTitleClass) {
		XSSFWorkbook 		xb 		= null;
		CmMap 				map 	= null;
		List<CmMap> 		list 	= new ArrayList<CmMap>();
		SimpleDateFormat	sdf		= new SimpleDateFormat("yyyy-MM-dd");
		try {
			xb = new XSSFWorkbook(filePath);
			
			XSSFSheet sheet = xb.getSheetAt(0);
			
			Cell cell = null;
			int firstRow = sheet.getFirstRowNum();
			int lastRow = sheet.getLastRowNum();
			int firstCell = 0;
			int lastCell = 0;
			
			for (int rowIdx = firstRow; rowIdx <= lastRow; rowIdx++) {
				
				Row row = sheet.getRow(rowIdx);
				
				if (row == null) {
					continue;
				}
				
				firstCell = row.getFirstCellNum();
				lastCell = row.getLastCellNum();
				
				map = new CmMap();
				
				for (int cellIdx = firstCell; cellIdx <= lastCell; cellIdx++) {
					
					cell = row.getCell(cellIdx);
					
					if (cell == null)
						continue;
					
					
					switch (cell.getCellType()) {
						case Cell.CELL_TYPE_BOOLEAN :
							System.out.println("" + cellIdx + " : CELL_TYPE_BOOLEAN");
							break;
						case Cell.CELL_TYPE_BLANK :
							System.out.println("" + cellIdx + " : CELL_TYPE_BLANK");
							break;
						case Cell.CELL_TYPE_ERROR :
							System.out.println("" + cellIdx + " : CELL_TYPE_ERROR");
							break;
						case Cell.CELL_TYPE_FORMULA :
							System.out.println("" + cellIdx + " : CELL_TYPE_FORMULA");
							break;
						case Cell.CELL_TYPE_NUMERIC :
							System.out.println("" + cellIdx + " : CELL_TYPE_NUMERIC");
							break;
						case Cell.CELL_TYPE_STRING :
							System.out.println("" + cellIdx + " : CELL_TYPE_STRING");
							break;
					}
					
					
					if (arrTitleClass != null && arrTitleClass.length > cellIdx && arrTitleClass[cellIdx] != null && arrTitleClass[cellIdx].indexOf("cal") > -1) {
						try {
							map.put("i_sCell" + (cellIdx + 1) , sdf.format(cell.getDateCellValue()));
						} catch (Exception e) {
							cell.setCellType(cell.CELL_TYPE_STRING);
							map.put("i_sCell" + (cellIdx + 1) , cell.getStringCellValue());
						}
					}
					else {
						cell.setCellType(cell.CELL_TYPE_STRING);
						map.put("i_sCell" + (cellIdx + 1) , cell.getStringCellValue());
					}
				}
				
				list.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}*/

	public static String getSubStr(String i_sSource, String ch, String length) {
		if (i_sSource.length() > getIntValue( length) ) {
			return i_sSource.substring( 0, getIntValue( length) ) + ch + ch;
		}
		else {
			return i_sSource;
		}
	}
	
	public static CmMap newMap(String ...strings) {
		CmMap result = new CmMap();
		
		String[] keyArray = new String[strings.length/2];
		String[] valueArray = new String[strings.length/2];
		
		for ( int i = 1 ; i < strings.length +1 ; i++) {
			String param = strings[i-1];
			if ( i%2 == 0 ) {
				valueArray[(i/2+i%2 - 1)] = param;
			}
			else {
				keyArray[(i/2+i%2 - 1)] = param;
			}
		}
		
		for ( int i = 0 ; i < keyArray.length ; i++ ) {
			result.put(keyArray[i], valueArray[i]);
		}
		
		return result;
	}
	
	
	public static String getDoubleFormat( String str, int integerLen, int decimalLen ) {
		
		if (str == null || str.equals("")) {
			
			StringBuffer sb		= new StringBuffer();
			sb.append("0");
			
			if (decimalLen > 0) {
				sb.append(".");
				for (int i = 0; i < decimalLen; i++) {
					sb.append("0");
				}
			}
			return sb.toString();
		}
		
		if (integerLen < 0)
			integerLen = 1;
		
		StringBuffer intSb = new StringBuffer();
		StringBuffer deciSb = new StringBuffer();
		
		for (int i = 0; i < integerLen; i++) {
			intSb.append("#");
		}
		for (int i = 0; i < decimalLen; i++) {
			deciSb.append("#");
		}
		
		intSb.append(".").append(deciSb.toString());
		
		DecimalFormat format	= new DecimalFormat(intSb.toString());
		
		return "" + format.format(Double.parseDouble(getOnlyDouble(str)));
	}
	
	//숫자 자리수 맞추기
	public static String getStringFormat(int integerLen, int val) {
		String str = "";
		
		str = String.format("%0"+integerLen+"d", val);
		return str;
	}	
	
	public static CmMap getReturnParsToCmMap( String str ) {
		CmMap		map		= new CmMap();
		
		try {
			if (str != null || !"".equals(str)) {
				
				String[] 	parse	= str.split("&");
				String[]	arrKey	= null;
				String		key		= "";
				String		val		= "";
				int 		len		= parse.length;
				
				for (int i = 0; i < len; i++) {
					
					arrKey	= parse[i].split("=");
					
					if (arrKey == null)
						continue;
						
						key = arrKey[0];
						
						if (arrKey.length != 2)
							continue;
						
						val = URLDecoder.decode(arrKey[1],CmPathInfo.getCHARSET());
						
					map.put(key, val);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}


	public static int getHalCnt(List<CmMap> lstMat, String number) {
		int cnt = 0;
		if(lstMat != null) {
			for (CmMap mat : lstMat) {
				if(mat.getString("v_mtart").equals("HAL" + number)) {
					cnt++;
				}
			}
		}

		return cnt;
	}
	
	//TODO POI 관련
	/**
	 * Excel Cell Style
	 * @param wb
	 * @return
	 */
	/*public static HSSFCellStyle getHSSFCellStyle(HSSFWorkbook wb) {
		return (HSSFCellStyle)createStyles(wb).get("default");
	}
	
	public static HSSFSheet getHSSFSheet(HSSFWorkbook wb, String[] titleArray) {
		return getHSSFSheet(wb, titleArray, null, null);
	}
	
	public static HSSFSheet getHSSFSheet(HSSFWorkbook wb, String[] titleArray, int[] columnWidth) {
		return getHSSFSheet(wb, titleArray, columnWidth, null);
	}
	
	public static HSSFSheet getHSSFSheet(HSSFWorkbook wb, String[] titleArray, int[] columnWidth, String[] styleArray ) {
		
		int titleLen	= titleArray == null ? 0 : titleArray.length;
		int widthLen	= columnWidth == null ? 0 : columnWidth.length;
		int csLen		= styleArray == null ? 0 : styleArray.length;
		
		Map<String, CellStyle>	stylesMap	= createStyles(wb);	
		HSSFSheet 				sheet 		= wb.createSheet();			// POI 객체 생성
		HSSFRow 				header 		= sheet.createRow(0);		// 조회일자가 들어갈 행을 만든다.
		
		for ( int i = 0 ; i < titleLen ; i++ ) {
			header.createCell(i);
		}
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		header.getCell(0).setCellValue("(조회일자 : "+simpleDateFormat.format(new Date())+")");
		
		sheet.createRow(1);
		
		HSSFRow row;
		HSSFCell cell;
		
		//컬럼 타이틀 row 생성
		row = sheet.createRow(2);
		//컬럼 타이틀 설정
		for ( int i = 0 ; i < titleLen ; i++ ) {
			cell = row.createCell(i); 
			cell.setCellValue(titleArray[i]);
			
			if (csLen > i && styleArray[i] != null) {
				cell.setCellStyle(stylesMap.get(styleArray[i]));
			}
			else {
				cell.setCellStyle(stylesMap.get("default"));
			}
			
			// width 설정
			if (widthLen > i) {
				sheet.setColumnWidth(i, columnWidth[i] * 265);
			}
			else {
				sheet.autoSizeColumn(i);
			}
		}
		
		return sheet;
	}*/
	
	//TODO POI 관련
	/**
	 * Cell Style
	 * @param wb
	 * @return
	 */
	/*public static Map<String, CellStyle> createStyles(HSSFWorkbook wb){
		
		Map<String, CellStyle> styles = new HashMap<String, CellStyle>();
		CellStyle style;
		Font font;
		
		Font titleFont = wb.createFont();
		titleFont.setFontHeightInPoints((short)18);
		titleFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
		style = wb.createCellStyle();
		style.setAlignment(CellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		style.setFont(titleFont);
		styles.put("title", style);
		 
		Font monthFont = wb.createFont();
		monthFont.setFontHeightInPoints((short)11);
		monthFont.setColor(IndexedColors.WHITE.getIndex());
		style = wb.createCellStyle();
		style.setAlignment(CellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		style.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		style.setFont(monthFont);
		style.setWrapText(true);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBottomBorderColor(HSSFColor.BLACK.index);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setLeftBorderColor(HSSFColor.BLACK.index);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setRightBorderColor(HSSFColor.BLACK.index);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setTopBorderColor(HSSFColor.BLACK.index);
		styles.put("header2", style);
		
		style = wb.createCellStyle();
		style.setWrapText(true);
		style.setAlignment(CellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBottomBorderColor(HSSFColor.BLACK.index);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setLeftBorderColor(HSSFColor.BLACK.index);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setRightBorderColor(HSSFColor.BLACK.index);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setTopBorderColor(HSSFColor.BLACK.index);
		styles.put("header", style);

		
		style = wb.createCellStyle();
		style.setAlignment(CellStyle.ALIGN_CENTER);
		style.setWrapText(true);
		style.setBorderRight(CellStyle.BORDER_THIN);
		style.setRightBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderLeft(CellStyle.BORDER_THIN);
		style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderTop(CellStyle.BORDER_THIN);
		style.setTopBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderBottom(CellStyle.BORDER_THIN);
		style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		styles.put("cell", style);

		style = wb.createCellStyle();
		style.setAlignment(CellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		style.setDataFormat(wb.createDataFormat().getFormat("0.00"));
		styles.put("formula", style);

		style = wb.createCellStyle();
		style.setAlignment(CellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		style.setFillForegroundColor(IndexedColors.GREY_40_PERCENT.getIndex());
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		style.setDataFormat(wb.createDataFormat().getFormat("0.00"));
		styles.put("formula_2", style);
		
		
		// 기본
		style = wb.createCellStyle();
		style.setWrapText(true);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBottomBorderColor(HSSFColor.BLACK.index);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setLeftBorderColor(HSSFColor.BLACK.index);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setRightBorderColor(HSSFColor.BLACK.index);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setTopBorderColor(HSSFColor.BLACK.index);
		styles.put("default", style);
		
		font = wb.createFont();
		font.setBoldweight(Font.BOLDWEIGHT_BOLD);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBottomBorderColor(HSSFColor.BLACK.index);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setLeftBorderColor(HSSFColor.BLACK.index);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setRightBorderColor(HSSFColor.BLACK.index);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setTopBorderColor(HSSFColor.BLACK.index);
		styles.put("default_bold", style);

		return styles;
	}*/
	

	public static String getAddMonth(String dateString , int addMonth){
		Calendar cal = GregorianCalendar.getInstance();
		String year = "";
		String month = "";
		
		try {
			
			if (isNotEmpty(dateString) && dateString.length() >= 8){
				DateFormat df = new SimpleDateFormat("yyyyMMdd");
				df.setLenient(false);
				Date date = df.parse(dateString);
				cal.setTime(date);
				cal.add(Calendar.MONTH, addMonth);
				
				year = Integer.toString(cal.get(Calendar.YEAR));
				month = (cal.get(Calendar.MONTH)+1 < 10 ? '0'+Integer.toString(cal.get(Calendar.MONTH)+1) : Integer.toString(cal.get(Calendar.MONTH)+1));
			} else {
				return "";
			}
		} catch (ParseException e) {
			e.printStackTrace();
			return "";
		}
		return  year + "/" + month;
	}
	
	// YY/MM/DD
	public static String getDatePatten2(String i_sSource) {
		if (getStringValue(i_sSource).length() < "YYYYMMDD".length()) { 
			return i_sSource;
		}
		
		String	sChar	= "/";
		
		return i_sSource.substring(2, 4)
		+ sChar + i_sSource.substring(4, 6)
		+ sChar + i_sSource.substring(6, 8);
	}
	
	public static String getIndexOfYN(String dateString , String dataString2){
		String indexOfYn	=	"N";
		
		if(dateString.indexOf(dataString2) > -1 ){
			indexOfYn	=	"Y";
		} else {
			indexOfYn	=	"N";
		}
		
		return indexOfYn;
	}
}
