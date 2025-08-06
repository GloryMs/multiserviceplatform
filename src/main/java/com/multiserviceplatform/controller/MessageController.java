package com.multiserviceplatform.controller;

import com.multiserviceplatform.dto.MessageDTO;
import com.multiserviceplatform.service.MessageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/messages")
public class MessageController {
    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('SEEKER', 'PROVIDER')")
    public ResponseEntity<MessageDTO> createMessage(@Valid @RequestBody MessageDTO messageDTO) {
        MessageDTO createdMessage = messageService.createMessage(messageDTO);
        return ResponseEntity.status(201).body(createdMessage);
    }

    @GetMapping("/{messageId}")
    @PreAuthorize("hasAnyRole('SEEKER', 'PROVIDER', 'ADMIN')")
    public ResponseEntity<MessageDTO> getMessageById(@PathVariable Integer messageId) {
        MessageDTO message = messageService.getMessageById(messageId);
        return ResponseEntity.ok(message);
    }

    @GetMapping("/job/{jobId}")
    @PreAuthorize("hasAnyRole('SEEKER', 'PROVIDER', 'ADMIN')")
    public ResponseEntity<List<MessageDTO>> getMessagesByJobId(@PathVariable Integer jobId) {
        List<MessageDTO> messages = messageService.getMessagesByJobId(jobId);
        return ResponseEntity.ok(messages);
    }
}
