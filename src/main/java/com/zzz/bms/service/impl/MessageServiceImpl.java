// BookManager-master/src/main/java/com/zzz/bms/service/impl/MessageServiceImpl.java
package com.zzz.bms.service.impl;

import com.zzz.bms.entity.Message;
import com.zzz.bms.mapper.MessageMapper;
import com.zzz.bms.mapper.UserMapper;
import com.zzz.bms.model.User;
import com.zzz.bms.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private UserMapper userMapper;

    private static final Logger logger = LoggerFactory.getLogger(MessageServiceImpl.class);

    @Override
    public int addMessage(Message message) {
        logger.info("添加留言: {}", message);
        try {
            return messageMapper.addMessage(message);
        } catch (Exception e) {
            logger.error("添加留言失败: {}", e.getMessage(), e);
            throw new RuntimeException("添加留言失败", e);
        }
    }

    @Override
    public List<Message> getAllMessages() {
        List<Message> messages = messageMapper.getAllMessages();
        for (Message message : messages) {
            Integer userId = message.getUserId();
            User user = userMapper.selectByPrimaryKey(userId);
            if (user != null) {
                message.setUserName(user.getUsername());
            }
        }
        return messages;
    }
}