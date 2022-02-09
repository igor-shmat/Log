package test.concord.interceptor;

import test.concord.service.RequestInterceptor;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Type;

@ControllerAdvice
public class RequestBodyAdviceAdapter extends org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter {

    private final HttpServletRequest httpServletRequest;
    private final RequestInterceptor interceptor;

    public RequestBodyAdviceAdapter(HttpServletRequest httpServletRequest, RequestInterceptor interceptor) {
        this.httpServletRequest = httpServletRequest;
        this.interceptor = interceptor;
    }

    @Override
    public boolean supports(MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType,
                                Class<? extends HttpMessageConverter<?>> converterType) {
        interceptor.logRequest(body, httpServletRequest);

        return super.afterBodyRead(body, inputMessage, parameter, targetType, converterType);
    }
}
