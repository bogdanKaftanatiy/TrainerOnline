package managedBean;

import bean.AdminBean;
import bean.ClientBean;
import bean.TrainerBean;
import entity.Admin;
import entity.Client;
import entity.Trainer;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import java.security.NoSuchAlgorithmException;
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


    public String insert() throws NoSuchAlgorithmException {
        switch (role) {
            case "client":
                Client client = new Client(login, password, name, surname, dob);
                client.setPassword(password);
                clientDao.updateObject(client);
                break;
            case "trainer":
                Trainer trainer = new Trainer(login, password, name, surname, dob);
                trainer.setPassword(password);
                trainerDao.updateObject(trainer);
                break;
            case "admin":
                Admin admin = new Admin(login, password, name, surname, dob);
                admin.setPassword(password);
                adminDao.updateObject(admin);
                break;
            default:
                logger.error("Illegal account role!");
                break;
        }
        return "/index?faces-redirect=true";
    }

    public String toRegistration() {
        login = "";
        password = "";
        name = "";
        surname = "";
        dob = null;
        return "registration";
    }

    public void validateFrom(ComponentSystemEvent event) {
        FacesContext fc = FacesContext.getCurrentInstance();
        UIComponent components = event.getComponent();
        String groupID = components.getClientId();

        UIInput uiInputLogin = (UIInput) components.findComponent("login");
        String login = uiInputLogin.getLocalValue() == null ? "" : uiInputLogin.getLocalValue().toString();
        UIInput uiInputPass = (UIInput) components.findComponent("pass");
        String pass = uiInputPass.getLocalValue() == null ? "" : uiInputPass.getLocalValue().toString();
        UIInput uiInputName = (UIInput) components.findComponent("name");
        String name = uiInputName.getLocalValue() == null ? "" : uiInputName.getLocalValue().toString();
        UIInput uiInputSurname = (UIInput) components.findComponent("surname");
        String surname = uiInputSurname.getLocalValue() == null ? "" : uiInputSurname.getLocalValue().toString();

        if(login.isEmpty() || pass.isEmpty() || name.isEmpty() || surname.isEmpty()) {
            FacesMessage msg = new FacesMessage("Fill all input fields");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            fc.addMessage(groupID, msg);
            fc.renderResponse();
        }
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
