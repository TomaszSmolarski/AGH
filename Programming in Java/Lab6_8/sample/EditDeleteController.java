package sample;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditDeleteController {
    @FXML
    public Text text;
    public int editable=0;


    public void EditButtonClicked()
    {
        editable=1;
        Stage stagea = (Stage) text.getScene().getWindow();
        stagea.close();
    }

    public void DeleteButtonClicked()
    {
        editable=2;
        Stage stagea = (Stage) text.getScene().getWindow();
        stagea.close();
    }

}
