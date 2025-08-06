package com.multiserviceplatform.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "notifications")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer notificationId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private JobOrder job;

    @NotBlank(message = "Type is mandatory")
    @Pattern(regexp = "Push|Email|SMS", message = "Invalid notification type")
    private String type;

    @NotBlank(message = "Content is mandatory")
    private String content;

    @NotNull
    @Column(updatable = false)
    private LocalDateTime sentAt = LocalDateTime.now();

    @NotNull
    private Boolean isRead = false;

}
