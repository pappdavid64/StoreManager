package controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import util.DBConnector;

public class MainMenuController{

    @FXML
    private void showSearchMenu()
    {
        MenuLoader.showSearchMenu();
    }


    @FXML
    private void showStatisticsMenu()
    {
        MenuLoader.showStatisticsMenu();
    }

    @FXML
    private void exit()
    {
        DBConnector.closeEntityManager();
        Platform.exit();
    }
}
