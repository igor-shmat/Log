package test.concord.service;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

@Component
public class RequestInterceptor {
    private static Logger log = Logger.getLogger(RequestInterceptor.class);

    public void logResponse(Object body, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        StringBuilder stringBuilder = new StringBuilder();
        Map<String, String> parameters = parametersMap(httpServletRequest);

        stringBuilder.append("method=[").append(httpServletRequest.getMethod()).append("] ");
        stringBuilder.append("Response ");
        stringBuilder.append("path=[").append(httpServletRequest.getRequestURI()).append("] ");
        stringBuilder.append("responseBody=[").append(body).append("] ");

        if (!parameters.isEmpty()) {
            stringBuilder.append("parameters=[").append(parameters).append("] ");
        }

        log.info(stringBuilder.toString());
    }

    public void logRequest(Object body, HttpServletRequest httpServletRequest) {
        StringBuilder stringBuilder = new StringBuilder();
        Map<String, String> parameters = parametersMap(httpServletRequest);
        stringBuilder.append("method=[").append(httpServletRequest.getMethod()).append("] ");
        stringBuilder.append("Request ");
        stringBuilder.append("path=[").append(httpServletRequest.getRequestURI()).append("] ");

        if (!parameters.isEmpty()) {
            stringBuilder.append("parameters=[").append(parameters).append("] ");
        }

        if (body != null) {
            stringBuilder.append("body=[" + body + "]");
        }

        log.info(stringBuilder.toString());
    }

    private Map<String, String> parametersMap(HttpServletRequest httpServletRequest) {
        Map<String, String> resultMap = new HashMap<>();
        Enumeration<String> parameterNames = httpServletRequest.getParameterNames();

        while (parameterNames.hasMoreElements()) {
            String key = parameterNames.nextElement();
            String value = httpServletRequest.getParameter(key);
            resultMap.put(key, value);
        }
        return resultMap;
    }
}
