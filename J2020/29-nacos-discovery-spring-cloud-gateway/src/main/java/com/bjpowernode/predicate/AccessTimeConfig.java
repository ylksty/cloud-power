package com.bjpowernode.predicate;

import lombok.Data;

import java.time.LocalTime;

@Data
public class AccessTimeConfig {

    private LocalTime start;

    private LocalTime end;

}