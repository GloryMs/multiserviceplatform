package com.multiserviceplatform.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "profiles")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer profileId;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NotBlank(message = "Name is mandatory")
    private String name;

    private String profilePicture;

    private String address;

    private String certifications; // Provider only

    private String servicesOffered; // Provider only

    @NotBlank(message = "Location is mandatory")
    private String location;

    @NotNull
    private LocalDateTime updatedAt = LocalDateTime.now();

}