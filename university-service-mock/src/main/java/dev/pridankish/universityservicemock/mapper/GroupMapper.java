package dev.pridankish.universityservicemock.mapper;

import dev.pridankish.universityservicemock.dto.CreateGroupDto;
import dev.pridankish.universityservicemock.dto.ResponseGroupDto;
import dev.pridankish.universityservicemock.entity.Group;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GroupMapper {
    Group toEntity(CreateGroupDto createGroupDto);

    @Mapping(source = "university.id", target = "universityId")
    ResponseGroupDto toDto(Group group);
}
