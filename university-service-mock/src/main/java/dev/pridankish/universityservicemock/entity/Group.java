package dev.pridankish.universityservicemock.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

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
}
