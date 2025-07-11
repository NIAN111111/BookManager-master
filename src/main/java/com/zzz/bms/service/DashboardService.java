package com.zzz.bms.service;

import com.zzz.bms.entity.BookTypeStatistics;
import com.zzz.bms.entity.BorrowTimeStatistics;

import java.util.List;

public interface DashboardService {
    /**
     * 获取图书类型统计
     */
    List<BookTypeStatistics> getBookTypeStatistics();

    /**
     * 获取借阅时间统计
     */
    List<BorrowTimeStatistics> getBorrowTimeStatistics();

    /**
     * 获取所有图书价格
     */
    List<Double> getAllBookPrices();
}