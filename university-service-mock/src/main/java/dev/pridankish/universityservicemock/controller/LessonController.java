package dev.pridankish.universityservicemock.controller;

import dev.pridankish.universityservicemock.dto.ResponseLessonDto;
import dev.pridankish.universityservicemock.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/lessons")
@RequiredArgsConstructor
public class LessonController {

    private final LessonService lessonService;

    @GetMapping("/get_lessons_by_date/{date}")
    public ResponseEntity<List<ResponseLessonDto>> getLessonsByDate(LocalDateTime date) {
        var lessonsByDateAndGroup = lessonService.getLessonsByDate(date);

        return ResponseEntity.ok(lessonsByDateAndGroup);
    }
}
