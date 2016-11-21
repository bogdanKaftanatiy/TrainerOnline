package managedBean.client;

import bean.ClientBean;
import bean.MessageBean;
import bean.TrainerBean;
import entity.Client;
import entity.Message;
import entity.Trainer;
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
@ManagedBean(name = "clientBean", eager = true)
@SessionScoped
public class ClientController {
    private final static Logger logger = Logger.getLogger(ClientController.class);
    @EJB
    private ClientBean clientDao;
    @EJB
    private TrainerBean trainerDao;
    @EJB
    private MessageBean messageDao;
    @ManagedProperty(value = "#{userSession}")
    private UserSession userSession;
    private Client currentClient;
    private double minRating;
    private double inputRating;
    private boolean isRatingUpdate = false;
    private String inputMessage;

    public String sendMessage() {
        Message message = new Message();
        message.setMessage(inputMessage);
        message.setDate(new Date());
        message.setRead(false);
        message.setFromAccount(getCurrentClient());
        message.setToAccount(getCurrentClient().getTrainer());
        messageDao.updateObject(message);
        inputMessage = "";
        return "message_page";
    }

    public List<Message> getUserMessages() {
        List<Message> result = messageDao.getAccountMessages(getCurrentClient(), getCurrentClient().getTrainer());
        for (Message m : result) {
            if (m.getToAccount().equals(this.getCurrentClient())) {
                m.setRead(true);
                messageDao.updateObject(m);
            }
        }
        return result;
    }

    public String edit() {
        clientDao.updateObject(currentClient);
        return "client_profile?faces-redirect=true";
    }

    public String getSender(Message message) {
        if(message.getFromAccount().equals(getCurrentClient()))
            return "You";
        else
            return message.getFromAccount().getName();
    }

    private void setClient() {
        String currentLogin = userSession.getCurrentUser();
        currentClient = clientDao.searchByLogin(currentLogin);
        logger.info("Update current client");
    }

    public Client getCurrentClient(){
        if(currentClient == null){
            setClient();
        }
        return currentClient;
    }

    public void setCurrentClient(Client currentClient) {
        this.currentClient = currentClient;
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

    public String estimateTrainer() {
        if(currentClient.getTrainer().getRating() == 0)
            currentClient.getTrainer().setRating(inputRating);
        else {
            double newRating = (currentClient.getTrainer().getRating() + inputRating)/2;
            currentClient.getTrainer().setRating(newRating);
        }
        trainerDao.updateObject(currentClient.getTrainer());
        isRatingUpdate = false;
        return "trainerPage?faces-redirect=true";
    }

    public String getDOB() {
        Calendar birth = Calendar.getInstance(Locale.ROOT);
        birth.setTime(currentClient.getDob());
        String result = birth.get(Calendar.DATE) + "." + birth.get(Calendar.MONTH) +
                "." + birth.get(Calendar.YEAR);
        return result;
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

    public double getInputRating() {
        return inputRating;
    }

    public void setInputRating(double inputRating) {
        this.inputRating = inputRating;
    }

    public String getInputMessage() {
        return inputMessage;
    }

    public void setInputMessage(String inputMessage) {
        this.inputMessage = inputMessage;
    }

    public boolean isRatingUpdate() {
        return isRatingUpdate;
    }

    public void updateRating() {
        isRatingUpdate = true;
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

    public String toMessagePage() {
        return "message_page";
    }
}
