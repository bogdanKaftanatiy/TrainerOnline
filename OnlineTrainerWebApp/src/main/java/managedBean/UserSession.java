package managedBean;

import org.apache.log4j.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author Bogdan Kaftanatiy
 */
@ManagedBean(name = "userSession", eager = true)
@SessionScoped
public class UserSession {
    private static final Logger logger = Logger.getLogger(UserSession.class);
    private String username;
    private String password;
    private boolean isLogin = false;

    public String login() {
        if(isLogin)
            logout();
        HttpServletRequest request =
                (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        try {
            request.login(username, password);
            isLogin = true;
            if(request.isUserInRole("ADMIN"))
                return "/admin/admin_index?faces-redirect=true";
            if(request.isUserInRole("TRAINER"))
                return "/trainer/trainer_index?faces-redirect=true";
            if(request.isUserInRole("CLIENT"))
                return "/client/client_index?faces-redirect=true";
        } catch (ServletException e) {
            logger.error("Error authorization user with login=" + username);
            return "/error";
        } finally {
            username = "";
            password = "";
        }

        logger.warn("Unknown user role!");
        logout();
        return "/error";
    }

    public String logout() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        logger.info("Invalidate session for user: " + getCurrentUser());
        session.invalidate();
        isLogin = false;
        return "/index?faces-redirect=true";
    }

    public boolean isLogin() {
        return isLogin;
    }

    public String getCurrentUser() {
        return FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
    }

    public String toUserPage() {
        HttpServletRequest request =
                (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        if(request.isUserInRole("ADMIN"))
            return "/admin/admin_index?faces-redirect=true";
        if(request.isUserInRole("TRAINER"))
            return "/trainer/trainer_index?faces-redirect=true";
        if(request.isUserInRole("CLIENT"))
            return "/client/client_index?faces-redirect=true";

        logger.warn("Unknown user role!");
        logout();
        return "/error";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
