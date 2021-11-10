package model;

import enums.Degree;
import enums.TeacherType;

public class FullTimeTeacher extends Teacher {
    private double baseSalary;

    public FullTimeTeacher(String name, String lastName, String personalCode, double baseSalary) {
        super(name, lastName, personalCode);
        this.baseSalary= baseSalary;
    }

    public FullTimeTeacher(String name, String lastName, String personalNumber, Degree degree, Integer experienceYear, int age, TeacherType type, double baseSalary) {
        super(name, lastName, personalNumber, degree, experienceYear, age, type);
        this.baseSalary = baseSalary;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(double baseSalary) {
        this.baseSalary = baseSalary;
    }

    @Override
    public Double calculateSalary() {
        return baseSalary - super.calculateInsurance(baseSalary) - super.calculateTax(baseSalary);
    }

}
