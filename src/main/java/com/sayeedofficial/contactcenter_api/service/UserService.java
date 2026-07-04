package com.sayeedofficial.contactcenter_api.service;

import com.sayeedofficial.contactcenter_api.dto.CreateUserRequest;
import com.sayeedofficial.contactcenter_api.dto.UserStatus;
import com.sayeedofficial.contactcenter_api.entity.UserEntity;
import com.sayeedofficial.contactcenter_api.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public void createUser(CreateUserRequest request) {

        if (userRepository.existsByEmail(request.email())) {
            throw new IllegalArgumentException("Email already exists");
        }

        if (userRepository.existsByPhoneNumber(request.phoneNumber())) {
            throw new IllegalArgumentException("Phone number already exists");
        }

        if (userRepository.existsByEmployeeCode(request.employeeCode())) {
            throw new IllegalArgumentException("Employee code already exists");
        }

        UserEntity user = new UserEntity(request.fullName(), request.email(), request.phoneNumber(),
                request.employeeCode(), request.externalIdentityId(), request.timeZone(),
                UserStatus.ACTIVE);

        userRepository.save(user);
    }
}
