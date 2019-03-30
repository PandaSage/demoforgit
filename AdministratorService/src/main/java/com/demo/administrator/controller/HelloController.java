package com.demo.administrator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {

	@RequestMapping({"/index","/index.do","/hello.do"})
	public String index(Model model) {
		model.addAttribute("name", "SpringBlog from Admin");
		return "hello";
	}
	@RequestMapping({"/main.do"})
	public String main(Model model) {
		return "main";
	}
	@RequestMapping({"/admin_calender.do"})
	public String admin_calender(Model model) {
		return "admin_calender";
	}
	@RequestMapping({"/admin_widgets.do"})
	public String admin_widgets(Model model) {
		return "admin_widgets";
	}
}