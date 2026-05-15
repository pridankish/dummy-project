package dev.pridankish.universityservicemock.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Table(schema = "university", name = "t_university")
public class University {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            name = "c_name",
            nullable = false,
            unique = true
    )
    @NotNull
    @Size(min = 1, max = 200)
    private String name;

    @OneToMany(
            mappedBy = "university",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private List<Group> groups;

    public void addGroup(Group group) {
        groups.add(group);
        group.setUniversity(this);
    }

    public void removeGroup(Group group) {
        groups.remove(group);
        group.setUniversity(null);
    }
}
