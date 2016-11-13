package managedBean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 * @author Bogdan Kaftanatiy
 */
@ManagedBean(name = "userSession")
@SessionScoped
public class UserSession {
    private String userName;

    public String getUserName() {
        userName = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String logout() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        session.invalidate();
        return "/index?faces-redirect=true";
    }
}
