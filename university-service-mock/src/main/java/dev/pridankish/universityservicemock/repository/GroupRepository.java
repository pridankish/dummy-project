package dev.pridankish.universityservicemock.repository;

import dev.pridankish.universityservicemock.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group, Long> {
    List<Group> getGroupsByUniversityId(Long universityId);
}
