// BookManager-master/src/main/java/com/zzz/bms/mapper/MessageMapper.java
package com.zzz.bms.mapper;

import com.zzz.bms.entity.Message;
import java.util.List;

public interface MessageMapper {
    // 获取所有留言
    List<Message> getAllMessages();

    // 添加留言
    int addMessage(Message message);
}