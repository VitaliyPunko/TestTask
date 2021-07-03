package entity;

public class DepartmentEntity {

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
}
