package entity;

import javax.persistence.*;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Users accounts
 * @author Bogdan Kaftanatiy
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "role")
@Table(name = "accounts")
public abstract class Account implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;
    @Column(name = "login")
    protected String login;
    @Column(name = "password")
    protected String password;
    @Column(name = "name")
    protected String name;
    @Column(name = "surname")
    protected String surname;
    @Column(name = "dob")
    @Temporal(TemporalType.TIMESTAMP)
    protected Date dob;

    public Account() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws NoSuchAlgorithmException {
        this.password = getEncodedPassword(password);
    }

    private static String getEncodedPassword(String password) throws NoSuchAlgorithmException {
        return new String(Base64.getEncoder().encode(encryption(password)));
    }

    private static byte[] encryption(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(password.getBytes());
        return md.digest();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public int getAge() {
        Date currentDate = new Date();
        Calendar current = Calendar.getInstance(Locale.ROOT);
        current.setTime(currentDate);

        Calendar birth = Calendar.getInstance(Locale.ROOT);
        birth.setTime(dob);
        int diff = current.get(Calendar.YEAR) - birth.get(Calendar.YEAR);
        if(birth.get(Calendar.DAY_OF_YEAR) > birth.get(Calendar.DAY_OF_YEAR)) {
            diff--;
        }
        return diff;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        if (id != account.id) return false;
        if (!login.equals(account.login)) return false;
        if (!password.equals(account.password)) return false;
        if (!name.equals(account.name)) return false;
        if (!surname.equals(account.surname)) return false;
        return dob != null ? dob.equals(account.dob) : account.dob == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + login.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + surname.hashCode();
        result = 31 * result + (dob != null ? dob.hashCode() : 0);
        return result;
    }
}
