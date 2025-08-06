package com.multiserviceplatform.service.impl;

import com.multiserviceplatform.dto.MessageDTO;
import com.multiserviceplatform.model.JobOrder;
import com.multiserviceplatform.model.Message;
import com.multiserviceplatform.model.User;
import com.multiserviceplatform.repository.JobOrderRepository;
import com.multiserviceplatform.repository.MessageRepository;
import com.multiserviceplatform.repository.UserRepository;
import com.multiserviceplatform.service.MessageService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;
    private final JobOrderRepository jobOrderRepository;
    private final UserRepository userRepository;
    //private final ModelMapper modelMapper;

    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository, JobOrderRepository jobOrderRepository,
                              UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.jobOrderRepository = jobOrderRepository;
        this.userRepository = userRepository;
        //this.modelMapper = modelMapper;
    }

    @Override
    public MessageDTO createMessage(MessageDTO messageDTO) {
        ModelMapper modelMapper = new ModelMapper();
        JobOrder job = jobOrderRepository.findById(messageDTO.getJobId())
                .orElseThrow(() -> new IllegalArgumentException("Job not found with ID: " + messageDTO.getJobId()));
        User sender = userRepository.findById(messageDTO.getSenderId())
                .orElseThrow(() -> new IllegalArgumentException("Sender not found with ID: " + messageDTO.getSenderId()));
        User receiver = userRepository.findById(messageDTO.getReceiverId())
                .orElseThrow(() -> new IllegalArgumentException("Receiver not found with ID: " + messageDTO.getReceiverId()));
        Message message = modelMapper.map(messageDTO, Message.class);
        message.setJob(job);
        message.setSender(sender);
        message.setReceiver(receiver);
        message.setIsFlagged(true);
        message.setSentAt(LocalDateTime.now());
        Message savedMessage = messageRepository.save(message);
        return modelMapper.map(savedMessage, MessageDTO.class);
    }

    @Override
    public MessageDTO getMessageById(Integer messageId) {
        ModelMapper modelMapper = new ModelMapper();
        Message message = messageRepository.findById(messageId)
                .orElseThrow(() -> new IllegalArgumentException("Message not found with ID: " + messageId));
        return modelMapper.map(message, MessageDTO.class);
    }

    @Override
    public List<MessageDTO> getMessagesByJobId(Integer jobId) {
        ModelMapper modelMapper = new ModelMapper();
        return messageRepository.findByJob_JobId(jobId).stream()
                .map(message -> modelMapper.map(message, MessageDTO.class))
                .collect(Collectors.toList());
    }
}
