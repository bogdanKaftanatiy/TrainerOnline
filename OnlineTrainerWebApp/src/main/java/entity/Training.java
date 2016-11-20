package entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

/**
 * @author Bogdan Kaftanatiy
 */
@Entity
@Table(name = "tranings")
public class Training implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Column(name = "description")
    private String description;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "clientID")
    private Client client;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "training")
    private Set<Exercise> exercises;

    public Training() {
        exercises = new HashSet<>();
    }

    public Training(Date date, String description, Client client) {
        this.date = date;
        this.description = description;
        this.client = client;
        this.exercises = new HashSet<>();
    }

    public Training(int id, Date date, String description, Client client) {
        this.id = id;
        this.date = date;
        this.description = description;
        this.client = client;
        this.exercises = new HashSet<>();
    }

    public void addExercise(Exercise exercise) {
        exercises.add(exercise);
        exercise.setTraining(this);
    }

    public void removeExercise(Exercise exercise) {
        exercises.remove(exercise);
        exercise.setTraining(null);
    }

    public String getFormattedDate() {
        Calendar calendarDate = Calendar.getInstance(Locale.ROOT);
        calendarDate.setTime(date);
        String result = calendarDate.get(Calendar.DATE) + "." + calendarDate.get(Calendar.MONTH) + "."
                + calendarDate.get(Calendar.YEAR);
        return result;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Set<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(Set<Exercise> exercises) {
        this.exercises = exercises;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Training training = (Training) o;

        if (id != training.id) return false;
        if (date != null ? !date.equals(training.date) : training.date != null) return false;
        if (description != null ? !description.equals(training.description) : training.description != null)
            return false;
        if (client != null ? !client.equals(training.client) : training.client != null) return false;
        return exercises != null ? exercises.equals(training.exercises) : training.exercises == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (client != null ? client.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Training{" +
                "id=" + id +
                ", date=" + date +
                ", description='" + description + '\'' +
                ", client=" + client +
                '}';
    }
}
