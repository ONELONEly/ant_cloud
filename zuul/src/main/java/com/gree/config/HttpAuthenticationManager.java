package com.gree.config;



import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface HttpAuthenticationManager {


    Map<String, Object> authenticate(HttpServletRequest request);

    Boolean isIgnoreUrl(String url);
}
