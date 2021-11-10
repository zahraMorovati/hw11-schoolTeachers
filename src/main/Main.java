package main;


import model.Course;
import model.School;

import service.CourseService;
import service.SchoolService;
import service.TeacherService;

import static model.ConsoleColors.*;
import static model.GetValidData.*;

public class Main {

    public static void main(String[] args) {
        CourseService courseService=new CourseService();
        SchoolService schoolService=new SchoolService();
        TeacherService teacherService=new TeacherService();
        addCoursesToTeachers(teacherService,courseService);
        addSchoolsToTeachers(teacherService,schoolService);
        mainMenu(teacherService,schoolService,courseService);
    }

    public static void mainMenu(TeacherService teacherService,SchoolService schoolService,CourseService courseService){

        try {


            System.out.println(BLUE_BRIGHT+"---------------------------------- main menu ----------------------------------"+RESET);
            int choice=getValidChoice(
                    "1)add course\n" +
                            "2)add school\n" +
                            "3)list of Teachers Have More Than Average Salary\n" +
                            "4)list of Teachers With 10 Years Experience\n" +
                            "5)list of PART_TIME teachers with MA degree and more than 2 course and with school degree=2\n" +
                            "6)SchoolNames\n" +
                            "7)Teachers Group By SchoolNames\n" +
                            "8)exit\n" +
                            "enter your choice: ",8);

            switch (choice){
                case 1:{

                    String personalNumber=getPersonalNumber(teacherService);
                    System.out.println(CYAN+"********** course list ***********"+RESET);
                    courseService.printCourseList();
                    int courseNumber=getValidInt("enter course number: ");
                    Course course=courseService.getCourseByCourseNumber(courseNumber);
                    teacherService.add(course,personalNumber);
                    System.out.println("course successfully added!");
                    mainMenu(teacherService,schoolService,courseService);
                }break;
                case 2:{
                    String personalNumber=getPersonalNumber(teacherService);
                    System.out.println(CYAN+"********** school list ***********"+RESET);
                    schoolService.printSchoolList();
                    String schoolName=getValidName("enter school name: ");
                    School school=schoolService.getSchoolBySchoolName(schoolName);
                    teacherService.add(school,personalNumber);
                    System.out.println("school successfully added!");
                    mainMenu(teacherService,schoolService,courseService);

                }break;
                case 3:{
                    System.out.println(teacherService.findTeachersHaveMoreThanAverageSalary());
                    mainMenu(teacherService,schoolService,courseService);
                }break;
                case 4:{
                    System.out.println(teacherService.listTeacherWith10YearsExperience());
                    mainMenu(teacherService,schoolService,courseService);
                }break;
                case 5:{
                    System.out.println(teacherService.findSpecificTeacher());
                    mainMenu(teacherService,schoolService,courseService);
                }break;
                case 6:{
                    System.out.println(teacherService.findSchoolNames());
                    mainMenu(teacherService,schoolService,courseService);
                }break;
                case 7:{
                    System.out.println(teacherService.getTeachersGroupBySchoolName());
                    mainMenu(teacherService,schoolService,courseService);
                }break;
                case 8:{
                    System.exit(0);
                }break;
            }
        }catch ( Exception e) {
            System.out.println(RED+"Exception: "+e.getMessage()+RESET);
            mainMenu(teacherService,schoolService,courseService);
        }


    }

    public static String getPersonalNumber(TeacherService teacherService){
        System.out.println(CYAN+"********** teachers list ***********"+RESET);
        teacherService.printTeachersList();
        return getValidPersonalNumber("enter personal number: ");
    }

    public static void addCoursesToTeachers(TeacherService teacherService, CourseService courseService){

        teacherService.teachers.get(0).getCourse().add(courseService.getCourseByCourseNumber(1));
        teacherService.teachers.get(0).getCourse().add(courseService.getCourseByCourseNumber(2));
        teacherService.teachers.get(0).getCourse().add(courseService.getCourseByCourseNumber(3));
    }

    public static void addSchoolsToTeachers(TeacherService teacherService, SchoolService schoolService){

        teacherService.teachers.get(0).getSchool().add(schoolService.getSchoolBySchoolName("alavi"));
        teacherService.teachers.get(0).getSchool().add(schoolService.getSchoolBySchoolName("madani"));

    }

}
