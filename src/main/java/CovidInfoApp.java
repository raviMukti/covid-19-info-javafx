import controller.DashboardController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import javax.swing.*;
import java.net.URL;
import java.net.URLConnection;


public class CovidInfoApp extends Application {

    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("view/Home.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setResizable(false);
        primaryStage.setTitle("COVID-19 INFO");
        primaryStage.centerOnScreen();
        primaryStage.setScene(scene);
        primaryStage.show();
        try {
            URL url = new URL("https://www.google.com");
            URLConnection urlConnection = url.openConnection();
            urlConnection.connect();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/Dashboard.fxml"));
            Parent rootDashboad = (Parent) loader.load();
            DashboardController dashboardController = loader.getController();
            dashboardController.setLabelTimeWorld();
            dashboardController.setLabelTimeCountry();
            Stage stageDashboard = new Stage();
            stageDashboard.setResizable(false);
            Scene sceneDashboard = new Scene(rootDashboad);
            stageDashboard.setTitle("COVID19 - INFO");
            stageDashboard.setScene(sceneDashboard);
            stageDashboard.show();
            primaryStage.getScene().getWindow().hide();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Koneksi gagal, harap periksa internet anda! " + e);
            primaryStage.close();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
