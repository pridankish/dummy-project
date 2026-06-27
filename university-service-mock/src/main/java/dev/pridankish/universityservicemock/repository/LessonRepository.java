package dev.pridankish.universityservicemock.repository;

import dev.pridankish.universityservicemock.entity.Lesson;
import dev.pridankish.universityservicemock.entity.enums.WeekType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {

//    List<Lesson> findLessonsByGroupIdAndWeekTypeAndWeekDay(Long groupId, WeekType weekType, DayOfWeek dayOfWeek);

    @Query("select l from Lesson l " +
            "where l.group.id = :groupId " +
            "and l.weekType = :weekType " +
            "and l.weekDay = :dayOfWeek " +
            "order by l.startTime asc")
    List<Lesson> findLessonsByDate(@Param("groupId") Long groupId,
                                   @Param("weekType") WeekType weekType,
                                   @Param("dayOfWeek") DayOfWeek dayOfWeek);
}
