package com.bjpowernode.sentinel;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import com.bjpowernode.model.RestObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义限流降级错误信息
 *
 */
@Slf4j
@Component
public class MyBlockExceptionHandler implements BlockExceptionHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, BlockException e) throws Exception {
        log.info("UrlBlockHandler.....................................");
        RestObject restObject = null;

        // 不同的异常返回不同的提示语
        if (e instanceof FlowException) {
            restObject = RestObject.builder().statusCode(100).statusMessage("接口限流了").build();

        } else if (e instanceof DegradeException) {
            restObject = RestObject.builder().statusCode(101).statusMessage("服务降级了").build();

        } else if (e instanceof ParamFlowException) {
            restObject = RestObject.builder().statusCode(102).statusMessage("热点参数限流了").build();

        } else if (e instanceof SystemBlockException) {
            restObject = RestObject.builder().statusCode(103).statusMessage("触发系统保护规则").build();

        } else if (e instanceof AuthorityException) {
            restObject = RestObject.builder().statusCode(104).statusMessage("授权规则不通过").build();
        }

        //返回json数据
        response.setStatus(500);
        response.setCharacterEncoding("utf-8");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        //springmvc 的一个json转换类 （jackson）
        new ObjectMapper().writeValue(response.getWriter(), restObject);

        //跳转
        //request.getRequestDispatcher("/index.jsp").forward(request, response);

        //重定向
        //response.sendRedirect("http://www.baidu.com");
    }
}