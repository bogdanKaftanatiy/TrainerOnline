package bean;

import entity.Admin;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

/**
 * @author Bogdan Kaftanatiy
 */
@Stateless
@TransactionManagement(value = TransactionManagementType.CONTAINER)
public class AdminBean extends AbstractDAO<Admin> {
    @Override
    public Admin getObject(int id) {
        Admin result = em.find(Admin.class, id);
        if(result != null) {
            logger.info("Find admin with id = " + id);
        } else {
            logger.info("Admin with id = " + id + ", not found");
        }
        return result;
    }
}
