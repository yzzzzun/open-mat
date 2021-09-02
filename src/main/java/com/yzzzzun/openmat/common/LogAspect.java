package com.yzzzzun.openmat.common;

import java.text.MessageFormat;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.google.common.base.Joiner;

@Component
@Aspect
public class LogAspect {

	private static final Logger LOGGER = LoggerFactory.getLogger(LogAspect.class);

	@Pointcut("within(com.yzzzzun.openmat..controller.*)")
	public void onRequest() {
	}

	@Around("com.yzzzzun.openmat.common.LogAspect.onRequest()")
	public Object doLogging(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();

		long startTime = System.currentTimeMillis();
		try {
			return proceedingJoinPoint.proceed();
		} finally {
			long endTime = System.currentTimeMillis();
			long executionTime = endTime - startTime;

			String params = this.getParams(request);

			String logFormat = MessageFormat.format("Request : {0} {1} {2} < {3} ({4}ms)", request.getMethod(),
				request.getRequestURI(),
				params,
				request.getRemoteHost(),
				executionTime);

			LOGGER.info(logFormat);
		}
	}

	private String getParams(HttpServletRequest request) {
		Map<String, String[]> parameterMap = request.getParameterMap();
		return parameterMap.entrySet().stream()
			.map(entry -> String.format("%s -> (%s)", entry.getKey(), Joiner.on(",").join(entry.getValue())))
			.collect(Collectors.joining(", "));
	}
}
