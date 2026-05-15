package dev.pridankish.universityservicemock.controller;

import dev.pridankish.universityservicemock.dto.CreateGroupDto;
import dev.pridankish.universityservicemock.dto.ResponseGroupDto;
import dev.pridankish.universityservicemock.service.GroupService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/groups")
@RequiredArgsConstructor
@Slf4j
public class GroupController {

    private final GroupService groupService;

    @PostMapping
    public ResponseEntity<ResponseGroupDto> createGroup(
            @Valid @RequestBody CreateGroupDto createGroupDto,
            BindingResult bindingResult
    ) throws BindException {
        log.info("Received request to create group {}", createGroupDto);
        if (bindingResult.hasErrors()) {
            if (bindingResult instanceof BindException exception) {
                throw exception;
            } else {
                throw new BindException(bindingResult);
            }
        } else {
            var group = groupService.createGroup(createGroupDto);

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(group);
        }
    }

    @GetMapping("/{universityId}")
    public ResponseEntity<List<ResponseGroupDto>> getGroupsByUniversityId(
            @PathVariable Long universityId
    ) {
        var groupsByUniversityId = groupService.getGroupsByUniversityId(universityId);
        return ResponseEntity
                .ok(groupsByUniversityId);
    }
}
