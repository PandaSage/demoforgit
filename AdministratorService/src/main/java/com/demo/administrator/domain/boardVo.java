package com.demo.administrator.domain;

import java.io.Serializable;

import lombok.Data;

@Data
public class boardVo extends CommonVo implements Serializable {
	private static final long serialVersionUID = 1L;
	String		name;
	String		title;
	String		content;
	String[]	options;
}
