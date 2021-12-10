package com.bjpowernode.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ResultObject<T> {

    private int statusCode;

    private String statusMessage;

    private T data;
}