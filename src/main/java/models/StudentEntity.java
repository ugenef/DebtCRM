package models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "student", schema = "public", catalog = "dddj0qvpae0k6u")
public class StudentEntity {
    private int studentId;
    private String firstName;
    private String middleName;
    private String lastName;
    private AcademicGroupEntity academicGroupByGroupId;

    @Id
    @Column(name = "student_id")
    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    @Basic
    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "middle_name")
    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    @Basic
    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentEntity that = (StudentEntity) o;
        return studentId == that.studentId &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(middleName, that.middleName) &&
                Objects.equals(lastName, that.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, firstName, middleName, lastName);
    }

    @ManyToOne
    @JoinColumn(name = "group_id", referencedColumnName = "group_id", nullable = false)
    public AcademicGroupEntity getAcademicGroupByGroupId() {
        return academicGroupByGroupId;
    }

    public void setAcademicGroupByGroupId(AcademicGroupEntity academicGroupByGroupId) {
        this.academicGroupByGroupId = academicGroupByGroupId;
    }
}
