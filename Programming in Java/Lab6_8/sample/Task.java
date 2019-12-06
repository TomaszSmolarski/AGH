package sample;


//import javafx.scene.paint.Color;


import javax.swing.text.html.ListView;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Task implements Serializable {

   public String title;
   public TaskPriority priority;
   public String expData;
   public String text;
   public Color color ;
   public String taskName;
    Task(){}
    Task(String title,TaskPriority priority,String expData,String text)  {
        this.title=title;
        this.priority=priority;
        this.expData=expData;
        this.text=text;
        if(this.priority==TaskPriority.LOW){color=Color.GREEN;}
        else if(this.priority==TaskPriority.MEDIUM){color=Color.YELLOW;}
        else if(this.priority==TaskPriority.HIGH){color=Color.RED;}
        else {color=Color.BLACK;}

    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getTitle() {
        return title;
    }

    public TaskPriority getPriority() {
        return priority;
    }

    public String getExpData() {
        return expData;
    }

    public String getText() {
        return text;
    }

    public Color getColor() {
        return color;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPriority(TaskPriority priority) {
        this.priority = priority;
        if(this.priority==TaskPriority.LOW){color=Color.GREEN;}
        else if(this.priority==TaskPriority.MEDIUM){color=Color.YELLOW;}
        else if(this.priority==TaskPriority.HIGH){color=Color.RED;}
        else {color=Color.BLACK;}

    }



    public void setExpData(String expData) {
        this.expData = expData;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString(){
        return this.title;
   }
    public static void serial(Controller x,String File) throws IOException
        {
            try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(File))) {
               outputStream.writeObject(new ArrayList<>(x.getToDoList()));
               outputStream.writeObject(new ArrayList<>(x.getInProgressList()));
               outputStream.writeObject(new ArrayList<>(x.getDoneList()));
            }

        }
    public static List<Task>  deserial(Controller x,String file) throws IOException, ClassNotFoundException {

        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file))) {
            List<Task> readObject = (ArrayList<Task>) inputStream.readObject();
            readObject.addAll((ArrayList<Task>) inputStream.readObject());
            readObject.addAll((ArrayList<Task>) inputStream.readObject());
            return readObject;
        }
    }

    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
    }

    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
    }
}

