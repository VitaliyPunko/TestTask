package entity.dto;

public class WorkerInfoAndHoursDto {

    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private Integer workerHours;

    public WorkerInfoAndHoursDto() {
    }

    public WorkerInfoAndHoursDto(String firstName, String lastName, String email, Integer workerHours) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.workerHours = workerHours;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getWorkerHours() {
        return workerHours;
    }

    public void setWorkerHours(Integer workerHours) {
        this.workerHours = workerHours;
    }
}
