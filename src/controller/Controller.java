package controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import model.Project;
import model.ProjectsList;
import model.Task;
import model.Timer;
import view.RootPane;

public class Controller {

    //fields to be used throughout class
    private Timer timer;
    private final RootPane view;
    private final ProjectsList model;

    private int count = 1;

    public Controller(RootPane view, ProjectsList model, Timer timer) {
        //initialise view and model fields
        this.view = view;
        this.model = model;
        this.timer = timer;

        view.setProjectsListItems(model.getAllProjects());
        this.attachEventHandlers();
    }

    //helper method - used to attach event handlers
    private void attachEventHandlers() {

        view.createAddProjectHandler(new AddProjectHandler());
        view.createDeleteProjectHandler(new DeleteProjectHandler());
        view.createAddTaskHandler(new AddTaskHandler());
        view.createDeleteTaskHandler(new DeleteTaskHandler());
        view.createProjectSelectedHandler(new ProjectSelectedHandler());
        view.createStartTimerHandler(new StartTimer());
        view.createStopTimerHandler(new StopTimer());
        view.createResetTimerHandler(new ResetTimer());

    }

    public void setTaskDuration(String duration) {
        view.setTaskDuration(duration);
    }

    private class AddProjectHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent e) {

            model.createNewProject(count++);

        }
    }

    private class DeleteProjectHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent e) {

            Project selectedProject = view.getSelectedProject();
            if (model.deleteProject(selectedProject)) {
                showAlert("Delete Project", "Successful!", Alert.AlertType.INFORMATION);
            } else {
                showAlert("Delete Project", "Select a project to delete!", Alert.AlertType.WARNING);
            }

        }
    }

    private class AddTaskHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent e) {

            Project selectedProject = view.getSelectedProject();
            if (selectedProject == null) {
                showAlert("Add Task", "Select a project to add task!", Alert.AlertType.WARNING);
            } else {

                String name = view.getTaskName();
                String duration = view.getTaskDuration();

                if (name.isEmpty() || duration.isEmpty()) {
                    showAlert("Add Task", "Enter task name and duration!", Alert.AlertType.WARNING);
                } else {
                    selectedProject.addTask(new Task(name, duration));
                    view.setTaskName("");
                    view.setTaskDuration("");
                    view.displayProjectTasks(selectedProject.getAllTasks());
                    showAlert("Add Task", "Successful!", Alert.AlertType.INFORMATION);
                }

            }

        }
    }

    private class DeleteTaskHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent e) {

            Project selectedProject = view.getSelectedProject();
            Task selectedTask = view.getSelectedTask();

            if (selectedProject == null || selectedTask == null) {
                showAlert("Delete Task", "Select a project task to delete!", Alert.AlertType.WARNING);
            } else {
                if(selectedProject.deleteTask(selectedTask)){
                    view.displayProjectTasks(selectedProject.getAllTasks());
                    showAlert("Delete Project", "Successful!", Alert.AlertType.INFORMATION);
                }else{

                }
            }

        }
    }

    private class ProjectSelectedHandler implements ChangeListener<Project> {

        @Override
        public void changed(ObservableValue<? extends Project> observable, Project oldValue, Project newValue) {
            try {
                view.displayProjectTasks(newValue.getAllTasks());
            } catch (Exception e) {
            }
        }
    }

    private class StartTimer implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent e) {
            timer.startTimer(Controller.this);
        }
    }

    private class StopTimer implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent e) {
            timer.stopTimer();
        }
    }

    private class ResetTimer implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent e) {
            timer.resetTimer(Controller.this);
        }
    }

    private void showAlert(String header, String context, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setHeaderText(header);
        alert.setContentText(context);
        alert.showAndWait();
    }

}
