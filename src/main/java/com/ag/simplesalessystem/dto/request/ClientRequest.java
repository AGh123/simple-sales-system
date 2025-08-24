package com.ag.simplesalessystem.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ClientRequest(
        @NotBlank @Size(max = 100) String firstName,
        @NotBlank @Size(max = 100) String lastName,
        @NotBlank
        @Pattern(regexp = "^[0-9]{8,15}$", message = "Mobile must be 8–15 digits")
        String mobile
) {
}
