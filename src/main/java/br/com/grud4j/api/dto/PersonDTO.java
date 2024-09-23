package br.com.grud4j.api.dto;

import lombok.Builder;

@Builder
public record PersonDTO(
        String name,
        String birthDay
) {
}
