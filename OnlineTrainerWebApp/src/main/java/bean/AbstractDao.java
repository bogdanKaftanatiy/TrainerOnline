package bean;

import org.apache.log4j.Logger;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Bogdan Kaftanatiy
 */
public abstract class AbstractDAO<DataT> {
    protected final static Logger logger = Logger.getLogger(AbstractDAO.class);
    @PersistenceContext(unitName = "program_pu")
    protected EntityManager em;

    public abstract DataT getObject(int id);

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public DataT updateObject(DataT object) {
        DataT result = em.merge(object);
        logger.info("Updated object: " + object);
        return result;
    }

    public void deleteObject(int id) {
        em.remove(getObject(id));
        logger.info("Deleted object with id=" + id);
    }
}
