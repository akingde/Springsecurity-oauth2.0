package com.base.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.base.common.ajax.AjaxJson;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AuthExceptionEntryPoint  implements AuthenticationEntryPoint{
	@Autowired
	private ObjectMapper objectMapper;
	
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		log.info("授权失败的信息："+objectMapper.writeValueAsString(authException));
		//只返回json
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		
		response.setCharacterEncoding("UTF-8"); 
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().write(objectMapper.writeValueAsString(AjaxJson.error(HttpServletResponse.SC_UNAUTHORIZED+"", authException.getMessage())));
		
	
	}

}
