package com.demo.administrator.base.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DownloadServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void performTask(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("utf-8");
		String 	tempFileName 	= new String (request.getParameter("tempfilename").getBytes("8859_1"), "KSC5601");  //임시파일명
//		String 	filename 		= new String (request.getParameter("filename").getBytes("8859_1"), "KSC5601");		//원본파일명
		String 	filename 		= java.net.URLDecoder.decode((String)request.getParameter("filename"), "UTF-8");		//원본파일명
		String 	path 			= new String (request.getParameter("path").getBytes("8859_1"), "KSC5601");				//파일경로
		
		try {
			
			//ServletContext 	context 	= getServletContext();
			//String 			rootPath 	= context.getRealPath(path);  //현재 jsp의 실제 경로 산출
			String 			filePath 	= CmPathInfo.getUPLOAD_PATH() + path + tempFileName;
			File 			tempFile 	= new File(filePath);
			String 			agentType 	= request.getHeader("User-Agent");

			System.out.println("파일경로!!!!!!!!!!!----->"+filePath);
		 	System.out.println("파일크기!!!!!!!!!!------->"+tempFile.length());
		 	
		 	//파일을 없거나 읽을수 없는 파일일 경우
			try {
				
				if (!tempFile.exists() || !tempFile.canRead()) 
				{
					PrintWriter out = response.getWriter();
					out.println("<script type='text/javascript'>alert('File Not Found');history.back();</script>");
					return;
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				PrintWriter out = response.getWriter();
				out.println("<script type='text/javascript'>alert('File Not Found');history.back();</script>");
				return;
			}

			boolean flag = false;
			
			if(agentType != null && agentType.indexOf("MSIE 5.5") >= 0)
				flag = true;

			if (flag) {
				filename	= java.net.URLEncoder.encode(filename, "UTF-8");
				//response.setHeader("Content-Type", "doesn/mater;charset=8859_1");
				//response.setHeader("Content-Type", "doesn/mater;charset=utf-8");
				//response.setHeader("Content-Disposition", "attachment;filename="+java.net.URLDecoder.decode((String)request.getParameter("filename"), "UTF-8")+";");
				response.setHeader("Content-Disposition", "attachment;filename="+filename+";");
			} else {
				filename	= java.net.URLEncoder.encode(filename, "UTF-8");
				response.setContentType("application/octet-stream");
				response.setContentLength((int)tempFile.length());
				//response.setHeader("Content-Type", "application/octet-stream; charset=euc-kr");
				//response.setHeader("Content-Type", "application/octet-stream; charset=utf-8");
				//response.setHeader("Content-Disposition", "attachment;filename="+java.net.URLDecoder.decode((String)request.getParameter("filename"), "UTF-8")+";");
				response.setHeader("Content-Disposition", "attachment;filename="+filename+";");
				//response.setHeader("Content-Type", "application/x-msdownload");
			}
			
			response.setHeader("Content-Transfer-Encoding", "binary;");
			response.setHeader("Pragma", "no-cache;");
			response.setHeader("Expires", "-1;");
			
			dumpFile(tempFile, response);

		} catch (Exception e) {
			e.printStackTrace();
			PrintWriter out = response.getWriter();
			out.println("<script>alert('File Not Found');history.back();</script>");
		}

	}

	private void dumpFile(File realFile, HttpServletResponse response) throws Exception {
		byte readByte[] = new byte[4096];
		
		try {
			
			InputStream bufferedinputstream =new FileInputStream(realFile);
			
			int i;
			
			while ((i = bufferedinputstream.read(readByte, 0, 4096)) != -1){
				 response.getOutputStream().write(readByte, 0, i);
			}
			bufferedinputstream.close();
			
		} catch (Exception _ex) {
			_ex.printStackTrace();
		} finally {
			response.getOutputStream().flush();
			response.getOutputStream().close();
		}
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)  throws ServletException, IOException {
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			performTask(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}


