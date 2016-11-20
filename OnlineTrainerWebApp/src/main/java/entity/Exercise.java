package entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Bogdan Kaftanatiy
 */
@Entity
@Table(name = "exercises")
public class Exercise implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "duration")
    private double duration;
    @Column(name = "description")
    private String description;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "trainingID")
    private Training training;

    public Exercise() {
    }

    public Exercise(String name, double duration, String description, Training training) {
        this.name = name;
        this.duration = duration;
        this.description = description;
        this.training = training;
    }

    public Exercise(int id, String name, double duration, String description, Training training) {
        this.id = id;
        this.name = name;
        this.duration = duration;
        this.description = description;
        this.training = training;
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

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Training getTraining() {
        return training;
    }

    public void setTraining(Training training) {
        this.training = training;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Exercise exercise = (Exercise) o;

        if (id != exercise.id) return false;
        if (Double.compare(exercise.duration, duration) != 0) return false;
        if (name != null ? !name.equals(exercise.name) : exercise.name != null) return false;
        if (description != null ? !description.equals(exercise.description) : exercise.description != null)
            return false;
        return training != null ? training.equals(exercise.training) : exercise.training == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        temp = Double.doubleToLongBits(duration);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (training != null ? training.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Exercise{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", duration=" + duration +
                ", description='" + description + '\'' +
                ", training=" + training +
                '}';
    }
}
