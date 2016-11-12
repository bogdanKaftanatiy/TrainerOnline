package entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Bogdan Kaftanatiy
 */
@Entity
@DiscriminatorValue("TRAINER")
public class Trainer extends Account {
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "trainer")
    private List<Client> clients;

    public Trainer() {
        clients = new ArrayList<Client>();
    }

    public Trainer(String login, String password, String name, String surname, Date dob) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.dob = dob;
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
