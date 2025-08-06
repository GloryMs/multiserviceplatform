package com.multiserviceplatform.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "admins")
@Data
@Getter
@Setter
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer adminId;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NotBlank(message = "Access level is mandatory")
    private String accessLevel;

    public void setAccessLevel(String accessLevel) {
        this.accessLevel = accessLevel;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAccessLevel() {
        return accessLevel;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public User getUser() {
        return user;
    }
}
