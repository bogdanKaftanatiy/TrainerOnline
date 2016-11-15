package managedBean;

import bean.ClientBean;
import bean.TrainerBean;
import entity.Client;
import entity.Trainer;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.util.List;

/**
 * @author Bogdan Kaftanatiy
 */
@ManagedBean(name = "clientBean", eager = true)
@SessionScoped
public class ClientController {
    private final static Logger logger = Logger.getLogger(ClientController.class);
    @EJB
    private ClientBean clientDao;
    @EJB
    private TrainerBean trainerDao;
    @ManagedProperty(value = "#{userSession}")
    private UserSession userSession;
    private Client currentClient;
    private double minRating;

    public String edit() {
        clientDao.updateObject(currentClient);
        return "client_profile?faces-redirect=true";
    }

    private void setClient() {
        String currentLogin = userSession.getUserName();
        currentClient = clientDao.searchByLogin(currentLogin);
        logger.info("Update current client");
    }

    public Client getClient(){
        if(currentClient == null){
            setClient();
        }
        return currentClient;
    }

    public String getCurrentTrainer() {
        if(currentClient.getTrainer() == null) {
            return "trainer is not selected";
        } else {
            return currentClient.getTrainer().getName() + " " + currentClient.getTrainer().getSurname();
        }
    }

    public String filter() {
        return "trainerList";
    }

    public String chooseTrainer(Trainer trainer) {
        currentClient.setTrainer(trainer);
        clientDao.updateObject(currentClient);
        return "trainerPage?faces-redirect=true";
    }

    public List<Trainer> getTrainerList() {
        if(minRating == 0)
            return trainerDao.getAll();
        else
            return trainerDao.getAppropriateTrainers(minRating);
    }

    public UserSession getUserSession() {
        return userSession;
    }

    public void setUserSession(UserSession userSession) {
        this.userSession = userSession;
    }

    public double getMinRating() {
        return minRating;
    }

    public void setMinRating(double minRating) {
        this.minRating = minRating;
    }

    public String toProfile() {
        return "client_profile";
    }

    public String toEdit() {
        return "editProfile";
    }

    public String toTrainerPage() {
        return  "trainerPage";
    }

    public String toTrainerList() {
        return "trainerList";
    }
}
