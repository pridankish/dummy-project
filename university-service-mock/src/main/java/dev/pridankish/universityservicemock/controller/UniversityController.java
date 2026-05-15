package dev.pridankish.universityservicemock.controller;

import dev.pridankish.universityservicemock.dto.CreateUniversityDto;
import dev.pridankish.universityservicemock.dto.ResponseUniversityDto;
import dev.pridankish.universityservicemock.service.UniversityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/universities")
@RequiredArgsConstructor
@Slf4j
public class UniversityController {

    private final UniversityService universityService;

    @GetMapping("/{id}")
    public ResponseEntity<ResponseUniversityDto> getUniversityById(
            @PathVariable("id") Long id
    ) {
        var universityResponse = universityService.getUniversityById(id);
        return ResponseEntity
                .ok(universityResponse);
    }

    @PostMapping
    public ResponseEntity<ResponseUniversityDto> createUniversity(
            @Valid @RequestBody CreateUniversityDto createUniversityDto,
            BindingResult bindingResult) throws BindException {
        log.info("Received request to create university {}", createUniversityDto);
        if (bindingResult.hasErrors()) {
            if (bindingResult instanceof BindException exception) {
                throw exception;
            } else {
                throw new BindException(bindingResult);
            }
        } else {
            var universityResponse = universityService.createUniversity(createUniversityDto);

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(universityResponse);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUniversity(
            @PathVariable Long id
    ) {
        log.info("Received request to delete university {}", id);
        universityService.deleteUniversityById(id);

        return ResponseEntity
                .noContent()
                .build();
    }
}
