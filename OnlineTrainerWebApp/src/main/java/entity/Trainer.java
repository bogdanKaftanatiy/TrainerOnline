package entity;

import javax.persistence.*;
import java.util.*;

/**
 * @author Bogdan Kaftanatiy
 */
@Entity
@DiscriminatorValue("TRAINER")
@NamedQuery(query = "SELECT t FROM Trainer t", name = "Trainer.getAll")
public class Trainer extends Account {
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "trainer")
    private List<Client> clients;
    @Column(name = "rating")
    private double rating;
    @Column(name = "description")
    private String description;

    public Trainer() {
        clients = new ArrayList<>();
    }

    public Trainer(String login, String password, String name, String surname, Date dob) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.dob = dob;
    }

    public Trainer(String login, String password, String name, String surname, Date dob, double rating) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.dob = dob;
        this.rating = rating;
    }

    public Trainer(String login, String password, String name, String surname, Date dob, double rating, String description) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.dob = dob;
        this.rating = rating;
        this.description = description;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void addClient(Client client) {
        clients.add(client);
    }

    public void removeClient(Client client) {
        clients.remove(client);
    }

    @Override
    public String toString() {
        return "Trainer{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", dob=" + dob +
                '}';
    }
}
