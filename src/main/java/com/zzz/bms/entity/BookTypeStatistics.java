package com.zzz.bms.entity;

import lombok.Data;

import java.util.Date;

/**
 * 图书类型统计
 */
@Data
public class BookTypeStatistics {
    private String typeName;
    private Integer count;
}

