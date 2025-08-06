package com.multiserviceplatform.service;

import com.multiserviceplatform.dto.MessageDTO;

import java.util.List;

public interface MessageService {
    MessageDTO createMessage(MessageDTO messageDTO);
    MessageDTO getMessageById(Integer messageId);
    List<MessageDTO> getMessagesByJobId(Integer jobId);
}
