package com.zzz.bms.mapper;

import com.zzz.bms.model.Borrow;
import org.apache.ibatis.annotations.Param;
import com.zzz.bms.entity.BorrowTimeStatistics;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface BorrowMapper {
    int deleteByPrimaryKey(Integer borrowid);

    int insert(Borrow record);

    int insertSelective(Borrow record);

    Borrow selectByPrimaryKey(Integer borrowid);

    int updateByPrimaryKeySelective(Borrow record);

    int updateByPrimaryKey(Borrow record);

    List<Borrow> selectAllByLimit(@Param("begin") Integer begin, @Param("size") Integer size);

    Integer selectCount();

    int selectCountBySearch(Map<String, Object> searchParam);

    List<Borrow> selectBySearch(Map<String, Object> searchParam);

    Integer selectCountByReader(Integer userid);

    List<Borrow> selectAllByLimitByReader(@Param("begin") Integer begin, @Param("size") Integer size, @Param("userid") Integer userid);
    @Select("SELECT DATE_FORMAT(borrowTime, '%Y-%m') as month, COUNT(borrowId) as count " +
            "FROM borrow " +
            "GROUP BY DATE_FORMAT(borrowTime, '%Y-%m') " +
            "ORDER BY month")
    List<BorrowTimeStatistics> getBorrowTimeStatistics();

    /**
     * 获取所有图书价格
     */
    @Select("SELECT bookPrice FROM book_info")
    List<Double> getAllBookPrices();
}




