package managedBean.testJMS;

import managedBean.UserSession;
import org.apache.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.jms.*;
import java.util.Random;

/**
 * @author Bogdan Kaftanatiy
 */
@ManagedBean(name = "jmsReceiver", eager = true)
@SessionScoped
public class JMSReceiver {
    private static final Logger logger = Logger.getLogger(JMSReceiver.class);

    @ManagedProperty(value = "#{userSession}")
    private UserSession userSession;

    @Resource(lookup = "java:jboss/exported/jms/topic/test")
    private Topic topic;

    @Inject
    @JMSConnectionFactory("java:jboss/testTopicFactory")
    private JMSContext context;

    public String receiveMessage() {
        String name = userSession.getCurrentUser();
        if(name == null || name.isEmpty())
            name = "test" + new Random().nextInt(99999) + new Random().nextInt(99999);
        JMSConsumer consumer = context.createSharedDurableConsumer(topic, name);
        return consumer.receiveBody(String.class);
    }

    public UserSession getUserSession() {
        return userSession;
    }

    public void setUserSession(UserSession userSession) {
        this.userSession = userSession;
    }
}
