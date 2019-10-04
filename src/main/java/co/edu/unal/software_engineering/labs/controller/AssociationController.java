package co.edu.unal.software_engineering.labs.controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import ch.qos.logback.core.joran.action.NewRuleAction;
import co.edu.unal.software_engineering.labs.model.Association;
import co.edu.unal.software_engineering.labs.model.Course;
import co.edu.unal.software_engineering.labs.model.Period;
import co.edu.unal.software_engineering.labs.model.Role;
import co.edu.unal.software_engineering.labs.model.User;
import co.edu.unal.software_engineering.labs.model.UserRole;
import co.edu.unal.software_engineering.labs.pojo.RegisterAssociationPOJO;
import co.edu.unal.software_engineering.labs.service.AssociationService;
import co.edu.unal.software_engineering.labs.service.CourseService;
import co.edu.unal.software_engineering.labs.service.PeriodService;
import co.edu.unal.software_engineering.labs.service.RoleService;
import co.edu.unal.software_engineering.labs.service.UserService;

@Controller
public class AssociationController{

 private final AssociationService associationService;
 private final UserService userService;
 private final RoleService roleService;
 private final CourseService courseService;
 private final PeriodService periodService;
    public AssociationController(AssociationService associationService,UserService userService,RoleService roleService
    ,CourseService courseService,PeriodService periodService){
        this.associationService=associationService;
        this.userService=userService;
        this.roleService=roleService;
        this.courseService=courseService;
        this.periodService=periodService;
    }

    @PostMapping("/asociacion/registro/{username}")
public ResponseEntity register(@PathVariable String username,@RequestBody RegisterAssociationPOJO associationPOJO){
    User userExisting=this.userService.findByUsername(username.toLowerCase());
    Role roleExisting=this.roleService.findById(associationPOJO.getRoleId());
    Course courseExisting=this.courseService.findById(associationPOJO.getCourseId());
    Period periodExisting=this.periodService.findById(associationPOJO.getPeriodId());
    
    if(userExisting==null||roleExisting==null||courseExisting==null||periodExisting==null){
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }else if(!userExisting.getRoles().contains(roleExisting)||!this.associationService.isRigth(associationPOJO)){
    return new ResponseEntity(HttpStatus.UNAUTHORIZED);
    }
    
    
        Association newAssociation=new Association();
        UserRole userRole=new UserRole();
        newAssociation.setUserRole(userRole);
        newAssociation.setUser(userExisting);
        newAssociation.setRole(roleExisting);
        newAssociation.setCourse(courseExisting);
        newAssociation.setPeriod(periodExisting);
        this.associationService.save(newAssociation);
        return new ResponseEntity(HttpStatus.CREATED);
    
    
        
}
}