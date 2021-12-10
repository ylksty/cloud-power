package com.bjpowernode.result;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ResultSupport {

    private String errorCode;

    private String errorMessage;
}