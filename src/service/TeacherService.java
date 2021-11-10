package service;

import enums.Degree;
import enums.TeacherType;
import model.*;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static enums.Degree.*;
import static enums.TeacherType.*;

public class TeacherService {

    public List<Teacher> teachers = new ArrayList<>();

    public TeacherService() {

        teachers.add(new PartTimeTeacher("ahmad", "admadi", "33", Degree.MA,10,35, PART_TIME, 4_500_000,22));
        teachers.add(new PartTimeTeacher("zahra", "zahraei", "28", Degree.PHD,10,45,PART_TIME, 120_000, 10));
        teachers.add(new PartTimeTeacher("ali", "aliyani", "50", Degree.BS,5,45,PART_TIME,  20_000, 50));
        teachers.add(new FullTimeTeacher("parviz", "parvizi", "34", Degree.PHD,10,38,FULL_TIME,  13_500_000));
        teachers.add(new FullTimeTeacher("fateme", "fatemi", "29", Degree.PHD,8,30,FULL_TIME,  6_300_000));
        teachers.add(new FullTimeTeacher("samane", "besharati", "51", Degree.BS,10,40,FULL_TIME,  4_000_000));
    }

    public int getIndexTeacher(String personalNumber){
        for (int i = 0; i < teachers.size(); i++) {
            if(teachers.get(i).getPersonalNumber().equalsIgnoreCase(personalNumber)){
                return i;
            }
        }
        return -1;
    }

    public void printTeachersList(){
        teachers.stream().map(i -> i.getPersonalNumber()+") "+i.getName()+" "+i.getLastName()).forEach(System.out::println);
    }

    public Optional<Course> findByCourseNumber(int courseNumber, String personalNumber) {

        for (Teacher teacher : teachers) {
            if (teacher.getPersonalNumber().equals(personalNumber)) {
                return teacher.getCourse().stream().filter(i -> i.getCourseNumber() == courseNumber).findAny();
            }
        }
        throw new RuntimeException("teacher not found!");
    }

    public Course add(Course course, String personalNumber) {
        Optional<Course> foundedCourse = findByCourseNumber(course.getCourseNumber(), personalNumber);
        if (foundedCourse.isPresent())
            throw new RuntimeException("duplicate Course");

        int index=getIndexTeacher(personalNumber);
        teachers.get(index).getCourse().add(course);
        return course;

    }

    public Optional<School> findBySchoolName(String schoolName, String personalNumber) {
        for (Teacher teacher : teachers) {
            if (teacher.getPersonalNumber().equals(personalNumber)) {
                return teacher.getSchool().stream().filter(i -> i.getName().equalsIgnoreCase(schoolName)).findAny();
            }
        }
        throw new RuntimeException("teacher not found!");
    }

    public School add(School school, String personalNumber) {
        Optional<School> foundedSchool = findBySchoolName(school.getName(),personalNumber);
        if (foundedSchool.isPresent())
            throw new RuntimeException("duplicate school");

        int index=getIndexTeacher(personalNumber);
        teachers.get(index).getSchool().add(school);
        return school;
    }

    public List<Teacher> findTeachersHaveMoreThanAverageSalary() {
        return teachers.stream().filter(i -> i.calculateSalary() > getSalaryAverage()).collect(Collectors.toList());
    }

    public Double getSalaryAverage() {
        Double sumSalary = teachers.stream()
                .map(Teacher::calculateSalary).reduce(0D, Double::sum);
        int count = teachers.size();
        return sumSalary / count;
    }

    public Map<TeacherType, List<Teacher>> listTeacherWith10YearsExperience() {
        return teachers.stream().filter(i -> i.getExperienceYear() == 10).collect(Collectors.groupingBy(Teacher::getType));
    }

    // 1-PART_TIME  2-MA 3-course>2 4- school --> degree==2
    public List<Teacher> findSpecificTeacher() {
        List<Teacher> foundedTeachers = new ArrayList<>();
        for (Teacher teacher : teachers) {
            teacher.getSchool().stream().filter(i -> i.getDegree() == 2);
            foundedTeachers.add(teacher);
        }
        return foundedTeachers.stream().filter(i -> i.getType().equals(PART_TIME)).
                filter(i -> i.getDegree().equals(MA)).
                filter(i -> i.getCourse().size() > 2).collect(Collectors.toList());
    }

    public Set<String> findSchoolNames() {
        for (Teacher teacher : teachers) {
            return teacher.getSchool().stream().map(School::getName).collect(Collectors.toSet());
        }
        throw new RuntimeException("no school found!");
    }

    public Map<String, List<Teacher>> getTeachersGroupBySchoolName() {

        Map<String, List<Teacher>> schoolListMap=new HashMap<>();
        for (String schoolName : findSchoolNames()) {
            List<Teacher> teacherList=new ArrayList<>();
            for (Teacher teacher : teachers) {
                Optional<School> optionalSchool=
                        teacher.getSchool().stream().filter(i -> i.getName().equalsIgnoreCase(schoolName)).findAny();
                if(optionalSchool.isPresent()){
                    teacherList.add(teacher);
                }
            }
            schoolListMap.put(schoolName,teacherList);
        }
        return schoolListMap;
    }
}
