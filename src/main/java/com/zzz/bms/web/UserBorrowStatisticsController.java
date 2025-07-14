package com.zzz.bms.web;

import com.zzz.bms.entity.UserBorrowStatistics;
import com.zzz.bms.service.UserBorrowStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/statistics")
public class UserBorrowStatisticsController {

    @Autowired
    private UserBorrowStatisticsService userBorrowStatisticsService;

    @GetMapping
    public List<UserBorrowStatistics> getBorrowStatistics() {
        return userBorrowStatisticsService.getBorrowStatistics();
    }
}