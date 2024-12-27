package com.aws.cicd.Service;

import com.aws.cicd.dto.Course;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    private final List<Course> courses = new ArrayList<>();


    public void addCourse(Course course)
    {
         courses.add(course);
    }

    public List<Course> getAllCourse(){
        return courses;
    }

    public Optional<Course> getById(int id)
    {
        return courses.stream()
                .filter(course -> course.getId() == id)
                .findFirst();
    }

    public boolean updateCourse(Course course)
    {
        return getById(course.getId()).map(oldCourse -> {
            courses.remove(oldCourse);
            courses.add(course);
            return true;
        }).orElse(false);
    }

    public boolean deleteCourse(int id){
//        return getById(id).map(c ->{
//            courses.remove(c);
//            return true;
//        }).orElse(false);

        return courses.removeIf(c->c.getId() == id);
    }


}
