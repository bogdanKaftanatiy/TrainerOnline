package managedBean.client;

import bean.ExerciseBean;
import bean.TrainingBean;
import entity.Exercise;
import entity.Training;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.util.List;

/**
 * @author Bogdan Kaftanatiy
 */
@ManagedBean(name = "trainingBean", eager = true)
@SessionScoped
public class TrainingController {
    private final static Logger logger = Logger.getLogger(TrainingController.class);
    @ManagedProperty(value = "#{clientBean}")
    private ClientController clientBean;
    @EJB
    private TrainingBean trainingDao;
    @EJB
    private ExerciseBean exerciseDao;
    private Exercise currentExercise;
    private Training currentTraining;

    public String addExercise() {
        currentTraining = trainingDao.updateObject(currentTraining);
        currentExercise = exerciseDao.updateObject(currentExercise);
        currentTraining.addExercise(currentExercise);
        exerciseDao.updateObject(currentExercise);
        currentExercise = new Exercise();
        return "addTrainings";
    }

    public String addTraining() {
        currentTraining.setClient(clientBean.getCurrentClient());
        trainingDao.updateObject(currentTraining);
        return "trainingsList?faces-redirect=true";
    }

    public String deleteTraining(Training training) {
        currentTraining.getExercises().remove(training);
        trainingDao.deleteObject(training.getId());
        currentExercise = new Exercise();
        return "trainingsList?faces-redirect=true";
    }

    public String deleteExercise(Exercise exercise) {
        exerciseDao.deleteObject(exercise.getId());
        return "addTrainings";
    }

    public String toTrainingsList() {
        return "trainingsList";
    }

    public String toAddTraining() {
        currentTraining = new Training();
        currentExercise = new Exercise();
        return "addTrainings";
    }

    public String toUpdateTraining(Training training) {
        currentTraining = training;
        currentExercise = new Exercise();
        return "addTrainings";
    }

    public List<Training> getTrainingsList() {
        return trainingDao.getClientTrainings(clientBean.getCurrentClient());
    }

    public Exercise getCurrentExercise() {
        return currentExercise;
    }

    public void setCurrentExercise(Exercise currentExercise) {
        this.currentExercise = currentExercise;
    }

    public Training getCurrentTraining() {
        return currentTraining;
    }

    public void setCurrentTraining(Training currentTraining) {
        this.currentTraining = currentTraining;
    }

    public ClientController getClientBean() {
        return clientBean;
    }

    public void setClientBean(ClientController clientBean) {
        this.clientBean = clientBean;
    }
}
