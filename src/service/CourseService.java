package service;

import model.Course;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CourseService {

    List<Course> courseList=new ArrayList<>();

    public CourseService() {

        Course course = new Course("math", 1);
        Course course1 = new Course("computer", 2);
        Course course2 = new Course("physic", 3);
        Course course3 = new Course("history", 4);
        Course course4 = new Course("art", 5);

        courseList.add(course);
        courseList.add(course1);
        courseList.add(course2);
        courseList.add(course3);
        courseList.add(course4);
    }

    public void printCourseList(){
        courseList.stream().map(i -> i.getCourseNumber()+") "+i.getName()).forEach(System.out::println);
    }

    public Course getCourseByCourseNumber(int courseNumber){

        Optional<Course> courseOptional = courseList.stream().filter(i -> i.getCourseNumber()==courseNumber).findFirst();
        return courseOptional.orElse(null);
    }




}
