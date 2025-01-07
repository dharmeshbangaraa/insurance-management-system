package com.insuarance.dto;

import java.time.LocalDate;

public record CustomerDto(
         Long id,
         String firstName,
         String lastName,
         String contactNumber,
         String dateOfBirth,
         String emailId,
         String password
) {
}
