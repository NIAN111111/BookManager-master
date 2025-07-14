// BookManager-master/src/main/java/com/zzz/bms/service/MessageService.java
package com.zzz.bms.service;

import com.zzz.bms.entity.Message;

import java.util.List;

public interface MessageService {
    // 添加留言
    int addMessage(Message message);

    // 获取所有留言
    List<Message> getAllMessages();
}