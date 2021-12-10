package com.bjpowernode.controller;

import com.bjpowernode.service.GoodsFeignService;
import com.bjpowernode.suport.HttpSuport;
import lombok.extern.slf4j.Slf4j;
import org.apache.skywalking.apm.toolkit.trace.TraceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Slf4j
@RestController
public class GoodsController {

    @Autowired
    private GoodsFeignService goodsFeignService;

    //@Trace(operationName = "trace_goods")
    @RequestMapping("/goods")
    public Object getAllGoods() {
        log.info("/goods --> getAllGoods --> ......" );

        TraceContext.putCorrelation("myKey", "123456");
        Optional<String> op = TraceContext.getCorrelation("myKey");
        log.info("myValue = {} ", op.get());

        String traceId = TraceContext.traceId();
        log.info("traceId = {} ", traceId);

        HttpSuport.getInstance().suport();

        return goodsFeignService.getAllGoods();
    }
}