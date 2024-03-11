package com.backendapplication;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String subject;
    private int chapters;
    private int numberOfClasses;
    private CourseType type;
    private LearningMode learningMode;

    // Constructors
    public Course() {}

    public Course(String name, String subject, int chapters, int numberOfClasses, CourseType type, LearningMode learningMode) {
        this.name = name;
        this.subject = subject;
        this.chapters = chapters;
        this.numberOfClasses = numberOfClasses;
        this.type = type;
        this.learningMode = learningMode;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getChapters() {
        return chapters;
    }

    public void setChapters(int chapters) {
        this.chapters = chapters;
    }

    public int getNumberOfClasses() {
        return numberOfClasses;
    }

    public void setNumberOfClasses(int numberOfClasses) {
        this.numberOfClasses = numberOfClasses;
    }

    public CourseType getType() {
        return type;
    }

    public void setType(CourseType type) {
        this.type = type;
    }

    public LearningMode getLearningMode() {
        return learningMode;
    }

    public void setLearningMode(LearningMode learningMode) {
        this.learningMode = learningMode;
    }
}

