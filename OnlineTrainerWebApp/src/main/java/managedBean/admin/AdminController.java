package managedBean.admin;

import bean.ClientBean;
import bean.TrainerBean;
import entity.Client;
import entity.Trainer;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.List;

/**
 * @author Bogdan Kaftanatiy
 */
@ManagedBean(name = "adminBean", eager = true)
@SessionScoped
public class AdminController {
    private final static Logger logger = Logger.getLogger(AdminController.class);
    @EJB
    private ClientBean clientDao;
    @EJB
    private TrainerBean trainerDao;

    public String deleteClient(Client client) {
        clientDao.deleteObject((int) client.getId());
        return toClientsList();
    }

    public String deleteTrainer(Trainer trainer) {
        trainerDao.deleteObject((int) trainer.getId());
        return toTrainersList();
    }

    public List<Client> getClientsList() {
        return clientDao.getAllClients();
    }

    public List<Trainer> getTrainerList() {
        return trainerDao.getAll();
    }

    public String toClientsList() {
        return "clients_list";
    }

    public String toTrainersList() {
        return "trainers_list";
    }

    public String toRegistration() {
        return "registration";
    }
}
