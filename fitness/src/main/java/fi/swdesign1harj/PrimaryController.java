package fi.swdesign1harj;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale.Category;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class PrimaryController {

    private final UserService userService = UserService.getInstance();

    @FXML
    private ComboBox<String> trackerComboBox;

    @FXML
    private DatePicker fromDatePicker;

    @FXML
    private DatePicker toDatePicker;

    @FXML
    private CheckBox caloriesCheckBox;

    @FXML
    private CheckBox distanceCheckBox;

    @FXML
    private CheckBox bpCheckBox;

    @FXML
    private CheckBox stepsCheckBox;

    @FXML
    private CheckBox hrCheckBox;

    @FXML
    private AnchorPane chartContainerAnchorPane;

    @FXML
    private XYChart<?,?> dataLineChart;

    @FXML
    private TreeView<String> userTreeView;

    @FXML
    public void initialize() {
        System.out.println("Initializing..");
        initChoices();
        initGroups();
    }


    /**
     * Populate checkboxes etc with some default values
     */
    private void initChoices() {
        System.out.println("Adding choices..");
        trackerComboBox.getItems().addAll(
            "Garmin vivoactive 4",
            "Apple Watch Ultra 2",
            "Polar Ignite 3"
        );
        trackerComboBox.setPromptText("Please select an option");

        fromDatePicker.setValue(LocalDate.now().minusDays(1));
        toDatePicker.setValue(LocalDate.now());

        stepsCheckBox.setSelected(true);
        caloriesCheckBox.setSelected(true);
    }

    /**
     * Populate the groups with some placeholder data
     */
    private void initGroups() {
        TreeItem<String> rootItem = new TreeItem<String>("All groups");
        rootItem.setExpanded(true);

        TreeItem<String> group1 = new TreeItem<String>("Uni friends");
        group1.getChildren().add(new TreeItem<String>("Mika"));
        group1.getChildren().add(new TreeItem<String>("Venla"));
        group1.getChildren().add(new TreeItem<String>("Jaakko"));
        group1.getChildren().add(new TreeItem<String>("Riikka"));
        group1.getChildren().add(new TreeItem<String>("Aatos"));
        group1.setExpanded(true);

        TreeItem<String> group2 = new TreeItem<String>("Ajakaa");
        group2.getChildren().add(new TreeItem<String>("Pertti"));
        group2.getChildren().add(new TreeItem<String>("Matti"));
        group2.getChildren().add(new TreeItem<String>("Kaisa"));

        TreeItem<String> group3 = new TreeItem<String>("Family");
        group3.getChildren().add(new TreeItem<String>("Mom"));
        group3.getChildren().add(new TreeItem<String>("Dad"));
        group3.setExpanded(true);

        rootItem.getChildren().add(group1);
        rootItem.getChildren().add(group2);
        rootItem.getChildren().add(group3);

        userTreeView.setRoot(rootItem);

    }

    /**
     * Create a chart when user selects a tracker
     */
    @FXML
    private void createChartAction() {
        System.out.println("Creating a chart");
        XYChart<String, Number> chart = createChart();

        chartContainerAnchorPane.getChildren().clear();
        AnchorPane.setTopAnchor(chart, 0.0);
        AnchorPane.setRightAnchor(chart, 0.0);
        AnchorPane.setBottomAnchor(chart, 100.0);
        AnchorPane.setLeftAnchor(chart, 0.0);
        chartContainerAnchorPane.getChildren().add(chart);
    }

    /**
     * Prototype which creates a linechart with random data
     */
    private XYChart<String, Number> createChart() {
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();        
        LineChart<String, Number> chart = new LineChart<>(xAxis, yAxis);

        UserData data = userService.getData();
        ArrayList<Integer> steps = data.getSteps();
        ArrayList<Integer> burnedCalories = data.getBurnedCalories();
        ArrayList<String> time = data.getTime();

        XYChart.Series<String, Number> series = new XYChart.Series<>();

        if(stepsCheckBox.isSelected()) {
            for (int i = 0; i < time.size(); i++) {
                series.getData().add(new XYChart.Data<>(time.get(i), steps.get(i)));
            }
            series.setName("Steps");
            chart.getData().add(series);

            // Series 2
            series = new XYChart.Series<>();
        }

        if(caloriesCheckBox.isSelected()) {
            for (int i = 0; i < time.size(); i++) {
                series.getData().add(new XYChart.Data<>(time.get(i), burnedCalories.get(i)));
            }
            series.setName("Calories");
            chart.getData().add(series);
        }

        return chart;
    }

    @FXML 
    private void onStepsCheckBoxAction() {
        createChartAction();
    }

    @FXML
    private void onCaloriesCheckBoxAction() {
        createChartAction();
    }
    

    @FXML
    private void queryUserDataAction() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("datainput.fxml"));
        Parent root = loader.load();
        DataInputController controller = loader.getController();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }
}
