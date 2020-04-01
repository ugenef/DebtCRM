package models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "academic_group", schema = "public", catalog = "dddj0qvpae0k6u")
public class AcademicGroupEntity {
    private int groupId;
    private String groupName;

    @Id
    @Column(name = "group_id")
    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    @Basic
    @Column(name = "group_name")
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
}
