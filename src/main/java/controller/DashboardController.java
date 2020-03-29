package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.CovidByCountry;
import model.CovidTotals;
import model.TableCovidCountry;
import model.TableCovidTotal;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import service.CovidInfoService;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {
    @FXML
    private TableView<TableCovidTotal> tableWorld;

    @FXML
    private TableColumn<TableCovidTotal, String> colRangeWorld;

    @FXML
    private TableColumn<TableCovidTotal, String> colPosWorld;

    @FXML
    private TableColumn<TableCovidTotal, String> colRecWorld;

    @FXML
    private TableColumn<TableCovidTotal, String> colDeathsWorld;

    @FXML
    private Label labelTimeWorld;

    @FXML
    private Button btnUpdatesWorld;

    @FXML
    private TableView<TableCovidCountry> tableCountry;

    @FXML
    private TableColumn<TableCovidCountry, String> colRangeCountry;

    @FXML
    private TableColumn<TableCovidCountry, String> colPosCountry;

    @FXML
    private TableColumn<TableCovidCountry, String> colRecCountry;

    @FXML
    private TableColumn<TableCovidCountry, String> colDeathsCountry;

    @FXML
    private Label labelTimeCountry;

    @FXML
    private Button btnUpdatesCountry;

    @FXML
    private Button btnSearchCountry;

    @FXML
    private TextField fieldSearchCountry;

    @FXML
    private Button btnReset;

    @FXML
    void btnResetAction(ActionEvent event) {
        fieldSearchCountry.setText("");
    }

    @FXML
    void btnSearchCountryAction(ActionEvent event) throws IOException {
        String name = fieldSearchCountry.getText().toLowerCase();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://covid-19-data.p.rapidapi.com/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        CovidInfoService service = retrofit.create(CovidInfoService.class);
        List<CovidByCountry> infoByCountry = service.getByCountry(name).execute().body();
        ObservableList<TableCovidCountry> listCovidCountryBySearch = FXCollections.observableArrayList();
        for (CovidByCountry covidSearchByCountry : infoByCountry ) {
            TableCovidCountry tableCovidCountry = new TableCovidCountry();
            tableCovidCountry.setRangeByCountry(covidSearchByCountry.getCountry());
            tableCovidCountry.setConfirmedByCountry(String.valueOf(covidSearchByCountry.getConfirmed()));
            tableCovidCountry.setRecoveredByCountry(String.valueOf(covidSearchByCountry.getRecovered()));
            tableCovidCountry.setDeathsByCountry(String.valueOf(covidSearchByCountry.getDeaths()));
            listCovidCountryBySearch.add(tableCovidCountry);
        }
        tableCountry.setItems(listCovidCountryBySearch);
        setLabelTimeCountry();
        System.out.println(infoByCountry);
    }

    @FXML
    void btnUpdatesCountryAction(ActionEvent event) {
        initKolomByCountry();
        setColomnTableByCountry();
        setLabelTimeCountry();
    }

    @FXML
    void btnUpdatesWorldAction(ActionEvent event) {
        initKolomWorld();
        setColomnTableWorld();
        setLabelTimeWorld();
    }

    public void initialize(URL location, ResourceBundle resources) {
        initKolomWorld();
        setColomnTableWorld();
        initKolomByCountry();
        setColomnTableByCountry();
    }
    // Get Data
    private List<CovidTotals> getTotals() throws IOException {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://covid-19-data.p.rapidapi.com/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        CovidInfoService service = retrofit.create(CovidInfoService.class);
        List<CovidTotals> infoTotal = service.getTotals().execute().body();
        return infoTotal;
    }

    // Get Data Covid By Country
    private List<CovidByCountry> getTotalByCountry() throws IOException {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://covid-19-data.p.rapidapi.com/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        CovidInfoService service = retrofit.create(CovidInfoService.class);
        List<CovidByCountry> infoByCountry = service.getByCountry("indonesia").execute().body();
        return (infoByCountry);
    }

    public void setLabelTimeWorld(){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        labelTimeWorld.setText(dateFormat.format(date));
    }

    public void setLabelTimeCountry(){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        labelTimeCountry.setText(dateFormat.format(date));
    }

    void initKolomWorld(){
        colRangeWorld.setCellValueFactory(new PropertyValueFactory<TableCovidTotal, String>("rangeWorld"));
        colPosWorld.setCellValueFactory(new PropertyValueFactory<TableCovidTotal, String>("confirmed"));
        colRecWorld.setCellValueFactory(new PropertyValueFactory<TableCovidTotal, String>("recovered"));
        colDeathsWorld.setCellValueFactory(new PropertyValueFactory<TableCovidTotal, String>("deaths"));
    }

    void setColomnTableWorld(){
        ObservableList<TableCovidTotal> listCovidTotalWorld = FXCollections.observableArrayList();
        try {
            getTotals();
            for (CovidTotals covidWorld : getTotals() ) {
                TableCovidTotal tableCovidTotal = new TableCovidTotal();
                tableCovidTotal.setRangeWorld("Dunia");
                tableCovidTotal.setConfirmed(covidWorld.getConfirmed());
                tableCovidTotal.setRecovered(covidWorld.getRecovered());
                tableCovidTotal.setDeaths(covidWorld.getDeaths());
                listCovidTotalWorld.add(tableCovidTotal);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        tableWorld.setItems(listCovidTotalWorld);
    }

    void initKolomByCountry(){
        colRangeCountry.setCellValueFactory(new PropertyValueFactory<TableCovidCountry, String>("rangeByCountry"));
        colPosCountry.setCellValueFactory(new PropertyValueFactory<TableCovidCountry, String>("confirmedByCountry"));
        colRecCountry.setCellValueFactory(new PropertyValueFactory<TableCovidCountry, String>("recoveredByCountry"));
        colDeathsCountry.setCellValueFactory(new PropertyValueFactory<TableCovidCountry, String>("deathsByCountry"));
    }

    void setColomnTableByCountry(){
        ObservableList<TableCovidCountry> listCovidByCountry = FXCollections.observableArrayList();
        try {
            getTotalByCountry();
            for (CovidByCountry covidByCountry: getTotalByCountry() ) {
                TableCovidCountry tableCovidCountry = new TableCovidCountry();
                tableCovidCountry.setRangeByCountry(covidByCountry.getCountry());
                tableCovidCountry.setConfirmedByCountry(String.valueOf(covidByCountry.getConfirmed()));
                tableCovidCountry.setRecoveredByCountry(String.valueOf(covidByCountry.getRecovered()));
                tableCovidCountry.setDeathsByCountry(String.valueOf(covidByCountry.getDeaths()));
                listCovidByCountry.add(tableCovidCountry);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        tableCountry.setItems(listCovidByCountry);
    }
}
