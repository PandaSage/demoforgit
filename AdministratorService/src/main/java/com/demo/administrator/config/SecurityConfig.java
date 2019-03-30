package com.demo.administrator.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Spring security configration
 * TODO 인증/보안 로직 추후 구현
 * @author hclee
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// @formatter:off
		http
			.formLogin()
				.loginPage("/login/userLogin")
			.and()
				.logout()
					.logoutUrl("/user/logout")
					.deleteCookies("JSESSIONID")
					.logoutSuccessUrl("/post/list")
			.and()
				.authorizeRequests()
					.antMatchers("/**/write*", "/**/edit*", "/**/delete*", "/main/main*").authenticated()
					.antMatchers("/**").permitAll();
		// @formatter:on
	}
}
