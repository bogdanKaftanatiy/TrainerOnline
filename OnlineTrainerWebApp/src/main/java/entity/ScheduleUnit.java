package entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Bogdan Kaftanatiy
 */
@Entity
@Table(name = "schedule_unit")
public class ScheduleUnit implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "weekDay")
    private int weekDay;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "clientID", nullable = false)
    private Client client;

    public ScheduleUnit() {
    }

    public ScheduleUnit(String name, String description, int weekDay, Client client) {
        this.name = name;
        this.description = description;
        this.weekDay = weekDay;
        this.client = client;
    }

    public ScheduleUnit(int id, String name, String description, int weekDay, Client client) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.weekDay = weekDay;
        this.client = client;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(int weekDay) {
        this.weekDay = weekDay;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ScheduleUnit that = (ScheduleUnit) o;

        if (id != that.id) return false;
        if (weekDay != that.weekDay) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        return client != null ? client.equals(that.client) : that.client == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + weekDay;
        result = 31 * result + (client != null ? client.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ScheduleUnit{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", weekDay=" + weekDay +
                ", clientID=" + client.getId() +
                '}';
    }
}
