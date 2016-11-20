package bean;

import entity.Client;
import entity.ScheduleUnit;

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
public class ScheduleUnitBean extends AbstractDAO<ScheduleUnit> {
    @Override
    public ScheduleUnit getObject(int id) {
        ScheduleUnit result = em.find(ScheduleUnit.class, id);
        if(result != null) {
            logger.info("Find schedule unit with id = " + id);
        } else {
            logger.info("Schedule unit with id = " + id + ", not found");
        }
        return result;
    }

    public List<ScheduleUnit> getClientSchedule(Client client, int weekDay) {
        TypedQuery<ScheduleUnit> query =
                em.createQuery("SELECT s FROM ScheduleUnit s WHERE s.client=:clientParam AND s.weekDay=:weekParam",
                        ScheduleUnit.class);
        query.setParameter("clientParam", client);
        query.setParameter("weekParam", weekDay);
        List<ScheduleUnit> result = query.getResultList();
        logger.info("Find " + result.size() + " schedule units for client: " + client);
        return  result;
    }
}
