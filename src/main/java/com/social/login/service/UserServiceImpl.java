package com.social.login.service;

import com.social.login.dto.UserDto;
import com.social.login.entity.User;
import com.social.login.enums.Role;
import com.social.login.enums.SocialType;
import com.social.login.mapper.UserMapper;
import com.social.login.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserServiceImpl extends DefaultOAuth2UserService implements UserService {
    private final UserRepository userRepository;

    @Override
    @Transactional
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        Map<String, Object> attributes = oAuth2User.getAttributes();

        UserDto userDto = findOrCreateUser(attributes);
        saveUser(userDto);

        return oAuth2User;
    }

    @Override
    @Transactional
    public UserDto findOrCreateUser(Map<String, Object> attributes) {
        String email = getAttribute(attributes, "email");
        String name = getAttribute(attributes, "name");
        String socialId = getAttribute(attributes, "sub");

        return userRepository.findByEmail(email)
                .map(UserMapper::toDto)
                .map(existingDto -> existingDto.toBuilder()
                        .name(name)
                        .email(email)
                        .build())
                .orElseGet(() -> createNewUserDto(name, email, socialId));
    }

    @Override
    public String getAttribute(Map<String, Object> attributes, String key) {
        Object value = attributes.get(key);
        return value != null ? value.toString() : null;
    }


    @Override
    @Transactional
    public UserDto createNewUserDto(String name, String email, String socialId) {
        return UserDto.builder()
                .name(name)
                .email(email)
                .role(Role.USER)
                .socialType(SocialType.GOOGLE)
                .socialId(socialId)
                .build();
    }

    @Override
    @Transactional
    public void saveUser(UserDto userDto) {
        User user = UserMapper.toEntity(userDto);
        userRepository.save(user);
    }
} 