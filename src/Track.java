package src;

import java.util.ArrayList;

public class Track {
    public Car car;
    public ArrayList<Line> lines;
    public Visualization visualization;

    public Track(){
        this.car = new Car(new Vector2d(400,400));
        this.lines = Parser.parse("lines.txt");

        this.visualization = new Visualization(this.car);

        Distances distances = car.processLines(lines);
        System.out.println(distances);
        try{
            Line line = new Line(this.car.center, this.car.center.sum(this.car.front.sub(this.car.center).unit().mul(distances.front)));
            Line line1 = new Line(this.car.center, this.car.center.sum(this.car.leftFront().sub(this.car.center).unit().mul(distances.left)));
            Line line2 = new Line(this.car.center, this.car.center.sum(this.car.rightFront().sub(this.car.center).unit().mul(distances.right)));
            ArrayList<Line> ls = new ArrayList<Line>();
            ls.add(line);
            ls.add(line1);
            ls.add(line2);
            this.visualization.lines = ls;
            this.visualization.repaint();
        }catch(Exception e){
            //pass
        }

    }
}