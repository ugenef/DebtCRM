package dcrm.service.controllers.dto;

import dcrm.service.businessmodels.Student;

public class StudentDto {

    private StudentDto(Student student){
        id= student.id;
        firstName = student.firstName;
        middleName = student.middleName;
        lastName = student.lastName;
        group = student.group.name;
    }

    public int id;
    public String firstName;
    public String middleName;
    public String lastName;
    public String group;

    public static StudentDto[] Map(Student[] students){
        StudentDto[] dtos = new StudentDto[students.length];
        for(int i = 0;i<students.length;i++){
            dtos[i] = new StudentDto(students[i]);
        }
        return dtos;
    }
}
