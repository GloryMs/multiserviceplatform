package com.multiserviceplatform.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Data
@Getter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email must be valid")
    @Column(unique = true)
    private String email;

    @Size(min = 10, max = 15, message = "Phone number must be between 10 and 15 characters")
    private String phone;

    @NotBlank(message = "Role is mandatory")
    @Pattern(regexp = "Seeker|Provider|Admin", message = "Role must be Seeker, Provider, or Admin")
    private String role;

    @NotBlank(message = "Password hash is mandatory")
    private String passwordHash;

    @NotNull
    @Column(updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Profile profile;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private ServiceProvider serviceProvider;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Admin admin;

    public User(String email, String passwordHash, String phone, String role) {
        this.email = email;
        this.passwordHash = passwordHash;
        this.phone = phone;
        this.role = role;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setServiceProvider(ServiceProvider serviceProvider) {
        this.serviceProvider = serviceProvider;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public String getPhone() {
        return phone;
    }

    public Profile getProfile() {
        return profile;
    }

    public String getRole() {
        return role;
    }
}