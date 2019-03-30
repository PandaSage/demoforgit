package com.demo.administrator.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.MediaType;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.demo.administrator.aop.RequestHandler;
import com.demo.administrator.viewresolver.JsonViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.demo.administrator" })
public class WebConfig extends WebMvcConfigurerAdapter {
	@Autowired RequestHandler requesthandler;
	
	/*
	 * TODO 정확한 설정법 찾아보기
	 * <mvc:default-servlet-handler>에 해당됨.
	 */
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	
	/*
	 * web resource handlers
	 * webjars/ CSS / JavaScript / Image 등의 정적 리소스를 처리해주는 핸들러를 등록
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");	//resource 관리를 web root 로 처리
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}

	/* 
	 * page 처리
	 * 요청받은 url에 대한 정적 매핑 처리
	 */
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		// 특별히 controller 를 타지 않아도 되는 뷰만 있는 경우 등록
		// ex) 디자인만 입힌 것들.
		registry.addViewController("/").setViewName("/hello");
		// 404 오류가 발생했을때 보여줄 뷰를 등록
		// registry.addViewController("/page-not-found").setViewName("errors/404");
	}

	/* 
	 * interceptor config
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		/*registry.addInterceptor(new LocaleInterceptor());
		registry.addInterceptor(new ThemeInterceptor()).addPathPatterns("/**").excludePathPatterns("/admin/**");
		registry.addInterceptor(new SecurityInterceptor()).addPathPatterns("/secure/*");*/
	}
	
	/* 
	 * ContentNegotiatingViewResolver
	 * data view configurer
	 */
	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		configurer.favorPathExtension(true)	//확장자로 contentType을 구분할 것인가
			.favorParameter(false)			//특정 파라메터로 contentType을 구분할 것인가(ex: format=json)
			.defaultContentType(MediaType.APPLICATION_JSON)
				.mediaType("xml", MediaType.APPLICATION_ATOM_XML)
				.mediaType("jsonp", MediaType.valueOf("application/javascript"))
				.mediaType("json", MediaType.APPLICATION_JSON);
	}
	
	/**
	 * resolver mapping
	 * @param manager
	 * @return
	 */
	@Bean
	public ContentNegotiatingViewResolver ContentNegotiatingViewResolver(ContentNegotiationManager manager) {
		ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
			resolver.setContentNegotiationManager(manager);

		// Define all possible view resolvers
		List<ViewResolver> resolvers = new ArrayList<ViewResolver>();
			resolvers.add(jspViewResolver());
			resolvers.add(jsonViewResolver());
			/*resolvers.add(jaxb2MarshallingXmlViewResolver());
			resolvers.add(pdfViewResolver());
			resolvers.add(excelViewResolver());*/

		resolver.setViewResolvers(resolvers);
		return resolver;
	}

	 /*
	 * Configure View resolver to provide JSON output using JACKSON library to
	 * convert object in JSON format.
	 * */
	 
	@Bean
	public ViewResolver jsonViewResolver() {
		return new JsonViewResolver();
	}
	
	//TODO 하위 data resolver 추후 구현 예정 (일단 테스트용 json resolver 구현)
	/*
	 * Configure View resolver to provide XML output Uses JAXB2 marshaller to
	 * marshall/unmarshall POJO's (with JAXB annotations) to XML
	 
	@Bean
	public ViewResolver jaxb2MarshallingXmlViewResolver() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setClassesToBeBound(Pizza.class);
		return new Jaxb2MarshallingXmlViewResolver(marshaller);
	}
 
	 * Configure View resolver to provide PDF output using lowagie pdf library to
	 * generate PDF output for an object content
	 
	@Bean
	public ViewResolver pdfViewResolver() {
		return new PdfViewResolver();
	}
 
	
	 * Configure View resolver to provide XLS output using Apache POI library to
	 * generate XLS output for an object content
	 
	@Bean
	public ViewResolver excelViewResolver() {
		return new ExcelViewResolver();
	}*/

	/**
	 * JSP를 뷰로 사용하는 뷰 리졸버 등록
	 */
	@Bean
	public ViewResolver jspViewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
			viewResolver.setViewClass(JstlView.class);
			viewResolver.setPrefix("/WEB-INF/views/");
			viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
	
	/**
	 * message source 들을 등록함
	 * TODO menu, main-content 등 활용 예정
	 * @return
	 */
	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
			messageSource.setBasenames("classpath:messages/messages", "classpath:messages/validation");
			// if true, the key of the message will be displayed if the key is not
			// found, instead of throwing a NoSuchMessageException
			messageSource.setUseCodeAsDefaultMessage(true);
			messageSource.setDefaultEncoding("UTF-8");
			// # -1 : never reload, 0 always reload
			messageSource.setCacheSeconds(0);
		return messageSource;
	}
	
	/**
	 * methodinterceptor porxy 생성
	 * @return
	 */
	/*@Bean
	public void requestInterceptorFactoryBean() {
		ProxyFactoryBean	reqeustValueInterceptor	=	new	ProxyFactoryBean();
		RegexpMethodPointcutAdvisor	ad = new RegexpMethodPointcutAdvisor();
			ad.setAdvice(new RequestHandler());
			ad.setPattern("*Controller");
		reqeustValueInterceptor.addAdvisor(ad);
	}
	*/
	
	/*
	 * lucy-xss-filter
	 *  TODO 추후 다시 구현 (ssc 와 함께 고려)
	 * 
	@Bean
	public FilterRegistrationBean getFilterRegistrationBean(){
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
			registrationBean.setFilter(new XssEscapeServletFilter());
			registrationBean.setOrder(1);
			registrationBean.addUrlPatterns("*.do", "*.go");	//filter를 거칠 url patterns
		return registrationBean;
	}*/
}
