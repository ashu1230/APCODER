package controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backendapplication.Course;
import com.backendapplication.service.AuthenticationService;

import service.CourseService;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping("/create")
    public ResponseEntity<Course> createCourse(@RequestBody Course course) {
        Course createdCourse = courseService.createCourse(course);
        return new ResponseEntity<>(createdCourse, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Course> updateCourse(@RequestBody Course course) {
        Course updatedCourse = courseService.updateCourse(course);
        return new ResponseEntity<>(updatedCourse, HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Object> getCourseById(@PathVariable Long id) {
        Course course = courseService.getCourseById(id);
        String userRole = AuthenticationService.getUserRole();

        if (userRole.equals("student")) {
            // For student role, return only basic course information
            if (course != null) {
                return new ResponseEntity<>(course.getName() + " - " + course.getSubject(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Course not found", HttpStatus.NOT_FOUND);
            }
        } else {
            // For other roles (e.g., course creator, teacher), return the full course object
            return new ResponseEntity<>(course, HttpStatus.OK);
        }
    }

}
