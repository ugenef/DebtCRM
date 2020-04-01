package models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "teacher_subject", schema = "public", catalog = "dddj0qvpae0k6u")
@IdClass(TeacherSubjectEntityPK.class)
public class TeacherSubjectEntity {
    private int teacherId;
    private int subjectId;
    private TeacherEntity teacherByTeacherId;
    private SubjectEntity subjectBySubjectId;

    @Id
    @Column(name = "teacher_id")
    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
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
        TeacherSubjectEntity that = (TeacherSubjectEntity) o;
        return teacherId == that.teacherId &&
                subjectId == that.subjectId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(teacherId, subjectId);
    }

    @ManyToOne
    @JoinColumn(name = "teacher_id", referencedColumnName = "teacher_id", nullable = false)
    public TeacherEntity getTeacherByTeacherId() {
        return teacherByTeacherId;
    }

    public void setTeacherByTeacherId(TeacherEntity teacherByTeacherId) {
        this.teacherByTeacherId = teacherByTeacherId;
    }

    @ManyToOne
    @JoinColumn(name = "subject_id", referencedColumnName = "subject_id", nullable = false)
    public SubjectEntity getSubjectBySubjectId() {
        return subjectBySubjectId;
    }

    public void setSubjectBySubjectId(SubjectEntity subjectBySubjectId) {
        this.subjectBySubjectId = subjectBySubjectId;
    }
}
