package com.zzz.bms.service;

import com.zzz.bms.entity.UserBorrowStatistics;

import java.util.List;

public interface UserBorrowStatisticsService {
    List<UserBorrowStatistics> getBorrowStatistics();
}