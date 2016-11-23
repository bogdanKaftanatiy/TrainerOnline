package managedBean.testJMS;

import org.apache.log4j.Logger;

import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Topic;

/**
 * @author Bogdan Kaftanatiy
 */
@ManagedBean(name = "jmsService", eager = true)
@SessionScoped
public class JMSService {
    private static final Logger logger = Logger.getLogger(JMSService.class);
    @Resource(lookup = "java:jboss/exported/jms/topic/test")
    private Topic topic;

    @Inject
    @JMSConnectionFactory("java:jboss/testTopicFactory")
    private JMSContext context;

    public void sendMessage() {
        String txt = "WARN: Some very important information!";
        logger.info("Sending message: " + txt);
        context.createProducer().send(topic, txt);
    }
}
