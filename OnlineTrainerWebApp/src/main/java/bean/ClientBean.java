package bean;

import entity.Client;
import entity.Trainer;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author Bogdan Kaftanatiy
 */
@Stateless
@TransactionManagement(value = TransactionManagementType.CONTAINER)
public class ClientBean extends AbstractDAO<Client> {
    @Override
    public Client getObject(int id) {
        Client result = em.find(Client.class, id);
        if(result != null) {
            logger.info("Find client with id = " + id);
        } else {
            logger.info("Client with id = " + id + ", not found");
        }
        return result;
    }

    public Client searchByLogin(String login) {
        TypedQuery<Client> query = em.createQuery("SELECT c FROM Client c WHERE c.login=:loginParam", Client.class);
        query.setParameter("loginParam", login);
        List<Client> array = query.getResultList();
        if(array==null || array.isEmpty()){
            logger.warn("Client with login='" + login + "' not exist");
            return null;
        } else {
            logger.info("Find client with login=" + login);
            return array.get(0);
        }
    }

    public List<Client> trainerClients(Trainer trainer) {
        TypedQuery<Client> query =
                em.createQuery("SELECT c FROM Client c WHERE c.trainer=:trainerParam",
                        Client.class);
        query.setParameter("trainerParam", trainer);
        List<Client> result = query.getResultList();
        logger.info("Find " + result.size() + " clients for trainer: " + trainer);
        return  result;
    }

    public List<Client> getAllClients() {
        TypedQuery<Client> query = em.createNamedQuery("Client.getAll", Client.class);
        List<Client> result = query.getResultList();

        logger.info("Get all client");

        return result;
    }
}
