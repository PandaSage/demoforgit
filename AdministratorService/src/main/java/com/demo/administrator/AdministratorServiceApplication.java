package com.demo.administrator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author hclee
 * @EnableAspectJAutoProxy XML 기반의 ApplicationContext 설정에서의 <aop:aspectj-autoproxy />와 동일한 기능
 */
@SpringBootApplication
@EnableAspectJAutoProxy
public class AdministratorServiceApplication { 
	public static void main(String[] args) {
		SpringApplication.run(AdministratorServiceApplication.class, args);
	}
}
