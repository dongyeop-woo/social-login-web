package com.social.login.dto;

import com.social.login.enums.Role;
import com.social.login.enums.SocialType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private String password;
    private Role role;
    private SocialType socialType;
    private String socialId;
} 