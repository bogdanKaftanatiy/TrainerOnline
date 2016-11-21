package managedBean.trainer;

import bean.ClientBean;
import bean.TrainerBean;
import bean.TrainingBean;
import entity.Account;
import entity.Client;
import entity.Trainer;
import managedBean.UserSession;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * @author Bogdan Kaftanatiy
 */
@ManagedBean(name = "trainerBean", eager = true)
@SessionScoped
public class TrainerController {
    private final static Logger logger = Logger.getLogger(TrainerController.class);
    @ManagedProperty(value = "#{userSession}")
    private UserSession userSession;
    @EJB
    private TrainerBean trainerDao;
    @EJB
    private ClientBean clientDao;
    @EJB
    private TrainingBean trainingDao;
    private Trainer currentTrainer;
    private Client currentClient = new Client();

    public String edit() {
        trainerDao.updateObject(currentTrainer);
        return "trainer_profile?faces-redirect=true";
    }

    public List<Client> getClientList() {
        List<Client> result = clientDao.trainerClients(getCurrentTrainer());
        return result;
    }

    public Trainer getCurrentTrainer() {
        if(currentTrainer == null)
            setTrainer();
        return currentTrainer;
    }

    public int getCurrentClientTrainingsCount() {
        return trainingDao.getClientTrainings(getCurrentClient()).size();
    }

    public void setCurrentTrainer(Trainer currentTrainer) {
        this.currentTrainer = currentTrainer;
    }

    private void setTrainer() {
        String currentLogin = userSession.getCurrentUser();
        currentTrainer = trainerDao.searchByLogin(currentLogin);
        logger.info("Update current trainer");
    }

    public UserSession getUserSession() {
        return userSession;
    }

    public void setUserSession(UserSession userSession) {
        this.userSession = userSession;
    }

    public String getDOB(Account account) {
        Calendar birth = Calendar.getInstance(Locale.ROOT);
        birth.setTime(account.getDob());
        String result = birth.get(Calendar.DATE) + "." + (birth.get(Calendar.MONTH) + 1) +
                "." + birth.get(Calendar.YEAR);
        return result;
    }

    public Client getCurrentClient() {
        return currentClient;
    }

    public void setCurrentClient(Client currentClient) {
        this.currentClient = currentClient;
    }

    public String toClientProfile(Client client) {
        currentClient = client;
        return "client_profile";
    }

    public String toProfile() {
        return "trainer_profile";
    }

    public String toClientList() {
        return "client_list?faces-redirect=true";
    }

    public String toEditProfile() {
        return "edit_profile";
    }
}
