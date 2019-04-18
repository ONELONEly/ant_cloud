package com.gree.config;

import com.gree.exception.KellyException;
import com.gree.exception.TokenExpiredException;
import com.gree.feign.AuthTokenApi;
import com.gree.util.AuthConstants;
import feign.FeignException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
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

    @Override
    public Map<String, Object> authenticate(HttpServletRequest request){
        String uri = request.getRequestURI();
        //判断是否为不过滤访问路径
        if(IsIgnoreUrl(uri)){
            return new HashMap<>();
        }
        //提取token
        String token = extractor.extract(request);
        if(StringUtils.isBlank(token)){
            throw new TokenExpiredException("can not extract token!");
        }
        //超级token判断
        if(AuthConstants.SUPER_TOKEN.equals(token)){
            return new HashMap<>();
        }
        //提交到认证服务器校验
        Map<String, Object> objectMap;
        try {
            objectMap = authTokenApi.checkToken(token,uri);
            if(objectMap.get("code") != null){
                throw new KellyException(objectMap.get("message").toString(),Integer.parseInt(objectMap.get("code").toString()));
            }
        } catch (FeignException e) {
            if(e.getMessage().contains("Token has expired")){
                throw new TokenExpiredException("Token has expired");
            }else{
                throw e;
            }
        }
        return objectMap;
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
