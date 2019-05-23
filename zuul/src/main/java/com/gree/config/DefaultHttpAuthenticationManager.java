package com.gree.config;

import com.gree.exception.TokenExpiredException;
import com.gree.feign.AuthTokenApi;
import com.gree.result.HandleRestResponse;
import com.gree.util.AuthConstants;
import com.gree.util.HttpTokenExtractor;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class DefaultHttpAuthenticationManager implements HttpAuthenticationManager{
    private Logger logger = LoggerFactory.getLogger(getClass());

    private HttpTokenExtractor extractor;

    private PathMatcher matcher = new AntPathMatcher();

    private AuthTokenApi authTokenApi;

    public DefaultHttpAuthenticationManager(HttpTokenExtractor extractor, AuthTokenApi authTokenApi) {
        this.extractor = extractor;
        this.authTokenApi = authTokenApi;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Map<String, Object> authenticate(HttpServletRequest request){
        String uri = request.getRequestURI();
        //判断是否为不过滤访问路径
        if(IsIgnoreUrl(uri)){
            return null;
        }
        //提取token
        String token = extractor.extract(request);
        if(StringUtils.isBlank(token)){
            throw new TokenExpiredException("can not extract token!");
        }
        //超级token判断
        if(AuthConstants.SUPER_TOKEN.equals(token)){
            return null;
        }
        return new HandleRestResponse<LinkedHashMap>().handle(LinkedHashMap.class,authTokenApi.checkToken(token,uri));
    }

    @Override
    public Boolean isIgnoreUrl(String url) {
        return IsIgnoreUrl(url);
    }

    public boolean IsIgnoreUrl(String path) {
        PermitProperties ignore = new PermitProperties();

        for (String ignoreUrlPattern : ignore.getUrls()){
            if(matcher.match(ignoreUrlPattern,path)){
                return true;
            }
        }
        return false;
    }

}
