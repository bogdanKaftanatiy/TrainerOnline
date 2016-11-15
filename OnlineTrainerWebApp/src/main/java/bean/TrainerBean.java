package bean;

import entity.Trainer;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

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

    public List<Trainer> getAll() {
        TypedQuery<Trainer> query = em.createNamedQuery("Trainer.getAll", Trainer.class);
        List<Trainer> result = query.getResultList();

        logger.info("Get all trainer");

        return result;
    }

    public List<Trainer> getAppropriateTrainers(double rating) {
        List<Trainer> allTrainer = getAll();
        List<Trainer> result = new ArrayList<>();
        for (Trainer t : allTrainer) {
            if(t.getRating() >= rating)
                result.add(t);
        }
        return result;
    }
}
