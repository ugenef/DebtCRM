package models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "student_subject", schema = "public", catalog = "dddj0qvpae0k6u")
@IdClass(StudentSubjectEntityPK.class)
public class StudentSubjectEntity {
    private int studentId;
    private int subjectId;
    private StudentEntity studentByStudentId;

    @Id
    @Column(name = "student_id")
    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    @Id
    @Column(name = "subject_id")
    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentSubjectEntity that = (StudentSubjectEntity) o;
        return studentId == that.studentId &&
                subjectId == that.subjectId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, subjectId);
    }

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "student_id", nullable = false)
    public StudentEntity getStudentByStudentId() {
        return studentByStudentId;
    }

    public void setStudentByStudentId(StudentEntity studentByStudentId) {
        this.studentByStudentId = studentByStudentId;
    }
}
