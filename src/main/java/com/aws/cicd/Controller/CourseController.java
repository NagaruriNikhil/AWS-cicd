package com.aws.cicd.Controller;


import com.aws.cicd.Service.CourseService;
import com.aws.cicd.dto.Course;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService cs;

    @PostConstruct
    public void addData(){
        cs.addCourse(new Course(1, "aws", 12.00));
        cs.addCourse(new Course(2, "azure", 12.00));
        cs.addCourse(new Course(1, "gcp", 12.00));

    }
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<Course>> getAllCourses()
    {
        List<Course> cl= cs.getAllCourse();
        return new ResponseEntity<>(cl,HttpStatus.OK);
    }

    @GetMapping(value="/{id}", produces = "application/json")
    public ResponseEntity<Course> getCourseById(@PathVariable int id)
    {
        Optional<Course> c= cs.getById(id);
        return c.map(value -> new ResponseEntity<>(value, HttpStatus.FOUND))
                .orElseGet(() ->new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<Course> addCourse(@RequestBody Course c){
        cs.addCourse(c);
        return new ResponseEntity<>(c, HttpStatus.CREATED);
    }

    @PutMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<Course> updateCourse(@RequestBody Course c)
    {
        boolean b = cs.updateCourse(c);
        if (b) return new ResponseEntity<>(c, HttpStatus.OK);
        else return new ResponseEntity<>(c, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Void> deleteCourse(@PathVariable int id){

        boolean b = cs.deleteCourse(id);
        if(b) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
