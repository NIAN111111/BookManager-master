// src/main/java/com/zzz/bms/entity/DashboardData.java
package com.zzz.bms.entity;

import java.util.List;

public class DashboardData {
    private List<BookTypeData> bookTypeData; // 图书类型数据
    private List<Double> bookPrices; // 图书价格数据
    private List<BorrowTimeData> borrowTimeData; // 借阅时间数据

    // Getters and Setters
    public List<BookTypeData> getBookTypeData() {
        return bookTypeData;
    }

    public void setBookTypeData(List<BookTypeData> bookTypeData) {
        this.bookTypeData = bookTypeData;
    }

    public List<Double> getBookPrices() {
        return bookPrices;
    }

    public void setBookPrices(List<Double> bookPrices) {
        this.bookPrices = bookPrices;
    }

    public List<BorrowTimeData> getBorrowTimeData() {
        return borrowTimeData;
    }

    public void setBorrowTimeData(List<BorrowTimeData> borrowTimeData) {
        this.borrowTimeData = borrowTimeData;
    }

    // 内部类：图书类型数据
    public static class BookTypeData {
        private String typeName;
        private Integer count;

        // 构造方法、Getters and Setters
        public BookTypeData(String typeName, Integer count) {
            this.typeName = typeName;
            this.count = count;
        }

        public String getTypeName() { return typeName; }
        public void setTypeName(String typeName) { this.typeName = typeName; }
        public Integer getCount() { return count; }
        public void setCount(Integer count) { this.count = count; }
    }

    // 内部类：借阅时间数据
    public static class BorrowTimeData {
        private String month;
        private Integer count;

        // 构造方法、Getters and Setters
        public BorrowTimeData(String month, Integer count) {
            this.month = month;
            this.count = count;
        }

        public String getMonth() { return month; }
        public void setMonth(String month) { this.month = month; }
        public Integer getCount() { return count; }
        public void setCount(Integer count) { this.count = count; }
    }
}