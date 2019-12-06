package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.*;
import java.util.List;

public class Controller {

    @FXML
    public ListView<Task> toDoList;
    public ListView<Task> inProgressList;
    public ListView<Task> doneList;
    public int whichList=0;
    private Stage stage=new Stage();
    private Stage stageEditDelete=new Stage();
    private TaskController taskController;
    private EditDeleteController editDeleteController;
    public Tooltip tooltip;
    public FileChooser fileChooser;

    public List<Task> getToDoList() {
        return toDoList.getItems();
    }

    public List<Task> getInProgressList() {
        return inProgressList.getItems();
    }

    public List<Task> getDoneList() {
        return doneList.getItems();
    }

    public void fromToDoToInProgressListButtonClicked()
    {
        if(toDoList.getSelectionModel().getSelectedItem()!=null) {
            toDoList.getSelectionModel().getSelectedItem().setTaskName("inProgressList");
            inProgressList.getItems().add(toDoList.getSelectionModel().getSelectedItem());
            toDoList.getItems().remove(toDoList.getSelectionModel().getSelectedItem());
        }
    }
    public void fromInProgressToDoneListButtonClicked()
    {
        if(inProgressList.getSelectionModel().getSelectedItem()!=null) {
            inProgressList.getSelectionModel().getSelectedItem().setTaskName("doneList");
            doneList.getItems().add(inProgressList.getSelectionModel().getSelectedItem());
            inProgressList.getItems().remove(inProgressList.getSelectionModel().getSelectedItem());
        }
    }
    public void fromInProgressToToDoListButtonClicked()
    {   if(inProgressList.getSelectionModel().getSelectedItem()!=null) {
        inProgressList.getSelectionModel().getSelectedItem().setTaskName("toDoList");
        toDoList.getItems().add(inProgressList.getSelectionModel().getSelectedItem());
        inProgressList.getItems().remove(inProgressList.getSelectionModel().getSelectedItem());
    }
    }
    public void fromDoneToInProgressListButtonClicked()
    {   if(doneList.getSelectionModel().getSelectedItem()!=null) {
        doneList.getSelectionModel().getSelectedItem().setTaskName("inProgressList");
        inProgressList.getItems().add(doneList.getSelectionModel().getSelectedItem());
        doneList.getItems().remove(doneList.getSelectionModel().getSelectedItem());
    }
    }
    public void menuOpen()
    {
        try{

            fileChooser = new FileChooser();
            fileChooser.setTitle("Open Resource File");
            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("BIN", "*.bin"));
            File file=fileChooser.showOpenDialog(stage);
            if(file!=null) {
                clearLists();
            List<Task> list = Task.deserial(this, file.getAbsolutePath());

            for (Task task : list) {
                addTaskWithListName(task);
                }
                            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void clearLists()
    {
        toDoList.getItems().clear();
        inProgressList.getItems().clear();
        doneList.getItems().clear();
    }
    public void addTaskWithListName(Task task)
    {
        if (task.taskName.equals("toDoList")) {
            toDoList.getItems().add(task);
        } else if (task.taskName.equals("inProgressList")) {
            inProgressList.getItems().add(task);
        } else if (task.taskName.equals("doneList")) {
            doneList.getItems().add(task);
        }
    }
    public void menuSave()
    {
        try {

            fileChooser = new FileChooser();
            fileChooser.setTitle("Open Resource File");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("BIN", "*.bin"));

            File file=fileChooser.showSaveDialog(stage);
            if(file!=null) {
                Task.serial(this, file.getAbsolutePath());
                clearLists();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void menuImport()
    {
        try
        {
            fileChooser = new FileChooser();
            fileChooser.setTitle("Open Resource File");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("CSV", "*.csv"));

            File file=fileChooser.showSaveDialog(stage);
            if(file!=null) {

                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file.getAbsoluteFile()), "UTF-8"));
                Csv.writeToCSV(toDoList, bw);
                Csv.writeToCSV(inProgressList, bw);
                Csv.writeToCSV(doneList, bw);
                bw.flush();
                bw.close();
                clearLists();
            }

        }
        catch (UnsupportedEncodingException e) {}
        catch (FileNotFoundException e){}
        catch (IOException e){}
    }
    public void menuExport()
    {
        fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("CSV", "*.csv"));

        File file=fileChooser.showOpenDialog(stage);
        if(file!=null)
        {clearLists();

            if(Csv.readFromCSV(file)!=null){
                List<Task> lista=Csv.readFromCSV(file);
            for(Task task: lista)
            {
                addTaskWithListName(task);
            }}
        }

    }
    public void menuClose()
    {
        Stage stagea = (Stage) toDoList.getScene().getWindow();
        stagea.close();
    }

    public void menuAbout()
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText("Autor: Tomasz Smolarski");
        alert.showAndWait();
    }

    public void ListSelected(){


        toDoList.setOnMouseClicked(new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent arg0) {
                whichList=1;
                Listowanie(arg0, toDoList);
                if(arg0.getButton()==MouseButton.PRIMARY)
                {
                    if(toDoList.getSelectionModel().getSelectedItem()!=null) {
                        tooltip.setText(toDoList.getSelectionModel().getSelectedItem().text);
                        toDoList.setTooltip(tooltip);
                    }
                }/*
                if(arg0.getButton()== MouseButton.MIDDLE) {
                    if(toDoList.getSelectionModel().getSelectedItem()!=null)
                    {
                     inProgressList.getItems().add(toDoList.getSelectionModel().getSelectedItem());
                     toDoList.getItems().remove(toDoList.getSelectionModel().getSelectedItem());
                    }
                }*/
            }
        });

       inProgressList.setOnMouseClicked(new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent arg0) {
                whichList=2;
                Listowanie(arg0, inProgressList);
                if(arg0.getButton()==MouseButton.PRIMARY)
                {
                    if(inProgressList.getSelectionModel().getSelectedItem()!=null) {
                        tooltip.setText(inProgressList.getSelectionModel().getSelectedItem().text);
                        inProgressList.setTooltip(tooltip);
                    }
                }
                /*
                if(arg0.getButton()== MouseButton.MIDDLE) {
                    if(inProgressList.getSelectionModel().getSelectedItem()!=null)
                    {
                        doneList.getItems().add(inProgressList.getSelectionModel().getSelectedItem());
                        inProgressList.getItems().remove(inProgressList.getSelectionModel().getSelectedItem());
                    }
                }*/
            }
        });

        doneList.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent arg0) {
                whichList=3;
                Listowanie(arg0, doneList);
                if(arg0.getButton()==MouseButton.PRIMARY)
                {
                    if(doneList.getSelectionModel().getSelectedItem()!=null) {
                        tooltip.setText(doneList.getSelectionModel().getSelectedItem().text);
                        doneList.setTooltip(tooltip);
                    }
                }
            }
        });

        stageEditDelete.setOnHiding(event -> {
            Task tmp;
            if(editDeleteController.editable==1){

                tmp = SwitchGetTask();
                MakeTaskControllerStage();
                Color color;

                taskController.titleTextField.setText(tmp.title);
                taskController.priorityChoiceBox.setValue(tmp.priority);
                taskController.expDataTextField.setText(tmp.expData);
                taskController.textTextArea.setText(tmp.text);


                SwitchRemoveTask(tmp);

                stage.show();
            }
            else if(editDeleteController.editable==2){
                tmp = SwitchGetTask();
                SwitchRemoveTask(tmp);
            }

        });

    }

    private Task SwitchGetTask() {
        Task tmp;
        switch (whichList){
            case 1:{tmp= toDoList.getSelectionModel().getSelectedItem();break;}
            case 2:{tmp= inProgressList.getSelectionModel().getSelectedItem();break;}
            case 3:{tmp= doneList.getSelectionModel().getSelectedItem();break;}
            default:{tmp= toDoList.getSelectionModel().getSelectedItem();}
        }
        return tmp;
    }

    private void SwitchRemoveTask(Task tmp) {
        switch (whichList){
            case 1:{toDoList.getItems().remove(tmp);break;}
            case 2:{inProgressList.getItems().remove(tmp);break;}
            case 3:{doneList.getItems().remove(tmp);break;}
            default:{toDoList.getItems().remove(tmp);}
        }
    }

    private void Listowanie(MouseEvent arg0, ListView<Task> list) {
        if(arg0.getButton()== MouseButton.SECONDARY && !list.getItems().isEmpty()&& list.getSelectionModel().getSelectedItem()!=null) {
            MakeEditDeleleControllerStage();
            Task tmp=SwitchGetTask();

            editDeleteController.text.setText(tmp.title);
            stageEditDelete.show();
        }
    }

    public void MakeEditDeleleControllerStage()
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("editDeleteWindow.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        editDeleteController=loader.getController();
        stageEditDelete.setTitle("EditOrDelete");
        stageEditDelete.setScene(new Scene(root, 500, 300));
        stageEditDelete.setResizable(false);
    }

    public void MakeTaskControllerStage()
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("taskWindow.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        taskController=loader.getController();
        stage.setTitle("Kanban");
        stage.setScene(new Scene(root, 546, 471));
        stage.setResizable(false);
    }


    public void handleButtonAction(ActionEvent actionEvent)throws IOException {

        MakeTaskControllerStage();
        taskController.editable=true;
        stage.show();
        stage.setOnHiding(event -> {
            if(taskController.editable && !taskController.titleTextField.getText().equals("")){
                Task t=taskController.returnTask();
                switch (whichList){
                    case 1:{t.setTaskName("toDoList"); toDoList.getItems().add(t);break;}
                    case 2:{t.setTaskName("inProgressList"); inProgressList.getItems().add(t);break;}
                    case 3:{t.setTaskName("doneList");doneList.getItems().add(t);break;}
                    default:{t.setTaskName("toDoList"); toDoList.getItems().add(t);}
                }
                stage.close();
            }


        });

    }
    public void initialize() {

        toDoList.setCellFactory(lv -> new ListCell<Task>() {
            @Override
            protected void updateItem(Task item, boolean empty) {

                super.updateItem(item, empty);
                if (item == null) {
                    setText(null);
                    setTextFill(null);
                } else {
                    setText(item.title);
                    setTextFill(ColorUtil.awtToFx(item.color));
                }
            }
        });

        inProgressList.setCellFactory(lv -> new ListCell<Task>() {
            @Override
            protected void updateItem(Task item, boolean empty) {

                super.updateItem(item, empty);
                if (item == null) {
                    setText(null);
                    setTextFill(null);
                } else {
                    setText(item.title);
                    setTextFill(ColorUtil.awtToFx(item.color));
                }
            }
        });

        doneList.setCellFactory(lv -> new ListCell<Task>() {
            @Override
            protected void updateItem(Task item, boolean empty) {

                super.updateItem(item, empty);
                if (item == null) {
                    setText(null);
                    setTextFill(null);
                } else {
                    setText(item.title);
                    setTextFill(ColorUtil.awtToFx(item.color));
                }
            }
        });
    }








}
