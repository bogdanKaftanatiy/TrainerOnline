package managedBean.trainer;

import managedBean.UserSession;
import org.apache.log4j.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

/**
 * @author Bogdan Kaftanatiy
 */
@ManagedBean(name = "trainerBean", eager = true)
@SessionScoped
public class TrainerController {
    private final static Logger logger = Logger.getLogger(TrainerController.class);
    @ManagedProperty(value = "#{userSession}")
    private UserSession userSession;



    public UserSession getUserSession() {
        return userSession;
    }

    public void setUserSession(UserSession userSession) {
        this.userSession = userSession;
    }
}
