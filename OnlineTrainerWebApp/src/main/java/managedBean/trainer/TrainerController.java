package managedBean.trainer;

import bean.ClientBean;
import bean.MessageBean;
import bean.TrainerBean;
import bean.TrainingBean;
import entity.*;
import managedBean.UserSession;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.util.Calendar;
import java.util.Date;
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
    @EJB
    private MessageBean messageDao;
    private Trainer currentTrainer;
    private Client currentClient = new Client();
    private String inputMessage;

    public String sendMessage() {
        Message message = new Message();
        message.setMessage(inputMessage);
        message.setDate(new Date());
        message.setRead(false);
        message.setFromAccount(currentTrainer);
        message.setToAccount(currentClient);
        messageDao.updateObject(message);
        inputMessage = "";
        return "messages_page";
    }

    public List<Message> getUserMessages() {
        List<Message> result = messageDao.getAccountMessages(getCurrentTrainer(), getCurrentClient());
        for (Message m : result) {
            if (m.getToAccount().equals(this.getCurrentTrainer())) {
                m.setRead(true);
                messageDao.updateObject(m);
            }
        }
        return result;
    }

    public String getSender(Message message) {
        if(message.getFromAccount().equals(this.getCurrentTrainer()))
            return "You";
        else
            return message.getFromAccount().getName();
    }

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



    public String convertDateToString(Date date) {
        Calendar calendar = Calendar.getInstance(Locale.ROOT);
        calendar.setTime(date);
        String result = calendar.get(Calendar.DATE) + "." + (calendar.get(Calendar.MONTH) + 1) +
                "." + calendar.get(Calendar.YEAR);
        return result;
    }

    public List<Training> getTrainingsList() {
        return trainingDao.getClientTrainings(currentClient);
    }

    public Client getCurrentClient() {
        return currentClient;
    }

    public void setCurrentClient(Client currentClient) {
        this.currentClient = currentClient;
    }

    public String getInputMessage() {
        return inputMessage;
    }

    public void setInputMessage(String inputMessage) {
        this.inputMessage = inputMessage;
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

    public String toMessagePage(Client client) {
        currentClient = client;
        inputMessage = "";
        return "messages_page";
    }

    public String toTrainingsPage(Client client) {
        currentClient = client;
        return "trainings_page";
    }
}
