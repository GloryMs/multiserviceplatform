package com.multiserviceplatform.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
public class MessageDTO {
    private Integer messageId;
    @NotNull(message = "Job ID is mandatory")
    private Integer jobId;
    @NotNull(message = "Sender ID is mandatory")
    private Integer senderId;
    @NotNull(message = "Receiver ID is mandatory")
    private Integer receiverId;
    @NotBlank(message = "Message content is mandatory")
    private String content;
    private LocalDateTime sentAt;
    private Boolean isFlagged;

    public void setContent(String content) {
        this.content = content;
    }

    public void setFlagged(Boolean flagged) {
        isFlagged = flagged;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public void setReceiverId(Integer receiverId) {
        this.receiverId = receiverId;
    }

    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    public void setSentAt(LocalDateTime sentAt) {
        this.sentAt = sentAt;
    }

    public String getContent() {
        return content;
    }

    public Boolean getFlagged() {
        return isFlagged;
    }

    public Integer getJobId() {
        return jobId;
    }

    public Integer getMessageId() {
        return messageId;
    }

    public Integer getReceiverId() {
        return receiverId;
    }

    public Integer getSenderId() {
        return senderId;
    }

    public LocalDateTime getSentAt() {
        return sentAt;
    }
}
