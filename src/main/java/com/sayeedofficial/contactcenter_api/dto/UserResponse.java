package com.sayeedofficial.contactcenter_api.dto;

import java.time.Instant;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public record UserResponse(

        UUID id,

        String fullName,

        String email,

        String phoneNumber,

        String employeeCode,

        String externalIdentityId,

        Set<UserRole> roles,

        String timeZone,

        Set<UUID> skillIds,

        Map<String, String> attributes,

        UserStatus status,

        Instant createdAt,

        Instant updatedAt

) {
}
