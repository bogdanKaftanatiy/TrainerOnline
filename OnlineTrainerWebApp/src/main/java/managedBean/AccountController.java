package managedBean;

import bean.AdminBean;
import bean.ClientBean;
import bean.TrainerBean;
import entity.Admin;
import entity.Client;
import entity.Trainer;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.Date;

/**
 * @author Bogdan Kaftanatiy
 */
@ManagedBean(name = "accountBean", eager = true)
@SessionScoped
public class AccountController {
    private final static Logger logger = Logger.getLogger(AccountController.class);
    @EJB
    private ClientBean clientDao;
    @EJB
    private TrainerBean trainerDao;
    @EJB
    private AdminBean adminDao;

    private String login;
    private String password;
    private String name;
    private String surname;
    private Date dob;
    private String role;


    public String insert() {
        switch (role) {
            case "client":
                Client client = new Client(login, password, name, surname, dob);
                clientDao.updateObject(client);
                break;
            case "trainer":
                Trainer trainer = new Trainer(login, password, name, surname, dob);
                trainerDao.updateObject(trainer);
                break;
            case "admin":
                Admin admin = new Admin(login, password, name, surname, dob);
                adminDao.updateObject(admin);
                break;
            default:
                logger.error("Illegal account role!");
                break;
        }
        return "index?faces-redirect=true";
    }

    public String toRegistration() {
        login = "";
        password = "";
        name = "";
        surname = "";
        dob = null;
        return "registration";
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

    public void setPassword(String password) {
        this.password = password;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
