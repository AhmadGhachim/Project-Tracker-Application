package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Project {

    private final String name;
    private final ObservableList<Task> tasks;

    public Project(int i) {
        this.name = "Project " + i;
        tasks = FXCollections.observableArrayList();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public boolean deleteTask(Task task) {
        return tasks.remove(task);
    }

    public ObservableList<Task> getAllTasks() {
        return tasks;
    }

    @Override
    public String toString() {
        return name;
    }

}
