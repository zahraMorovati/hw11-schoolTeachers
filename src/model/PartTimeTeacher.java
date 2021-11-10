package model;

import enums.Degree;
import enums.TeacherType;

public class PartTimeTeacher extends Teacher {
    private int hourlySalary;
    private int hourPerMonth;

    public PartTimeTeacher(String name, String lastName, String personalCode, int hourPerMonth, int hourlySalary) {
        super(name, lastName, personalCode);
        this.hourlySalary = hourlySalary;
        this.hourPerMonth = hourPerMonth;
    }

    public PartTimeTeacher(String name, String lastName, String personalNumber, Degree degree, Integer experienceYear, int age, TeacherType type, int hourlySalary, int hourPerMonth) {
        super(name, lastName, personalNumber, degree, experienceYear, age, type);
        this.hourlySalary = hourlySalary;
        this.hourPerMonth = hourPerMonth;
    }

    public int getHourlySalary() {
        return hourlySalary;
    }

    public void setHourlySalary(int hourlySalary) {
        this.hourlySalary = hourlySalary;
    }

    public int getHourPerMonth() {
        return hourPerMonth;
    }

    public void setHourPerMonth(int hourPerMonth) {
        this.hourPerMonth = hourPerMonth;
    }

    @Override
    public Double calculateSalary() {
        double baseSalary = hourlySalary * hourPerMonth;
        return baseSalary - super.calculateInsurance(baseSalary) - super.calculateTax(baseSalary);
    }
}
