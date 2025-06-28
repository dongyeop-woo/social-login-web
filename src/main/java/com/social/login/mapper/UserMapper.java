package com.social.login.mapper;

import com.social.login.dto.UserDto;
import com.social.login.entity.User;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class UserMapper {
    public static User toEntity(UserDto dto) {
        return User.builder()
                .id(dto.getId())
                .name(dto.getName())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .role(dto.getRole())
                .socialType(dto.getSocialType())
                .socialId(dto.getSocialId())
                .build();
    }

    public static UserDto toDto(User entity) {
        return UserDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .role(entity.getRole())
                .socialType(entity.getSocialType())
                .socialId(entity.getSocialId())
                .build();
    }
} 