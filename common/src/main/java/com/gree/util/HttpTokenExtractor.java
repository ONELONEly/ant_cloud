package com.gree.util;


import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface HttpTokenExtractor {

    String extract(HttpServletRequest request);

    Map<String,String> extractLoginMessage(HttpServletRequest request);
}
