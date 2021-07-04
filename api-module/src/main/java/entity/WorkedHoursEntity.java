package entity;


import java.io.Serializable;

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
}
