package com.base.security.app;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.security.SocialAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import com.base.security.app.util.AppSignUpUtils;
import com.base.security.core.constants.SecurityConstants;
import com.base.security.core.social.support.SocialUserInfo;

@RestController
public class AppSecurityController {
	
    @Autowired
    private ProviderSignInUtils providerSignInUtils;

    //将社交账户信息转存到redis和从redis取出社交账户信息的工具类
    @Autowired
    private AppSignUpUtils appSignUpUtils;

	
	@GetMapping(SecurityConstants.DEFAULT_APP_SOCIAL_SIGNUP_URL)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public SocialUserInfo getSocialUserInfo(HttpServletRequest request,SocialAuthenticationToken authenticationToken){
		SocialUserInfo userInfo=new SocialUserInfo();
//   //从session中取出已经认证的社交账户信息（Connection对象）
        //-->本接口的执行和微信、QQ的等服务提供商拿着授权码回调我们的项目在一个session里，因此这里可以从session里取出社交账户信息
        Connection<?> connection = providerSignInUtils.getConnectionFromSession(new ServletWebRequest(request));
        userInfo.setProviderId(connection.getKey().getProviderId());
        userInfo.setProviderUserId(connection.getKey().getProviderUserId());
        userInfo.setNickname(connection.getDisplayName());
        userInfo.setHeadimg(connection.getImageUrl());

        //从session里将社交账户信息取出并转存到redis
        appSignUpUtils.saveConnectionData(new ServletWebRequest(request), connection.createData());
        return userInfo;
      }
}
