package test.concord.interceptor;

import test.concord.service.RequestInterceptor;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice
public class ResponseBodyAdviceAdapter implements ResponseBodyAdvice<Object> {

    private final RequestInterceptor interceptor;

    public ResponseBodyAdviceAdapter(RequestInterceptor interceptor) {
        this.interceptor = interceptor;
    }

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType,
                                  Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if (serverHttpRequest instanceof ServletServerHttpRequest && serverHttpResponse instanceof ServletServerHttpResponse) {
            interceptor.logResponse(o, ((ServletServerHttpRequest) serverHttpRequest).getServletRequest(),
                    ((ServletServerHttpResponse) serverHttpResponse).getServletResponse());
        }
        return o;
    }
}
