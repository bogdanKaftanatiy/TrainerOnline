package managedBean.client;

import bean.ScheduleUnitBean;
import entity.Client;
import entity.ScheduleUnit;
import org.apache.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Bogdan Kaftanatiy
 */
@ManagedBean(name = "scheduleBean", eager = true)
@SessionScoped
public class ScheduleController {
    private final static Logger logger = Logger.getLogger(ScheduleController.class);
    @ManagedProperty(value = "#{clientBean}")
    private ClientController clientBean;
    @EJB
    private ScheduleUnitBean scheduleDao;
    private List<String> weekList;
    private List<ScheduleUnit> scheduleUnits;

    @PostConstruct
    public void init() {
        weekList = new ArrayList<>();
        weekList.add("Monday");
        weekList.add("Tuesday");
        weekList.add("Wednesday");
        weekList.add("Thursday");
        weekList.add("Friday");
        weekList.add("Saturday");
        weekList.add("Sunday");
        scheduleUnits = new ArrayList<>();
        for (int i = 0; i < 7; i++)
            scheduleUnits.add(new ScheduleUnit());
    }

    public String addScheduleUnit(int index) {
        ScheduleUnit currentUnit = scheduleUnits.get(index);
        currentUnit.setClient(clientBean.getCurrentClient());
        currentUnit.setWeekDay(index);
        scheduleDao.updateObject(currentUnit);
        return "schedulePage?faces-redirect=true";
    }

    public String deleteScheduleUnit(ScheduleUnit scheduleUnit) {
        scheduleDao.deleteObject(scheduleUnit.getId());
        return "schedulePage?faces-redirect=true";
    }

    public List<List<ScheduleUnit>> getDaySchedule() {
        List<List<ScheduleUnit>> result = new ArrayList<>();
        Client currentClient = clientBean.getCurrentClient();

        for (int i = 0; i < 7; i++)
            result.add(scheduleDao.getClientSchedule(currentClient, i));

        return result;
    }

    public String getDayName(int index) {
        return weekList.get(index);
    }

    public String toSchedule() {
        scheduleUnits = new ArrayList<>();
        for (int i = 0; i < 7; i++)
            scheduleUnits.add(new ScheduleUnit());
        return "schedulePage";
    }

    public ClientController getClientBean() {
        return clientBean;
    }

    public void setClientBean(ClientController clientBean) {
        this.clientBean = clientBean;
    }

    public List<ScheduleUnit> getScheduleUnits() {
        return scheduleUnits;
    }

    public void setScheduleUnits(List<ScheduleUnit> scheduleUnits) {
        this.scheduleUnits = scheduleUnits;
    }
}
