package dev.pridankish.universityservicemock.entity;

import dev.pridankish.universityservicemock.entity.enums.LessonType;
import dev.pridankish.universityservicemock.entity.enums.WeekType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@Table(schema = "university", name = "t_lesson")
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            name = "c_auditory_number",
            nullable = false
    )
    @Size(min = 1, max = 20)
    private String auditoryNumber;

    @Column(
            name = "c_lesson_type",
            nullable = false
    )
    @Size(min = 1, max = 20)
    @Enumerated(EnumType.STRING)
    private LessonType lessonType;

    @Column(
            name = "c_week_type",
            nullable = false
    )
    @Size(min = 1, max = 10)
    @Enumerated(EnumType.STRING)
    private WeekType weekType;

    @Column(
            name = "c_week_day",
            nullable = false
    )
    @Enumerated(EnumType.STRING)
    @Size(min = 1, max = 10)
    private DayOfWeek weekDay;

    @Column(
            name = "c_lesson_start_time",
            nullable = false
    )
    private LocalDateTime startTime;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;
}
