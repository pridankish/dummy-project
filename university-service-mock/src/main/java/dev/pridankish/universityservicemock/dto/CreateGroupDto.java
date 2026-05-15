package dev.pridankish.universityservicemock.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateGroupDto(
        @Size(min = 1, max = 20)
        @NotNull
        String name,

        @NotNull
        Long universityId) {
}
