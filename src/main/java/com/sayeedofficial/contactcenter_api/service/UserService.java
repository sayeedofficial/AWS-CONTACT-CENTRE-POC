package com.sayeedofficial.contactcenter_api.service;

import com.sayeedofficial.contactcenter_api.dto.CreateUserRequest;
import com.sayeedofficial.contactcenter_api.dto.UserResponse;
import com.sayeedofficial.contactcenter_api.dto.UserStatus;
import com.sayeedofficial.contactcenter_api.entity.UserEntity;
import com.sayeedofficial.contactcenter_api.exception.DuplicateUserException;
import com.sayeedofficial.contactcenter_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public UserResponse createUser(CreateUserRequest request) {

        if (userRepository.existsByEmail(request.email())) {
            throw new DuplicateUserException("Email already exists");
        }

        if (userRepository.existsByPhoneNumber(request.phoneNumber())) {
            throw new DuplicateUserException("Phone number already exists");
        }

        if (userRepository.existsByEmployeeCode(request.employeeCode())) {
            throw new DuplicateUserException("Employee code already exists");
        }

        UserEntity user = new UserEntity(
                request.fullName(),
                request.email(),
                request.phoneNumber(),
                request.employeeCode(),
                request.externalIdentityId(),
                request.timeZone(),
                UserStatus.ACTIVE
        );

        UserEntity savedUser = userRepository.save(user);

        return toUserResponse(savedUser);
    }

    private UserResponse toUserResponse(UserEntity user) {

        return new UserResponse(
                user.getId(),
                user.getFullName(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getEmployeeCode(),
                user.getExternalIdentityId(),
                Collections.emptySet(),
                user.getTimeZone(),
                Collections.emptySet(),
                Collections.emptyMap(),
                user.getStatus(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }
}