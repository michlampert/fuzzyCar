
import java.util.ArrayList;
import java.util.concurrent.*;

import java.awt.*;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Track {
    public Car car;
    public ArrayList<Line> lines;
    public Visualization visualization;
    public Controller controller;

    public Track(){
        this.car = new Car(new Vector2d(400,400));
        this.lines = Parser.parse("lines.txt");

        this.visualization = new Visualization(this.car);

        this.controller = new Controller();
        
        while(true){
            Distances distances = car.processLines(lines);
            double angle = this.controller.getAngle(distances);
            System.out.println(angle);
            try{
                Line line = new Line(this.car.center, this.car.center.sum(this.car.front.sub(this.car.center).unit().mul(distances.front)));
                Line line1 = new Line(this.car.center, this.car.center.sum(this.car.leftFront().sub(this.car.center).unit().mul(distances.left)));
                Line line2 = new Line(this.car.center, this.car.center.sum(this.car.rightFront().sub(this.car.center).unit().mul(distances.right)));
                ArrayList<Line> ls = new ArrayList<Line>();
                ls.add(line);
                ls.add(line1);
                ls.add(line2);
                this.visualization.lines = ls;
                this.visualization.repaint(5);
                
                this.car.rotate(-angle * 3.14 / 180);
                this.car.move();
            }catch(Exception e){
                //pass
            }
            // try
            // {
            //     BufferedImage image = new BufferedImage(1121, 822, BufferedImage.TYPE_INT_RGB);
            //     Graphics2D graphics2D = image.createGraphics();
            //     visualization.paint(graphics2D);
            //     ImageIO.write(image,"jpeg", new File("screen.jpeg"));
            // }
            // catch(Exception exception)
            // {
            //     //code
            // }
        }

    }
}