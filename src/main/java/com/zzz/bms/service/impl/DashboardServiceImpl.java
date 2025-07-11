package com.zzz.bms.service.impl;

import com.zzz.bms.entity.BookTypeStatistics;
import com.zzz.bms.entity.BorrowTimeStatistics;
import com.zzz.bms.mapper.BookTypeMapper;
import com.zzz.bms.mapper.BorrowMapper;
import com.zzz.bms.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashboardServiceImpl implements DashboardService {

    @Autowired
    private BookTypeMapper bookTypeMapper;

    @Autowired
    private BorrowMapper borrowMapper;

    @Override
    public List<BookTypeStatistics> getBookTypeStatistics() {
        return bookTypeMapper.getBookTypeStatistics();
    }

    @Override
    public List<BorrowTimeStatistics> getBorrowTimeStatistics() {
        return borrowMapper.getBorrowTimeStatistics();
    }

    @Override
    public List<Double> getAllBookPrices() {
        return borrowMapper.getAllBookPrices();
    }
}