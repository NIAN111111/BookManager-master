package com.zzz.bms.service.impl;

import com.zzz.bms.entity.UserBorrowStatistics;
import com.zzz.bms.service.UserBorrowStatisticsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserBorrowStatisticsServiceImpl implements UserBorrowStatisticsService {

    @Override
    public List<UserBorrowStatistics> getBorrowStatistics() {
        // 这里可以添加实际的统计逻辑，暂时模拟数据
        List<UserBorrowStatistics> statisticsList = new ArrayList<>();
        UserBorrowStatistics stat1 = new UserBorrowStatistics();
        stat1.setUsername("user1");
        stat1.setBorrowCount(5);
        statisticsList.add(stat1);

        UserBorrowStatistics stat2 = new UserBorrowStatistics();
        stat2.setUsername("user2");
        stat2.setBorrowCount(3);
        statisticsList.add(stat2);

        return statisticsList;
    }
}