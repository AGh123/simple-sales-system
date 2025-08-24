package com.ag.simplesalessystem.dto.response;

public record ClientResponse(
        Long id,
        String firstName,
        String lastName,
        String mobile
) {
}
