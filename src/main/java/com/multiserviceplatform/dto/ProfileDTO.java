package com.multiserviceplatform.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDTO {
    private Integer profileId;
    private Integer userId;
    @NotBlank(message = "Name is mandatory")
    private String name;
    private String profilePicture;
    private String address;
    private String certifications;
    private String servicesOffered;
    @NotBlank(message = "Location is mandatory")
    private String location;
}
