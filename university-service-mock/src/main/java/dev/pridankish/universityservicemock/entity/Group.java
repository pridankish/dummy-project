package dev.pridankish.universityservicemock.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
@Table(schema = "university", name = "t_group")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            name = "c_name",
            nullable = false
    )
    @NotNull
    @Size(min = 1, max = 20)
    private String name;

    @ManyToOne
    @JoinColumn(name = "university_id")
    private University university;

    @OneToMany(
            mappedBy = "group",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private List<Lesson> lessons;

    public void addLesson(Lesson lesson) {
        lessons.add(lesson);
        lesson.setGroup(this);
    }

    public void removeLesson(Lesson lesson) {
        lessons.remove(lesson);
        lesson.setGroup(null);
    }
}
