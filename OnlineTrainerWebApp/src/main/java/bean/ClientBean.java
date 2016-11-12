package bean;

import entity.Client;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

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
}
