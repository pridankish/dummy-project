package dev.pridankish.universityservicemock.dto;

import dev.pridankish.universityservicemock.entity.enums.LessonType;

import java.time.LocalDateTime;

public record ResponseLessonDto(
        Long id,
        String auditoryNumber,
        LessonType lessonType,
        LocalDateTime startTime,
        Long groupId) {
}
