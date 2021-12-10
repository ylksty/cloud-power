package com.bjpowernode.suport;

import lombok.extern.slf4j.Slf4j;
import org.apache.skywalking.apm.toolkit.trace.Trace;
import org.apache.skywalking.apm.toolkit.trace.TraceContext;

import java.util.Optional;

@Slf4j
public class HttpSuport {

    public static HttpSuport getInstance() {
        return new HttpSuport();
    }

    /**
     * 该方法不能静态的
     */
    @Trace(operationName = "http_suport")
    public void suport() {

        TraceContext.putCorrelation("httpKey", "http123456");
        Optional<String> op = TraceContext.getCorrelation("httpKey");
        log.info("HttpSuport myValue = {} ", op.get());

        String traceId = TraceContext.traceId();

        log.info("HttpSuport traceId = {} ", traceId);
    }
}