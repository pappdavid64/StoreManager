package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import view.MainMenu;

import java.io.IOException;

public class MenuLoader {
    private static Stage primaryStage;

    public static void setPrimaryStage(Stage primaryStage)
    {
        MenuLoader.primaryStage = primaryStage;
    }

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

        }
    }

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

        }
    }

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

        }
    }
}
