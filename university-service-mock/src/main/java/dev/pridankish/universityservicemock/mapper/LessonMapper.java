package dev.pridankish.universityservicemock.mapper;

import dev.pridankish.universityservicemock.dto.ResponseLessonDto;
import dev.pridankish.universityservicemock.entity.Lesson;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LessonMapper {

    @Mapping(source = "group.id", target = "groupId")
    ResponseLessonDto toDto(Lesson lesson);
}
