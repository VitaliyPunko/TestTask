package entity;

import javax.persistence.*;

@Entity
@Table(name = "worked_hours")
public class WorkedHoursEntity {
    private Integer id;
    private Integer workerHours;

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "Worker_hours", nullable = true)
    public Integer getWorkerHours() {
        return workerHours;
    }

    public void setWorkerHours(Integer workerHours) {
        this.workerHours = workerHours;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WorkedHoursEntity that = (WorkedHoursEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (workerHours != null ? !workerHours.equals(that.workerHours) : that.workerHours != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (workerHours != null ? workerHours.hashCode() : 0);
        return result;
    }
}
