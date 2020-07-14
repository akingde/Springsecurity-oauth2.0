package com.base.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * 验证码 保存 获取 一出 
 * @author lqq
 *
 */
public interface ValidateCodeRepository  {
	
	/**
     * 保存验证码
     * @param request
     * @param code
     * @param validateCodeType
     */
    void save(ServletWebRequest request, ValidateCode code, ValidateCodeType validateCodeType);
    /**
     * 获取验证码
     * @param request
     * @param validateCodeType
     * @return
     */
    ValidateCode get(ServletWebRequest request, ValidateCodeType validateCodeType);
    /**
     * 移除验证码
     * @param request
     * @param codeType
     */
    void remove(ServletWebRequest request, ValidateCodeType codeType);
	
}
