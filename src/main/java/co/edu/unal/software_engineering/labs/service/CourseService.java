package co.edu.unal.software_engineering.labs.service;

import org.springframework.stereotype.Service;

import co.edu.unal.software_engineering.labs.model.Course;
import co.edu.unal.software_engineering.labs.model.User;
import co.edu.unal.software_engineering.labs.pojo.RegisterCoursePOJO;
import co.edu.unal.software_engineering.labs.repository.CourseRepository;
import co.edu.unal.software_engineering.labs.repository.RoleRepository;

@Service
public class CourseService {
    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;

    }

    public Course findById(Integer courseId){
        return this.courseRepository.findById(courseId).orElse(null);
    }
    public void save(Course course) {

        this.courseRepository.save(course);
    }

    public boolean isRigthCourse(RegisterCoursePOJO course) {
        boolean correctness = course.getCourseName() != null && course.getDurationHours() != null;
        if (correctness) {
            correctness = correctness && !course.getCourseName().trim().isEmpty()
                    && course.getDurationHours().compareTo(0) > 0;
        }
        return correctness;
    }
}
