package dev.pridankish.universityservicemock.mapper;

import dev.pridankish.universityservicemock.dto.CreateUniversityDto;
import dev.pridankish.universityservicemock.dto.ResponseUniversityDto;
import dev.pridankish.universityservicemock.entity.University;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UniversityMapper {
    ResponseUniversityDto toDto(University university);
    University toEntity(CreateUniversityDto createUniversityDto);
}
