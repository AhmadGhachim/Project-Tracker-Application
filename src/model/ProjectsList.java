package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ProjectsList {

    private final ObservableList<Project> projects;

    public ProjectsList() {
        projects = FXCollections.observableArrayList();
    }

    public ObservableList<Project> getAllProjects() {
        return projects;
    }

    public void createNewProject(int i) {
        projects.add(new Project(i));
    }

    public boolean deleteProject(Project selectedProject) {
        if (selectedProject == null) {
            return false;
        }
        projects.remove(selectedProject);
        return true;
    }
}
