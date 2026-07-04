package com.sayeedofficial.contactcenter_api.repository;

import com.sayeedofficial.contactcenter_api.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;


public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    boolean existsByEmail(String email);

    boolean existsByPhoneNumber(String phoneNumber);

    boolean existsByEmployeeCode(String employeeCode);

    Optional<UserEntity> findByEmail(String email);

    Optional<UserEntity> findByEmployeeCode(String employeeCode);
}