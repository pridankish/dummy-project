package dev.pridankish.universityservicemock.service;

import dev.pridankish.universityservicemock.dto.CreateUniversityDto;
import dev.pridankish.universityservicemock.dto.ResponseUniversityDto;
import dev.pridankish.universityservicemock.mapper.UniversityMapper;
import dev.pridankish.universityservicemock.repository.UniversityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UniversityService {

    private final UniversityMapper universityMapper;
    private final UniversityRepository universityRepository;

    @Transactional
    public ResponseUniversityDto createUniversity(CreateUniversityDto createUniversityDto) {
        var universityEntity = universityMapper.toEntity(createUniversityDto);
        log.info("Creating university {}", universityEntity);
        var savedUniversity = universityRepository.save(universityEntity);
        return universityMapper.toDto(savedUniversity);
    }

    @Transactional
    public void deleteUniversityById(Long id) {
        universityRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public ResponseUniversityDto getUniversityById(Long id) {
        var university = universityRepository.getUniversityById(id);
        return universityMapper.toDto(university);
    }
}
