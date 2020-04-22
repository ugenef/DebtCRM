package dcrm.service.controllers.dto;

import dcrm.service.businessmodels.Teacher;

public class TeacherDto {
    private TeacherDto(Teacher teacher) {
        id = teacher.id;
        firstName = teacher.firstName;
        middleName = teacher.middleName;
        lastName = teacher.lastName;
    }

    public int id;
    public String firstName;
    public String middleName;
    public String lastName;

    public static TeacherDto[] Map(Teacher[] teachers) {
        TeacherDto[] dtos = new TeacherDto[teachers.length];
        for (int i = 0; i < teachers.length; i++) {
            dtos[i] = new TeacherDto(teachers[i]);
        }
        return dtos;
    }
}

