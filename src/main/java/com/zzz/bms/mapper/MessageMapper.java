package com.zzz.bms.mapper;

import com.zzz.bms.entity.Message;

import java.util.List;

public interface MessageMapper {
    // 添加留言
    int addMessage(Message message);

    // 获取所有留言
    List<Message> getAllMessages();
}