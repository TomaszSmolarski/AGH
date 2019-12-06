package sample;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class AsyncTask extends Task {

    public GraphicsContext gc;
    public  BufferedImage bi;
    public int i=0;
    public int maxPunkt;
    public int MIN=-8;
    public int MAX=8;
    public int hits;
    public double calka;

    AsyncTask(GraphicsContext gc, int i)
    {this.gc=gc;this.bi= new BufferedImage((int)gc.getCanvas().getWidth(), (int)gc.getCanvas().getHeight(), BufferedImage.TYPE_INT_RGB);
    this.maxPunkt=i;

    }
    @Override
    protected Object call() throws Exception {
        hits=0;
        Random random = new Random();
        double x;
        double nx;
        double y;
        double ny;
        while (true){
            updateProgress(i, maxPunkt);
            i++;
                x = MIN + (MAX -MIN) * random.nextDouble();
                nx = -1*(gc.getCanvas().getWidth() ) / (MAX-MIN) ;
                y = MIN + (MAX -MIN) * random.nextDouble();
                ny =-1* (gc.getCanvas().getHeight() )/ (MAX-MIN) ;

                if (Equation.calc(x, y)) {
                    bi.setRGB((int) (x*nx+gc.getCanvas().getWidth()/2), (int) (y*ny+gc.getCanvas().getHeight()/2), Color.YELLOW.getRGB());
                    hits++;

                } else {
                    bi.setRGB((int) (x*nx+gc.getCanvas().getWidth()/2), (int) (y*ny+gc.getCanvas().getHeight()/2), Color.BLUE.getRGB());

                    if (i % 10000 == 0){final WritableImage img =SwingFXUtils.toFXImage(bi, null);
                    Platform.runLater(()->gc.drawImage(img, 0, 0));}

                }


                //System.out.println(i);



            if (i==maxPunkt) break;
        if(isCancelled()) { break;  }}
        calka=(double)(MAX-MIN)*(MAX-MIN)*hits/maxPunkt;
        return calka;
    }


}