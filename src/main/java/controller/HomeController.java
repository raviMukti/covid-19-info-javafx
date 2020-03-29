package controller;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.CovidTotals;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import service.CovidInfoService;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    @FXML
    private Label labelLoading;

    public void initialize(URL location, ResourceBundle resources) {
        labelLoading.setVisible(false);

    }

}
