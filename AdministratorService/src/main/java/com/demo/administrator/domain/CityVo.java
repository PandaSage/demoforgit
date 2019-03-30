package com.demo.administrator.domain;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CityVo extends CommonVo implements Serializable {
	private static final long serialVersionUID = 2696113054752441014L;
	String name;
}
