package com.multiserviceplatform.service.impl;

import com.multiserviceplatform.dto.ProfileDTO;
import com.multiserviceplatform.model.Profile;
import com.multiserviceplatform.model.User;
import com.multiserviceplatform.repository.ProfileRepository;
import com.multiserviceplatform.repository.UserRepository;
import com.multiserviceplatform.service.ProfileService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProfileServiceImpl implements ProfileService {
    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;
    //private final ModelMapper modelMapper;

    @Autowired
    public ProfileServiceImpl(ProfileRepository profileRepository, UserRepository userRepository) {
        this.profileRepository = profileRepository;
        this.userRepository = userRepository;
        //this.modelMapper = modelMapper;
    }

    @Override
    public ProfileDTO createProfile(Integer userId, ProfileDTO profileDTO) {
        ModelMapper modelMapper = new ModelMapper();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userId));
        if (profileRepository.findByUser_UserId(userId).isPresent()) {
            throw new IllegalArgumentException("Profile already exists for user ID: " + userId);
        }
        Profile profile = modelMapper.map(profileDTO, Profile.class);
        profile.setUser(user);
        Profile savedProfile = profileRepository.save(profile);
        user.setProfile(profile);
        userRepository.save(user);
        return modelMapper.map(savedProfile, ProfileDTO.class);
    }

    @Override
    public ProfileDTO getProfileById(Integer profileId) {
        ModelMapper modelMapper = new ModelMapper();
        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new IllegalArgumentException("Profile not found with ID: " + profileId));
        return modelMapper.map(profile, ProfileDTO.class);
    }

    @Override
    public ProfileDTO getProfileByUserId(Integer userId) {
        ModelMapper modelMapper = new ModelMapper();
        Profile profile = profileRepository.findByUser_UserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("Profile not found for user ID: " + userId));
        return modelMapper.map(profile, ProfileDTO.class);
    }

    @Override
    public ProfileDTO updateProfile(Integer profileId, ProfileDTO profileDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new IllegalArgumentException("Profile not found with ID: " + profileId));
        modelMapper.map(profileDTO, profile);
        profile.setProfileId(profileId);
        Profile updatedProfile = profileRepository.save(profile);
        return modelMapper.map(updatedProfile, ProfileDTO.class);
    }

    @Override
    public void deleteProfile(Integer profileId) {
        if (!profileRepository.existsById(profileId)) {
            throw new IllegalArgumentException("Profile not found with ID: " + profileId);
        }
        profileRepository.deleteById(profileId);
    }
}
