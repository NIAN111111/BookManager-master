// BookManager-master/src/main/java/com/zzz/bms/web/MessageController.java
package com.zzz.bms.web;

import com.zzz.bms.common.ResponseResult;
import com.zzz.bms.entity.Message;
import com.zzz.bms.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping("/create")
    public ResponseResult createMessage(@RequestBody @Valid Message message) {
        if (message.getUserId() == null) {
            return ResponseResult.error("用户ID不能为空");
        }
        messageService.addMessage(message);
        return ResponseResult.success();
    }

    @GetMapping("/list")
    public ResponseResult listMessages() {
        return ResponseResult.success(messageService.getAllMessages());
    }
}