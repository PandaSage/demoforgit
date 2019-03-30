package com.demo.administrator.aop;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.demo.administrator.base.object.CmMap;

import lombok.extern.slf4j.Slf4j;

/**
 * @author hclee
 * controller, service, dao 등 공동 logging
 * @Order(Ordered.LOWEST_PRECEDENCE) 추가하여 순서를 설정 할 수 있다
 */

@Slf4j
@Component
@Aspect
public class RequestValueAspect {
	
	/**
	 * controller 공통 Request 처리 및 logging (class info, method info, request parameter info)
	 * @param jp
	 * @return
	 * @throws Throwable
	 */
	@Around("execution(* com.demo.administrator.controller..*.*(..))") 
	public Object beforeProcessing(ProceedingJoinPoint jp) throws Throwable {
		log.info("#####################################################");
		log.info("pakage, class info : " + jp.getSignature().getDeclaringTypeName());
		log.info("Method name : " + jp.getSignature().getName());

		for (Object obj : jp.getArgs()) {
			log.info("args class info : " + obj.getClass().getName());	// method arg class logging
			//request 제어를 위한 HttpServletRequest obj 생성 및 일괄 처리를 위한 request map 생성
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
			Map<String, String[]> requestParameterMap = request.getParameterMap(); // TODO getParameterMap 의 generic이 String, String[]인 이유

			if (obj instanceof HttpServletRequest
					|| obj instanceof MultipartHttpServletRequest) {
				//method arg가 HttpServletRequest or MultipartHttpServletRequest 인 경우
				this.isParamLogging(requestParameterMap);

			} else if (obj instanceof CmMap) {
				//TODO [완료] HashMap 을 custom Map으로 작업 필요 : CmMap으로 치환 완료
				//method arg가 CmMap 인 경우
				requestParameterMap.keySet().forEach(key -> {
					//obj를 CmMap 형식으로 casting 후 값을 할당
					//TODO 추후 parameter의 접두어 구분을 이용한 전처리 로직 구현 필요(배열 처리 등)
					log.debug(key +" : "+ requestParameterMap.get(key)[0]);
					((CmMap)obj).put(key, requestParameterMap.get(key)[0]);
				});
				
			}
			log.info("*****************************************************");
		}
		log.info("#####################################################");

		return jp.proceed();
	}

	/**
	 * HttpServletRequest 의 Parameter Logging Method
	 */
	private void isParamLogging(Map<String, String[]> requestParameterMap) {
		//request param logging
		requestParameterMap.keySet().forEach(key -> log.debug(key +" : "+ requestParameterMap.get(key)[0]));
	}
}
