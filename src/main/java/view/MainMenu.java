package view;

import controller.*;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import util.DBConnector;


public class MainMenu extends Application {

    @Override
    public void start(Stage primaryStage){
        primaryStage.setTitle("Store Manager");
        MenuLoader.setPrimaryStage(primaryStage);
        MenuLoader.showMainMenu();

        DBConnector.initEntityManager();

        primaryStage.setOnCloseRequest(
                (WindowEvent e) ->
                {
                    DBConnector.closeEntityManager();
                }
        );

    }

    public static void main(String[] args) {
        launch(args);
    }
}

