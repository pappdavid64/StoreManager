package controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import util.DBConnector;

/**
 * Controller class for the main menu.
 */
public class MainMenuController{

    /**
     * Navigates to the search menu.
     */
    @FXML
    private void showSearchMenu() {
        MenuLoader.showSearchMenu();
    }

    /**
     * Navigates to the statistics menu.
     */
    @FXML
    private void showStatisticsMenu() {
        MenuLoader.showStatisticsMenu();
    }

    /**
     * Close the entity manager and exit from the application.
     */
    @FXML
    private void exit() {
        DBConnector.closeEntityManager();
        Platform.exit();
    }
}
