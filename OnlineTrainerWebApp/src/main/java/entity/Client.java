package entity;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Bogdan Kaftanatiy
 */
@Entity
@DiscriminatorValue("CLIENT")
public class Client extends Account {
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "trainerID")
    private Trainer trainer;

    public Client() {
    }

    public Client(String login, String password, String name, String surname, Date dob) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.dob = dob;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        if (this.trainer != null) {
            this.trainer.removeClient(this);
        }
        if (trainer == null) {
            this.trainer = null;
        } else {
            this.trainer = trainer;
            this.trainer.addClient(this);
        }
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", dob=" + dob +
                '}';
    }
}
