package com.multiserviceplatform.service;

import com.multiserviceplatform.dto.ProfileDTO;

public interface ProfileService {
    ProfileDTO createProfile(Integer userId, ProfileDTO profileDTO);
    ProfileDTO getProfileById(Integer profileId);
    ProfileDTO getProfileByUserId(Integer userId);
    ProfileDTO updateProfile(Integer profileId, ProfileDTO profileDTO);
    void deleteProfile(Integer profileId);
}
