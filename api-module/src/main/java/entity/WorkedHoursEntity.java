package entity;


import java.io.Serializable;
import java.util.Objects;

public class WorkedHoursEntity implements Serializable {

    private Integer id;
    private Integer workerHours;
    private Integer workerId;

    public WorkedHoursEntity() {
    }

    public WorkedHoursEntity(Integer workerHours, Integer workerId) {
        this.workerHours = workerHours;
        this.workerId = workerId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getWorkerHours() {
        return workerHours;
    }

    public void setWorkerHours(Integer workerHours) {
        this.workerHours = workerHours;
    }

    public Integer getWorkerId() {
        return workerId;
    }

    public void setWorkerId(Integer workerId) {
        this.workerId = workerId;
    }

    @Override
    public String toString() {
        return "WorkedHoursEntity{" +
                "id=" + id +
                ", workerHours=" + workerHours +
                ", workerId=" + workerId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkedHoursEntity that = (WorkedHoursEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(workerHours, that.workerHours) && Objects.equals(workerId, that.workerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, workerHours, workerId);
    }
}
