package dev.pridankish.universityservicemock.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateUniversityDto(
        @Size(min = 1, max = 200)
        @NotNull
        String name) {
}
