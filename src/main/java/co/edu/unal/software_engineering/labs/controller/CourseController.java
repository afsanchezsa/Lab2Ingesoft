package co.edu.unal.software_engineering.labs.controller;

import java.util.Collections;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unal.software_engineering.labs.model.Association;
import co.edu.unal.software_engineering.labs.model.Course;
import co.edu.unal.software_engineering.labs.model.Role;
import co.edu.unal.software_engineering.labs.model.User;
import co.edu.unal.software_engineering.labs.pojo.RegisterCoursePOJO;
import co.edu.unal.software_engineering.labs.pojo.RegisterUserPOJO;
import co.edu.unal.software_engineering.labs.repository.UserRepository;
import co.edu.unal.software_engineering.labs.service.CourseService;
import co.edu.unal.software_engineering.labs.service.RoleService;
import co.edu.unal.software_engineering.labs.service.UserService;

@CrossOrigin
@RestController
public class CourseController {
    private final CourseService courseService;

    private final UserService userService;

    public CourseController(CourseService courseService, UserService userService) {
        this.courseService = courseService;
        this.userService = userService;

    }

    // this method is for create a course. it receives the id from user wich is
    // creating the course
    @PostMapping(value = { "/registro/curso/{username}" })
    public ResponseEntity register(@PathVariable String username, @RequestBody RegisterCoursePOJO coursePOJO) {
        User userExisting = userService.findByUsername(username.toLowerCase());

        if (userExisting == null || !courseService.isRigthCourse(coursePOJO)) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        } else if (!userService.isTeacher(userExisting)) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
        Course newCourse = new Course();
        newCourse.setCourseName(coursePOJO.getCourseName());
        newCourse.setDurationHours(coursePOJO.getDurationHours());
        this.courseService.save(newCourse);

        return new ResponseEntity(HttpStatus.CREATED);
    }

}
