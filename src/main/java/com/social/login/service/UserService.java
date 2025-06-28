package com.social.login.service;

import com.social.login.dto.UserDto;

import java.util.Map;

public interface UserService {
    UserDto findOrCreateUser(Map<String, Object> attributes);
    String getAttribute(Map<String, Object> attributes, String key);
    UserDto createNewUserDto(String name, String email, String socialId);
    void saveUser(UserDto userDto);
}
