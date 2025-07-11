package com.zzz.bms.entity;

import lombok.Data;

@Data
public class BorrowTimeStatistics {
    private String month;  // 格式: yyyy-MM
    private Integer count;
}