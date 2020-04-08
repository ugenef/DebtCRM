package models;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "academic_group", schema = "public", catalog = "dddj0qvpae0k6u")
public class AcademicGroupEntity {

    @Id
    @Column(name = "group_id")
    private int groupId;

    @Basic
    @Column(name = "group_name")
    private String groupName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "academicGroup")
    private Set<StudentEntity> students;

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AcademicGroupEntity that = (AcademicGroupEntity) o;
        return groupId == that.groupId &&
                Objects.equals(groupName, that.groupName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupId, groupName);
    }

    @Override
    public String toString() {
        return "AcademicGroupEntity{" +
                "groupId=" + groupId +
                ", groupName='" + groupName + '\'' +
                '}';
    }
}
