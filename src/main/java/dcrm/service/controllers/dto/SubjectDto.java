package dcrm.service.controllers.dto;

import dcrm.service.businessmodels.Subject;

public class SubjectDto {
    private SubjectDto(Subject subject) {
        id = subject.id;
        name = subject.name;
    }

    public int id;
    public String name;

    public static SubjectDto[] Map(Subject[] subjects) {
        SubjectDto[] dtos = new SubjectDto[subjects.length];
        for (int i = 0; i < subjects.length; i++) {
            dtos[i] = new SubjectDto(subjects[i]);
        }
        return dtos;
    }
}
