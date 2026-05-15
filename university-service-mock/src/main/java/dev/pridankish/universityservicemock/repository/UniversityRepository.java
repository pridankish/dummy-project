package dev.pridankish.universityservicemock.repository;

import dev.pridankish.universityservicemock.entity.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UniversityRepository extends JpaRepository<University, Long> {
    @Query("select u from University u join fetch u.groups g where u.id = :id")
    University getUniversityById(Long id);
}
