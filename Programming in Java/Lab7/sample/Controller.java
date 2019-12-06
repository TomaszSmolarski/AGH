package sample;

//import javafx.embed.swing.SwingFXUtils;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
//import javafx.scene.paint.*;
//import java.awt.Color;


//import java.awt.image.BufferedImage;
//import java.util.Random;

public class Controller {

    private double calka;
    @FXML
    private TextField textField;
    @FXML
    private Canvas canvas;
    @FXML
    private AsyncTask task;
    @FXML
    private Text text;
    @FXML
    private ProgressBar progressBar;
    @FXML
    private void drawShapes(GraphicsContext gc) {
        gc.setFill(Color.BLUEVIOLET);
        gc.fillRect(gc.getCanvas().getLayoutX(),
                gc.getCanvas().getLayoutY(),
                gc.getCanvas().getWidth(),
                gc.getCanvas().getHeight());



        task = new AsyncTask(gc, Integer.parseInt(textField.getText()));
        new Thread(task).start();

        progressBar.progressProperty().bind(task.progressProperty());

        task.setOnSucceeded(event -> Platform.runLater(()->{text.setText(task.getValue().toString());}));





    }




    public void startButtonClicked()
    {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        drawShapes(gc);

    }


    public void stopButtonClicked()
    {
        task.cancel();
    }
}
