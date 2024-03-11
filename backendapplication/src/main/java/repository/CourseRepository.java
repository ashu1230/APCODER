package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backendapplication.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    // Additional custom queries can be defined here if needed
}

