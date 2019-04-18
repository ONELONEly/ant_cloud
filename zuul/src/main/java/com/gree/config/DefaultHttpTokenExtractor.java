package com.gree.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.gree.util.AuthConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

public class DefaultHttpTokenExtractor implements HttpTokenExtractor{

    private Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public String extract(HttpServletRequest request) {
        return extractToken(request);
    }

    @Override
    public Map<String, String> extractLoginMessage(HttpServletRequest request) {
        return extractLoginMsg(request);
    }

    private String extractToken(HttpServletRequest request) {
        // first check the header...
        String token = extractHeaderToken(request);

        // bearer type allows a request parameter as well
        if (token == null) {
            Map result = extractJsonMsg(request);
            if (result == null) {
                token = request.getParameter(AuthConstants.TOKEN_PARAM);
                if (token == null || token.length() != 36) {
                    token = null;
                }
            }else{
                token = result.get(AuthConstants.TOKEN_PARAM) == null ? null : result.get(AuthConstants.TOKEN_PARAM).toString();
            }
        }
        return token;
    }

    private Map<String,String> extractLoginMsg(HttpServletRequest request) {
        Map result = extractJsonMsg(request);
        String client_id = request.getHeader(AuthConstants.CLIENT_ID);
        String client_secret = request.getHeader(AuthConstants.CLIENT_SECRET);
        String username;
        String password;
        Map<String,String> msg = new HashMap<>();
        if (result == null) {
            username = request.getParameter("username");
            password = request.getParameter("password");
        }else{
            username = result.get("username") == null ? null : result.get("username").toString();
            password = result.get("password") == null ? null : result.get("password").toString();
        }
        if (username == null || password == null || client_id == null || client_secret == null) {
            msg = null;
        }else{
            msg.put("username",username);
            msg.put("password",password);
            msg.put("client_id",client_id);
            msg.put("client_secret",client_secret);
        }
        return msg;
    }

    /**
     * Extract the OAuth bearer token from a header.
     *
     * @param request The request.
     * @return The token, or null if no OAuth authorization header was supplied.
     */
    private String extractHeaderToken(HttpServletRequest request) {
        return request.getHeader(AuthConstants.TOKEN_PARAM);
    }

    /**
     *
     * @param request 从JSon请求体获得token
     * @return 返回token
     */
    private Map extractJsonMsg(HttpServletRequest request){
        BufferedReader br;
        StringBuilder sb;
        String requestString;
        Map requestMap;
        try {
            br = new BufferedReader(new InputStreamReader(request.getInputStream()));
            String line;
            sb = new StringBuilder();
            while((line = br.readLine()) != null){
                sb.append(line);
            }
            requestString = URLDecoder.decode(sb.toString(),"UTF-8");
            requestMap = JSON.parseObject(requestString, Map.class);
        } catch (IOException| JSONException e) {
            return null;
        }
        return requestMap;
    }

}
