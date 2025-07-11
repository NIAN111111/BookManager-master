package com.zzz.bms.mapper;

import com.zzz.bms.model.BookInfo;
import com.zzz.bms.model.BookType;
import org.apache.ibatis.annotations.Param;
import com.zzz.bms.entity.BookTypeStatistics;
import com.zzz.bms.entity.BorrowTimeStatistics;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface BookTypeMapper {
    int deleteByPrimaryKey(Integer booktypeid);

    int insert(BookType record);

    int insertSelective(BookType record);

    BookType selectByPrimaryKey(Integer booktypeid);

    int updateByPrimaryKeySelective(BookType record);

    int updateByPrimaryKey(BookType record);

    List<BookType> selectAllByLimit(@Param("begin") Integer begin, @Param("size") Integer size);

    Integer selectCount();

    List<BookType> selectAll();

    int selectCountBySearch(Map<String, Object> searchParam);

    List<BookType> selectBySearch(Map<String, Object> searchParam);
    @Select("SELECT bt.bookTypeName as typeName, COUNT(bi.bookId) as count " +
            "FROM book_type bt " +
            "LEFT JOIN book_info bi ON bt.bookTypeId = bi.bookTypeId " +
            "GROUP BY bt.bookTypeId")
    List<BookTypeStatistics> getBookTypeStatistics();
}