package com.example.demo.common;

import lombok.Data;

import java.util.HashMap;

@Data
public class QueryPageParam {
    private static int PAGE_SIZE = 20;
    private static int PAGE_NUM = 1;

    private int pageNum;
    private int pageSize;

    private HashMap param;
}
