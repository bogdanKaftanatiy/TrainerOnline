package bean;

import entity.Client;
import entity.Training;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author Bogdan Kaftanatiy
 */
@Stateless
@TransactionManagement(value = TransactionManagementType.CONTAINER)
public class TrainingBean extends AbstractDAO<Training> {
    @Override
    public Training getObject(int id) {
        Training result = em.find(Training.class, id);
        if(result != null) {
            logger.info("Find training with id = " + id);
        } else {
            logger.info("Training with id = " + id + ", not found");
        }
        return result;
    }

    public List<Training> getClientTrainings(Client client) {
        TypedQuery<Training> query =
                em.createQuery("SELECT t FROM Training t WHERE t.client=:clientParam", Training.class);
        query.setParameter("clientParam", client);
        List<Training> result = query.getResultList();
        logger.info("Find " + result.size() + " trainings for client: " + client);
        return  result;
    }
}
