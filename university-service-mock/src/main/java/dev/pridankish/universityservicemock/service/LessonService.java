package dev.pridankish.universityservicemock.service;

import dev.pridankish.universityservicemock.dto.ResponseLessonDto;
import dev.pridankish.universityservicemock.entity.enums.WeekType;
import dev.pridankish.universityservicemock.mapper.LessonMapper;
import dev.pridankish.universityservicemock.repository.LessonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LessonService {

    private final LessonRepository lessonRepository;
    private final LessonMapper lessonMapper;

    @Transactional(readOnly = true)
    public List<ResponseLessonDto> getLessonsByDate(LocalDateTime date) {

        // TODO: Добаввить получение группы из класса Principal, когда добавлю JWT, пока хардкод

        var groupId = 1L;

        /* TODO
        *  1. реализовать получение по дате неделя четная или нечетная (считая, что неделя с 1 сентября имеет №1)
        *  2. реализовать получение дня недели по дате (возможно совместить с пунктом 1)
        */

        var weekType = WeekType.EVEN_WEEK;
//        var weekDay = date.getDayOfWeek();
        var weekDay = DayOfWeek.MONDAY;

        return lessonRepository.findLessonsByDate(groupId, weekType, weekDay).stream()
                .map(lessonMapper::toDto)
                .toList();
    }


}
