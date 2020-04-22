package dcrm.service.controllers.dto;

import dcrm.service.businessmodels.Debt;

public class DebtDto {
    private DebtDto(Debt debt) {
        studentId = debt.student.id;
        studentFirstName = debt.student.firstName;
        studentMiddleName = debt.student.middleName;
        studentLastName = debt.student.lastName;
        studentGroup = debt.student.group.name;

        subjectName = debt.subject.name;
    }

    public int studentId;
    public String studentFirstName;
    public String studentMiddleName;
    public String studentLastName;
    public String studentGroup;

    public String subjectName;

    public static DebtDto[] Map(Debt[] debts) {
        DebtDto[] dtos = new DebtDto[debts.length];
        for (int i = 0; i < debts.length; i++) {
            dtos[i] = new DebtDto(debts[i]);
        }
        return dtos;
    }
}

