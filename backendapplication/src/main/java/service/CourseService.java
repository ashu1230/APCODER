package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backendapplication.Course;

import repository.CourseRepository;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public Course createCourse(Course course) {
        // Check if the course object is null
        if (course == null) {
            throw new IllegalArgumentException("Course object cannot be null");
        }
        
        // Perform any other validation checks if necessary
        
        // Save the course to the database
        return courseRepository.save(course);
    }

    public Course updateCourse(Course course) {
        // Check if the course object is null
        if (course == null) {
            throw new IllegalArgumentException("Course object cannot be null");
        }
        
        // Perform any other validation checks if necessary
        
        // Update the course in the database
        return courseRepository.save(course);
    }

    public Course getCourseById(Long id) {
        // Check if the provided ID is null or negative
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid course ID");
        }
        
        // Retrieve the course from the database by its ID
        // If the course does not exist, throw an exception or return null based on your application's requirements
        return courseRepository.findById(id).orElse(null);
    }
}
