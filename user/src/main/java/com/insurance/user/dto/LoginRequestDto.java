package com.insurance.user.dto;

public record LoginRequestDto(
        String emailId,
        String password
) {
}
