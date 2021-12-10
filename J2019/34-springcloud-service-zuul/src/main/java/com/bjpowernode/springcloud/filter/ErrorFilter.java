package com.bjpowernode.springcloud.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class ErrorFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return "error";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        try {
            RequestContext context = RequestContext.getCurrentContext();
            ZuulException exception = (ZuulException)context.getThrowable();
            System.out.println("进入系统异常拦截" + exception.getMessage());

            HttpServletResponse response = context.getResponse();
            response.setContentType("application/json; charset=utf8");
            response.setStatus(exception.nStatusCode);

            PrintWriter writer = null;
            try {
                writer = response.getWriter();
                writer.print("{code:"+ exception.nStatusCode +",message:\""+ exception.getMessage() +"\"}");
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if(writer!=null){
                    writer.close();
                }
            }
        } catch (Exception e) {
            ReflectionUtils.rethrowRuntimeException(e);
        }

        return null;
    }
}