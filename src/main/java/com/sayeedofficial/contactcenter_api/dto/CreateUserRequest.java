package com.sayeedofficial.contactcenter_api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sayeedofficial.contactcenter_api.common.validation.ValidTimeZone;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = false)
public record CreateUserRequest(

        @NotBlank(message = "Full name is required")
        @Size(min = 2, max = 100, message = "Full name must be between 2 and 100 characters")
        @Pattern(
                regexp = "^[\\p{L}][\\p{L}\\p{M} .'-]*$",
                message = "Full name can contain letters, spaces, apostrophes, hyphens, and dots only"
        )
        String fullName,

        @NotBlank(message = "Email is required")
        @Email(message = "Email must be valid")
        @Size(max = 254, message = "Email must not exceed 254 characters")
        String email,

        @NotBlank(message = "Phone number is required")
        @Pattern(
                regexp = "^\\+[1-9]\\d{7,14}$",
                message = "Phone number must be in E.164 format, for example +919876543210"
        )
        String phoneNumber,

        @Size(max = 50, message = "Employee code must not exceed 50 characters")
        @Pattern(
                regexp = "^[A-Za-z0-9._-]*$",
                message = "Employee code can contain letters, numbers, dots, underscores, and hyphens only"
        )
        String employeeCode,

        @Size(max = 128, message = "External identity ID must not exceed 128 characters")
        @Pattern(
                regexp = "^[A-Za-z0-9:._@/-]*$",
                message = "External identity ID contains invalid characters"
        )
        String externalIdentityId,

        @NotEmpty(message = "At least one role is required")
        @Size(max = 3, message = "A user cannot have more than 3 roles")
        Set<@NotNull(message = "Role cannot be null") UserRole> roles,

        @NotBlank(message = "Time zone is required")
        @ValidTimeZone
        String timeZone,

        @Size(max = 50, message = "A user cannot be assigned more than 50 skills")
        Set<@NotNull(message = "Skill ID cannot be null") UUID> skillIds,

        @Size(max = 20, message = "Attributes cannot contain more than 20 entries")
        Map<
                @NotBlank(message = "Attribute key cannot be blank")
                @Size(max = 50, message = "Attribute key must not exceed 50 characters")
                @Pattern(
                        regexp = "^[A-Za-z0-9_.-]+$",
                        message = "Attribute key can contain letters, numbers, dots, underscores, and hyphens only"
                )
                String,

                @NotBlank(message = "Attribute value cannot be blank")
                @Size(max = 255, message = "Attribute value must not exceed 255 characters")
                String
        > attributes

) {

    private static final java.util.regex.Pattern MULTIPLE_WHITESPACE =
            java.util.regex.Pattern.compile("\\s+");

    public CreateUserRequest {
        fullName = normalizeWhitespace(fullName);
        email = normalizeEmail(email);
        phoneNumber = normalize(phoneNumber);
        employeeCode = normalizeOptional(employeeCode);
        externalIdentityId = normalizeOptional(externalIdentityId);
        timeZone = normalize(timeZone);

        roles = roles == null
                ? Set.of()
                : Collections.unmodifiableSet(new LinkedHashSet<>(roles));

        skillIds = skillIds == null
                ? Set.of()
                : Collections.unmodifiableSet(new LinkedHashSet<>(skillIds));

        attributes = attributes == null
                ? Map.of()
                : Collections.unmodifiableMap(new LinkedHashMap<>(attributes));
    }

    private static String normalize(String value) {
        return value == null ? null : value.strip();
    }

    private static String normalizeOptional(String value) {
        if (value == null || value.isBlank()) {
            return null;
        }

        return value.strip();
    }

    private static String normalizeWhitespace(String value) {
        if (value == null) {
            return null;
        }

        return MULTIPLE_WHITESPACE.matcher(value.strip()).replaceAll(" ");
    }

    private static String normalizeEmail(String value) {
        if (value == null) {
            return null;
        }

        return value.strip().toLowerCase(Locale.ROOT);
    }
}