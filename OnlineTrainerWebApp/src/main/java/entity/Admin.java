package entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Date;

/**
 * @author Bogdan Kaftanatiy
 */
@Entity
@DiscriminatorValue("ADMIN")
public class Admin extends Account {
    public Admin() {}

    public Admin(String login, String password, String name, String surname, Date dob) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.dob = dob;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", dob=" + dob +
                '}';
    }
}
