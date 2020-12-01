package src;

import java.util.ArrayList;
import java.util.concurrent.*;

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
        // this.visualization.lines.addAll(this.lines);

        while(true){
            
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
            // this.visualization.repaint((int) this.car.center.x - 50, (int) this.car.center.y - 50, 100, 100);


            
            TimeUnit.MILLISECONDS.sleep(50);
            
            this.car.rotate(-angle  * 3.14 / 180);
            this.car.move();
            
        }

    }

    public Track() throws Exception{
        this("track1");
    }
}