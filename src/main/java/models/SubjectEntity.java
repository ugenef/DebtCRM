package models;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "subject", schema = "public", catalog = "dddj0qvpae0k6u")
public class SubjectEntity {

    @Id
    @Column(name = "subject_id")
    private int id;

    @Basic
    @Column(name = "subject_name")
    private String name;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "subjects")
    private Set<StudentEntity> students;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "subjects")
    private Set<TeacherEntity> teachers;

    public int getId() {
        return id;
    }

    public void setId(int subjectId) {
        this.id = subjectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String subjectName) {
        this.name = subjectName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubjectEntity that = (SubjectEntity) o;
        return id == that.id &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "SubjectEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
