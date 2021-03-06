package com.demo.administrator.controller.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.administrator.base.object.CmMap;
import com.demo.administrator.dao.CmMysqlDao;
import com.demo.administrator.dao.CmOracleDao;
import com.demo.administrator.mappers.mysql.CityMapper;
import com.demo.administrator.mappers.oracle.CountryMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/main/*")
@Controller
public class MainController {
	@Autowired
	CityMapper citymapper;
	
	@Autowired
	CountryMapper countrymapper;
	
	@Autowired
	CmOracleDao oracleDao;
	
	@Autowired
	CmMysqlDao mysqlDao;
	
	@RequestMapping("chart.do")
	public String chart(@ModelAttribute("dataMap") CmMap reqVo
			, Model model	) {
		log.debug("title : " + reqVo.getString("title"));
		log.debug("content : " + reqVo.getString("content"));
		log.debug("title : " + reqVo.get("title"));
		log.debug("content : " + reqVo.get("content"));
		
		log.debug("CityMapper Time Info : " + citymapper.getCurrentDateTime());
		log.debug("CountryMapper Time Info : " + countrymapper.getCurrentDateTime());
		log.debug("mysqlDao Time Info : " + mysqlDao.selectOne("CityMapper.getCurrentDateTimeForQuery"));
		log.debug("oracleDao Time Info : " + oracleDao.selectOne("CountryMapper.getCurrentDateTimeForQuery"));
		return "main/chart";
	}
	
	@RequestMapping("main")
	public void main(@ModelAttribute("dataMap") CmMap reqVo
			, Model model) {
		log.debug("title : " + reqVo.getString("title"));
		log.debug("content : " + reqVo.getString("content"));
	}
}
