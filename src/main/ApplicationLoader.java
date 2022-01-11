package main;

import controller.Controller;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.ProjectsList;
import model.Timer;
import view.RootPane;

public class ApplicationLoader extends Application {

    private RootPane view;

    @Override
    public void init() {
        //create view and model and pass their references to the controller
        view = new RootPane();
        ProjectsList model = new ProjectsList();
        Timer timer = new Timer();
        new Controller(view, model, timer);
    }

    @Override
    public void start(Stage stage) throws Exception {

        stage.setMinWidth(770);
        stage.setMinHeight(350);

        stage.setTitle("TTrack");
        stage.setScene(new Scene(view, 770, 500));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
