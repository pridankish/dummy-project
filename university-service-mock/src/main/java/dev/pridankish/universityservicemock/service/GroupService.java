package dev.pridankish.universityservicemock.service;

import dev.pridankish.universityservicemock.dto.CreateGroupDto;
import dev.pridankish.universityservicemock.dto.ResponseGroupDto;
import dev.pridankish.universityservicemock.mapper.GroupMapper;
import dev.pridankish.universityservicemock.repository.GroupRepository;
import dev.pridankish.universityservicemock.repository.UniversityRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;
    private final UniversityRepository universityRepository;
    private final GroupMapper groupMapper;

    @Transactional
    public ResponseGroupDto createGroup(CreateGroupDto createGroupDto) {
        var university = universityRepository.findById(createGroupDto.universityId())
                .orElseThrow(
                        () -> new EntityNotFoundException("University not found with id: " + createGroupDto.universityId())
                );
        var group = groupMapper.toEntity(createGroupDto);
        group.setUniversity(university);
        university.addGroup(group);

        var savedGroup = groupRepository.save(group);

        return groupMapper.toDto(savedGroup);
    }

    @Transactional(readOnly = true)
    public List<ResponseGroupDto> getGroupsByUniversityId(Long universityId) {
        return groupRepository.getGroupsByUniversityId(universityId)
                .stream().map(groupMapper::toDto)
                .toList();
    }
}
