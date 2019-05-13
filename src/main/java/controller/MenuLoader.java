package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import view.MainMenu;

import java.io.IOException;

/**
 * Class for loading menus
 */
public class MenuLoader {
    /**
     * The primary stage of the application
     */
    private static Stage primaryStage;

    /**
     * The logger of the MenuLoader class
     */
    private static Logger logger = LoggerFactory.getLogger("Menu Loader Logger");

    /**
     * Sets the primary stage
     * @param primaryStage the new primary stage
     */
    public static void setPrimaryStage(Stage primaryStage)
    {
        MenuLoader.primaryStage = primaryStage;
    }

    /**
     * Navigates to the main menu
     * @throws Exception if MainMenu.fxml could not find
     */
    public static void showMainMenu()
    {
        try
        {
            FXMLLoader loader = new FXMLLoader();
            MainMenu mm = new MainMenu();
            loader.setLocation(mm.getClass().getResource("/fxml/MainMenu.fxml"));
            Pane mainMenu = (Pane) loader.load();

            Scene scene = new Scene(mainMenu);
            primaryStage.setScene(scene);
            primaryStage.centerOnScreen();
            primaryStage.show();



        } catch(IOException e) {
            logger.error(e.getMessage());
        }
    }

    /**
     * Navigates to the search menu
     * @throws Exception if the SearchMenu.fxml could not find
     */
    public static void showSearchMenu()
    {
        try
        {
            FXMLLoader loader = new FXMLLoader();
            MainMenu mm = new MainMenu();
            loader.setLocation(mm.getClass().getResource("/fxml/SearchMenu.fxml"));
            Pane searchMenu = (Pane) loader.load();

            Scene scene = new Scene(searchMenu);
            primaryStage.setScene(scene);
            primaryStage.centerOnScreen();
            primaryStage.show();



        } catch(IOException e) {
            logger.error(e.getMessage());
        }
    }

    /**
     * Navigates to the statistics menu
     * @throws Exception if the StatisticsMenu.fxml could not find
     */
    public static void showStatisticsMenu()
    {
        try
        {
            FXMLLoader loader = new FXMLLoader();
            MainMenu mm = new MainMenu();
            loader.setLocation(mm.getClass().getResource("/fxml/StatisticsMenu.fxml"));
            Pane statisticsMenu = (Pane) loader.load();

            Scene scene = new Scene(statisticsMenu);
            primaryStage.setScene(scene);
            primaryStage.centerOnScreen();
            primaryStage.show();



        } catch(IOException e) {
            logger.error(e.getMessage());
        }
    }
}
