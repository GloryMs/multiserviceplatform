package com.multiserviceplatform.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
public class NotificationDTO {
    private Integer notificationId;
    @NotNull(message = "User ID is mandatory")
    private Integer userId;
    private Integer jobId;
    @NotBlank(message = "Type is mandatory")
    @Pattern(regexp = "Push|Email|SMS", message = "Invalid notification type")
    private String type;
    @NotBlank(message = "Content is mandatory")
    private String content;
    private LocalDateTime sentAt;
    private Boolean isRead;

    public void setContent(String content) {
        this.content = content;
    }

    public void setRead(Boolean read) {
        isRead = read;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public void setNotificationId(Integer notificationId) {
        this.notificationId = notificationId;
    }

    public void setSentAt(LocalDateTime sentAt) {
        this.sentAt = sentAt;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public Boolean getRead() {
        return isRead;
    }

    public Integer getJobId() {
        return jobId;
    }

    public Integer getNotificationId() {
        return notificationId;
    }

    public LocalDateTime getSentAt() {
        return sentAt;
    }

    public String getType() {
        return type;
    }

    public Integer getUserId() {
        return userId;
    }
}
