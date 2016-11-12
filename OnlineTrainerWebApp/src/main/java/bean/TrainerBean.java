package bean;

import entity.Trainer;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

/**
 * @author Bogdan Kaftanatiy
 */
@Stateless
@TransactionManagement(value = TransactionManagementType.CONTAINER)
public class TrainerBean extends AbstractDAO<Trainer> {
    @Override
    public Trainer getObject(int id) {
        Trainer result = em.find(Trainer.class, id);
        if(result != null) {
            logger.info("Find trainer with id = " + id);
        } else {
            logger.info("Trainer with id = " + id + ", not found");
        }
        return result;
    }
}
