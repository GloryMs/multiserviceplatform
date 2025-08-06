package com.multiserviceplatform.service;

import com.multiserviceplatform.dto.ProfileDTO;
import com.multiserviceplatform.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {
    UserDTO createUser(UserDTO userDTO);
    UserDTO getUserById(Integer userId);
    UserDTO updateUser(Integer userId, UserDTO userDTO) ;
    void deleteUser(Integer userId) ;
    List<UserDTO> getAllUsers() ;
    ProfileDTO createProfile(Integer userId, ProfileDTO profileDTO) ;
    ProfileDTO getProfileByUserId(Integer userId) ;
}
