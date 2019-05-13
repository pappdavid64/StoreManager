package view;

import controller.*;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import util.DBConnector;


/**
 * Entry class of the application
 */
public class MainMenu extends Application {


    /* (non-Javadoc)
      * @see javafx.application.Application#start(javafx.stage.Stage)
      */
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

    /**
     * Main method of the application.
     * @param args arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}

