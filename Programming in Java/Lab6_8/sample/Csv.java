package sample;

import javafx.scene.control.ListView;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Csv {
    private static final String CSV_SEPARATOR = ";";
    public static void writeToCSV(ListView<Task> taskList, BufferedWriter bw)
    {
        try
        {
            for (Task task : taskList.getItems()) {
                StringBuffer oneLine = new StringBuffer();
                oneLine.append(task.getTitle());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(task.getPriority());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(task.getExpData());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(task.getText());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(task.getColor());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(task.getTaskName());
                bw.write(oneLine.toString());
                bw.newLine();
            }


        }
        catch (UnsupportedEncodingException e) {}
        catch (FileNotFoundException e){}
        catch (IOException e){}
    }

    public static List<Task> readFromCSV(File file) {
        List<Task> tasks = new ArrayList<>();
        Path pathToFile = Paths.get(file.getAbsolutePath());
        try (BufferedReader br = Files.newBufferedReader(pathToFile,
                StandardCharsets.US_ASCII)) {
            String line = br.readLine();

            while (line != null) {
                String[] attributes = line.split(CSV_SEPARATOR);
                if(attributes.length!=6)
                {return null;}
                Task task = createTask(attributes);
                tasks.add(task);
                line = br.readLine();
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return tasks;
    }

    private static Task createTask(String[] metadata) {

        String title = metadata[0];
        String priority = metadata[1];
        String expData = metadata[2];
        String text = metadata[3];
        String taskName= metadata[5];
        Task t;
        if(!priority.equals("LOW")&&!priority.equals("MEDIUM")&&!priority.equals("HIGH")){
            t=new Task(title,null,expData,text);
        }else {
            TaskPriority taskPriority = TaskPriority.valueOf(priority);
            t=new Task(title,taskPriority,expData,text);
        }
        t.taskName=taskName;
        return t;
    }
}
