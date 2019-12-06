package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class TaskController implements Initializable {
    @FXML
    TextField titleTextField;
    @FXML
    TextField expDataTextField;
    @FXML
    ChoiceBox priorityChoiceBox;
    @FXML
    TextArea textTextArea;
    @FXML
    Button button;
    private Task task;
    public boolean editable=true;

    public Task returnTask(){
        task = new Task(titleTextField.getText(), (TaskPriority) priorityChoiceBox.getValue(), expDataTextField.getText(), textTextArea.getText());
        return task;
    }

    public void taskWindowButton(ActionEvent actionEvent) {
        if(!titleTextField.getText().equals("")) {
            Stage stage = (Stage) button.getScene().getWindow();
            stage.close();
        }else{
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("write something !");
        alert.showAndWait();}

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Controller tmp=new Controller();
        priorityChoiceBox.getItems().add(TaskPriority.LOW);
        priorityChoiceBox.getItems().add(TaskPriority.MEDIUM);
        priorityChoiceBox.getItems().add(TaskPriority.HIGH);
    }


}
