package src;

import java.util.ArrayList;
import java.util.concurrent.*;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Track {
    public Car car;
    public ArrayList<Line> lines;
    public Visualization visualization;
    public Controller controller;

    public Track(String trackName) throws Exception{
        this.car = new Car(new Vector2d(400,150), new Vector2d(450,150));
        this.lines = Parser.parse("tracks/"+trackName+".txt");

        this.visualization = new Visualization(trackName, this.car);

        this.controller = new Controller();
        this.visualization.lines.addAll(this.lines);
        // while(true){
        for(int i = 0; i<10000; i++){
            
            Distances distances = car.processLines(lines);
            double angle = this.controller.getAngle(distances);
            
            Line line = new Line(this.car.center, this.car.center.sum(this.car.front.sub(this.car.center).unit().mul(distances.front)));
            Line line1 = new Line(this.car.center, this.car.center.sum(this.car.leftFront().sub(this.car.center).unit().mul(distances.left)));
            Line line2 = new Line(this.car.center, this.car.center.sum(this.car.rightFront().sub(this.car.center).unit().mul(distances.right)));
            ArrayList<Line> ls = new ArrayList<Line>();
            ls.add(line);
            ls.add(line1);
            ls.add(line2);
            this.visualization.lines = ls;

            this.visualization.repaint();


            
            TimeUnit.MILLISECONDS.sleep(200);
            
            this.car.rotate(-angle  * 3.14 / 180);
            this.car.move();
            if(i%5 == 0){
            try{
                BufferedImage image = new BufferedImage(1123, 794, BufferedImage.TYPE_INT_RGB);
                Graphics2D graphics2D = image.createGraphics();
                this.visualization.paint(graphics2D);
                ImageIO.write(image,"png", new File("screens/screen" + Integer.toString((i/5)) + ".png"));
            }catch(Exception exception)
            {
                System.out.println(exception);
            }}

        }

    }

    public Track() throws Exception{
        this("track1");
    }
}