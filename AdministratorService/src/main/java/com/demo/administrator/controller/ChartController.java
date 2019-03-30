package com.demo.administrator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.administrator.base.object.CmMap;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/chart/*")
public class ChartController {
	@RequestMapping("chart")
	public String chart_view(@ModelAttribute("dataMap") CmMap reqVo) {
		//TODO chart view sample
		log.debug("chart_gogo!!");
		return	"chart/chart";
	}
}
