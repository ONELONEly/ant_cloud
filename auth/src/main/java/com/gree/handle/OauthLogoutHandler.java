package com.gree.handle;

import com.gree.util.AuthUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zlt
 * @date 2018/10/17
 */
public class OauthLogoutHandler implements LogoutHandler {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private TokenStore tokenStore;

	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		Assert.notNull(tokenStore, "tokenStore must be set");
		String token = AuthUtils.extractToken(request);
		if(StringUtils.isNotBlank(token)){
			OAuth2AccessToken existingAccessToken = tokenStore.readAccessToken(token);
			OAuth2RefreshToken refreshToken;
			if (existingAccessToken != null) {
				if (existingAccessToken.getRefreshToken() != null) {
					logger.info("remove refreshToken!{}", existingAccessToken.getRefreshToken());
					refreshToken = existingAccessToken.getRefreshToken();
					tokenStore.removeRefreshToken(refreshToken);
				}
				logger.info("remove existingAccessToken!{}", existingAccessToken);
				tokenStore.removeAccessToken(existingAccessToken);
			}
		}
	}
}
