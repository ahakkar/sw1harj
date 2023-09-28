package fi.swdesign1harj;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DataInputController {

    private final UserService userService = UserService.getInstance();

    @FXML
    private Button submitButton;

    @FXML
    private Button cancelButton;

    @FXML
    private TextField stepsField;

    @FXML
    private TextField caloriesField;

    @FXML
    private TextField timeField;

    @FXML
    private void handleSubmitAction() {
        // Validate the input
        if (isFieldDataValid()) {
            userService.saveData(
                stepsField.getText(),
                caloriesField.getText(),
                timeField.getText()
            );

            // Close this window
            Stage stage = (Stage) submitButton.getScene().getWindow();
            stage.close();
        }
    }

    /**
     * TODO implement validation
     * @return
     */
    private Boolean isFieldDataValid() {
        return true;
    }

}