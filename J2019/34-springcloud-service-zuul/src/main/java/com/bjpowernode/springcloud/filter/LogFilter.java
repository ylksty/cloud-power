package com.bjpowernode.springcloud.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class LogFilter extends ZuulFilter {

    /**
     * 在路由的时候执行
     *
     * @return
     */
    @Override
    public String filterType() {
        return FilterConstants.ROUTE_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.PRE_DECORATION_FILTER_ORDER;
    }

    /**
     * 是否要使用过滤器
     *
     * @return
     */
    @Override
    public boolean shouldFilter() {
        //true表示要使用该过滤，false表示不使用该过滤器
        return true;
    }

    /**
     * 在路由的时候执行run方法
     *
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        String remoteAddr = request.getServerName();
        System.out.println("访问地址:" + remoteAddr + request.getRequestURI());

        //接收到参数
        /*request.getParameter("username");
        HttpServletResponse response = currentContext.getResponse();
        try {
            //重定向
            response.sendRedirect("");

            //forward跳转
            RequestDispatcher dispatcher = request.getRequestDispatcher("/path");
            dispatcher.forward(request, response);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }*/

        //int a = 10 / 0;

        //返回值目前是没有什么意义的，我们返回一个null就可以了；
        return null;
    }
}