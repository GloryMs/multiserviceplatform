package com.multiserviceplatform.service.impl;

import com.multiserviceplatform.dto.UserDTO;
import com.multiserviceplatform.dto.ProfileDTO;
import com.multiserviceplatform.model.User;
import com.multiserviceplatform.model.Profile;
import com.multiserviceplatform.repository.UserRepository;
import com.multiserviceplatform.repository.ProfileRepository;
import com.multiserviceplatform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ProfileRepository profileRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.profileRepository = profileRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }
        User user = modelMapper.map(userDTO, User.class);
        user.setPasswordHash(hashPassword(userDTO.getPassword())); // Assume a password hashing utility
        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserDTO.class);
    }

    @Override
    public UserDTO getUserById(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public UserDTO updateUser(Integer userId, UserDTO userDTO) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        if (!user.getEmail().equals(userDTO.getEmail()) && userRepository.existsByEmail(userDTO.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }
        modelMapper.map(userDTO, user);
        if (userDTO.getPassword() != null) {
            user.setPasswordHash(hashPassword(userDTO.getPassword()));
        }
        User updatedUser = userRepository.save(user);
        return modelMapper.map(updatedUser, UserDTO.class);
    }

    @Override
    public void deleteUser(Integer userId) {
        if (!userRepository.existsById(userId)) {
            throw new IllegalArgumentException("User not found");
        }
        userRepository.deleteById(userId);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProfileDTO createProfile(Integer userId, ProfileDTO profileDTO) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        if (profileRepository.findByUser_UserId(userId).isPresent()) {
            throw new IllegalArgumentException("Profile already exists for this user");
        }
        Profile profile = modelMapper.map(profileDTO, Profile.class);
        profile.setUser(user);
        Profile savedProfile = profileRepository.save(profile);
        return modelMapper.map(savedProfile, ProfileDTO.class);
    }

    @Override
    public ProfileDTO getProfileByUserId(Integer userId) {
        Profile profile = profileRepository.findByUser_UserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("Profile not found"));
        return modelMapper.map(profile, ProfileDTO.class);
    }

    private String hashPassword(String password) {
        // Placeholder for password hashing logic (e.g., BCrypt)
        return password; // Replace with actual hashing
    }
}
