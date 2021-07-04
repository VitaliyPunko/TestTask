package entity;

import java.io.Serializable;
import java.util.Objects;

public class DepartmentEntity implements Serializable {

    private Integer id;
    private String departmentName;

    public DepartmentEntity() {
    }

    public DepartmentEntity(String departmentName) {
        this.departmentName = departmentName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DepartmentEntity that = (DepartmentEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(departmentName, that.departmentName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, departmentName);
    }

    @Override
    public String toString() {
        return "DepartmentEntity{" +
                "id=" + id +
                ", departmentName='" + departmentName + '\'' +
                '}';
    }
}
