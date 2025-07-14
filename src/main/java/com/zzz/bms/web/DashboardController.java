package com.zzz.bms.web;

import com.zzz.bms.entity.BookTypeStatistics;
import com.zzz.bms.entity.BorrowTimeStatistics;
import com.zzz.bms.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    /**
     * 获取仪表盘数据
     */
    @GetMapping("/data")
    public Map<String, Object> getDashboardData() {
        Map<String, Object> result = new HashMap<>();

        // 获取图书类型统计
        List<BookTypeStatistics> bookTypeStatistics = dashboardService.getBookTypeStatistics();

        // 获取借阅时间统计
        List<BorrowTimeStatistics> borrowTimeStatistics = dashboardService.getBorrowTimeStatistics();

        // 获取图书价格
        List<Double> bookPrices = dashboardService.getAllBookPrices();

        result.put("bookTypeData", bookTypeStatistics);
        result.put("borrowTimeData", borrowTimeStatistics);
        result.put("bookPrices", bookPrices);

        return result;
    }
}