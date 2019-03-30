package com.demo.administrator.viewresolver;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.AbstractView;

import com.demo.administrator.base.utils.CmFunction;

public class ExcelView extends AbstractView {

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model
			, HttpServletRequest request
			, HttpServletResponse response) throws Exception {
		OutputStream out 	= response.getOutputStream();
		
		try {
			HSSFWorkbook		wb	 	= null;
			SimpleDateFormat	sdf 	= new SimpleDateFormat("yyyyMMdd");
//			String 				title 	= new String( CmFunction.getStringValue(model.get("excelFileName")).getBytes("EUC_KR"),"8859_1" );
			String 				title 	= new String( CmFunction.getStringValue(model.get("excelFileName")).getBytes("UTF-8"),"UTF-8" );
			
			if ( !title.equals("")) {
				response.setHeader("Content-Disposition", "attachment; fileName=\"" +title + "_" + sdf.format(new Date()) + ".xls\";"); 
				response.setHeader("Content-Transfer-Encoding", "binary");	
			}
			
			if (model.get("HSSFWorkbook") != null) {
				wb		= (HSSFWorkbook)model.get("HSSFWorkbook");
			}
			else {
				wb		= new HSSFWorkbook();
			}
			
			wb.write(out);
			out.close();
			out.flush();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}
}
