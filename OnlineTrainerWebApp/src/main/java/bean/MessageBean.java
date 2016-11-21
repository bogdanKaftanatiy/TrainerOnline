package bean;

import entity.Account;
import entity.Message;

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
public class MessageBean extends AbstractDAO<Message> {
    @Override
    public Message getObject(int id) {
        Message result = em.find(Message.class, id);
        if(result != null) {
            logger.info("Find message with id = " + id);
        } else {
            logger.info("Message with id = " + id + ", not found");
        }
        return result;
    }

    public List<Message> getAccountMessages(Account account) {
        TypedQuery<Message> query =
                em.createQuery("SELECT m FROM Message m WHERE m.from=:accountParam OR m.to=:accountParam",
                        Message.class);
        query.setParameter("accountParam", account);
        List<Message> result = query.getResultList();
        logger.info("Find " + result.size() + " messages for account: " + account);
        return  result;
    }
}
