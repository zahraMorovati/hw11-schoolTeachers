package service;

import enums.Degree;
import model.PartTimeTeacher;
import model.School;
import model.Teacher;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static enums.TeacherType.PART_TIME;
import static model.GetValidData.getValidName;

public class SchoolService {

    List<School> schoolList=new ArrayList<>();

    public SchoolService() {


        School school =  new School("maktab", 1);
        School school1 = new School("madani", 2);
        School school2 = new School("alavi", 3);
        School school3 = new School("razavi", 3);
        School school4 = new School("jalal", 1);
        School school5 = new School("diba", 1);

        schoolList.add(school);
        schoolList.add(school1);
        schoolList.add(school2);
        schoolList.add(school3);
        schoolList.add(school4);
        schoolList.add(school5);

    }

    public void printSchoolList(){
        schoolList.stream().map(i -> i.getName()+" - "+i.getDegree()).forEach(System.out::println);
    }

    public School getSchoolBySchoolName(String schoolName){
        Optional<School> schoolOptional=schoolList.stream().filter(i -> i.getName().equalsIgnoreCase(schoolName)).findFirst();
        return schoolOptional.orElse(null);
    }

}
