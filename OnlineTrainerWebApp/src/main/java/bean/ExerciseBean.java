package bean;

import entity.Exercise;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

/**
 * @author Bogdan Kaftanatiy
 */
@Stateless
@TransactionManagement(value = TransactionManagementType.CONTAINER)
public class ExerciseBean extends AbstractDAO<Exercise> {
    @Override
    public Exercise getObject(int id) {
        Exercise result = em.find(Exercise.class, id);
        if(result != null) {
            logger.info("Find exercise with id = " + id);
        } else {
            logger.info("Exercise with id = " + id + ", not found");
        }
        return result;
    }
}
