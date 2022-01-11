package view;

import javafx.beans.value.ChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Project;
import model.Task;

public class RootPane extends HBox {

    private ListView<Project> lvProjects;
    private Label lblTaskName, lblTaskDuration, lblOr;
    private TextField tfTaskName, tfTaskDuration;
    private Button btnAddProject, btnDeleteProject, btnStart, btnStop, btnResetTimer, btnAddTask,btnDeleteTask;
    private TableView<Task> tableView;

    public RootPane() {

        lvProjects = new ListView<>();

        lblTaskName = new Label("Task Name: ");
        lblTaskDuration = new Label("Task Duration: ");
        lblOr = new Label("OR");

        tfTaskName = new TextField();
        tfTaskDuration = new TextField();

        btnAddProject = new Button("Add Project");
        btnDeleteProject = new Button("Delete Project");
        btnDeleteTask = new Button("Delete Task");
        btnAddTask = new Button("Add to List");
        btnStart = new Button("Start");
        btnStop = new Button("Stop");
        btnResetTimer = new Button("Reset Timer");

        tableView = new TableView<>();
        TableColumn<Task, Integer> column1 = new TableColumn<>("Name");
        column1.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Task, Integer> column2 = new TableColumn<>("Duration");
        column2.setCellValueFactory(new PropertyValueFactory<>("duration"));

        tableView.getColumns().add(column1);
        tableView.getColumns().add(column2);

        HBox hb1 = new HBox(btnDeleteProject, btnAddProject);
        VBox vb1 = new VBox(lvProjects, hb1);

        HBox hb2 = new HBox(btnStart, btnStop, btnResetTimer);
        
        HBox hb3 = new HBox(btnAddTask);
        HBox hb4 = new HBox(lblOr);
        VBox vb3 = new VBox(tableView, btnDeleteTask);

        VBox vb2 = new VBox(lblTaskName, tfTaskName, lblTaskDuration, tfTaskDuration, hb4, hb2, hb3);

        hb2.setSpacing(10);
        hb1.setSpacing(20);
        hb1.setAlignment(Pos.CENTER);
        hb2.setAlignment(Pos.CENTER);
        hb3.setAlignment(Pos.CENTER);
        hb4.setAlignment(Pos.CENTER);
        vb1.setSpacing(20);

        vb2.setSpacing(20);

        this.setPadding(new Insets(20));
        this.setSpacing(20);
        
        vb1.prefWidthProperty().bind(this.widthProperty());
        vb1.prefHeightProperty().bind(this.heightProperty());
        vb2.prefWidthProperty().bind(this.widthProperty());
        vb2.prefHeightProperty().bind(this.heightProperty());
        hb3.setAlignment(Pos.CENTER);
        vb3.setSpacing(20);
        vb3.setAlignment(Pos.CENTER);
        tableView.prefWidthProperty().bind(this.widthProperty());
        tableView.prefHeightProperty().bind(this.heightProperty());
        lvProjects.prefHeightProperty().bind(this.heightProperty());


        this.getChildren().setAll(vb1, vb2, vb3);

    }

    public void setProjectsListItems(ObservableList<Project> projectsList) {
        lvProjects.setItems(projectsList);
    }

    public void createAddProjectHandler(EventHandler<ActionEvent> handler) {
        btnAddProject.setOnAction(handler);
    }

    public void createDeleteProjectHandler(EventHandler<ActionEvent> handler) {
        btnDeleteProject.setOnAction(handler);
    }

    public void createAddTaskHandler(EventHandler<ActionEvent> handler) {
        btnAddTask.setOnAction(handler);
    }

    public void createDeleteTaskHandler(EventHandler<ActionEvent> handler) {
        btnDeleteTask.setOnAction(handler);
    }

    public void createStartTimerHandler(EventHandler<ActionEvent> handler) {
        btnStart.setOnAction(handler);
    }

    public void createStopTimerHandler(EventHandler<ActionEvent> handler) {
        btnStop.setOnAction(handler);
    }

    public void createResetTimerHandler(EventHandler<ActionEvent> handler) {
        btnResetTimer.setOnAction(handler);
    }

    public void createProjectSelectedHandler(ChangeListener<Project> listener) {
        lvProjects.getSelectionModel().selectedItemProperty().addListener(listener);
    }

    public Project getSelectedProject() {
        return lvProjects.getSelectionModel().getSelectedItem();
    }

    public String getTaskName() {
        return tfTaskName.getText();
    }

    public String getTaskDuration() {
        return tfTaskDuration.getText();
    }

    public void setTaskName(String string) {
        tfTaskName.setText(string);
    }

    public void setTaskDuration(String string) {
        tfTaskDuration.setText(string);
    }

    public void displayProjectTasks(ObservableList<Task> tasks) {
        tableView.setItems(tasks);
    }

    public Task getSelectedTask() {
        return tableView.getSelectionModel().getSelectedItem();
    }

}
