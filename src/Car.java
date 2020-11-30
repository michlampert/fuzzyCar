package src;

import java.util.ArrayList;

public class Car {

    public static double velocity = 1;
    public static double sensorAngle = 1.0/6.0;
    public static double veryFar = 1000;
    public static double carSize = 30;
    public Vector2d center;
    public Vector2d front;

    public Car(Vector2d center, Vector2d front){
        this.center = center;
        this.front = center.sum(front.sub(center).unit().mul(carSize));
    }

    public Car(Vector2d center){
        this(center, center.sum(new Vector2d(0, 10)));
    }

    public Car(){
        this(new Vector2d(0, 0));
    }

    public void move(){
        Vector2d v = this.front.sub(this.center).unit().mul(Car.velocity);
        this.front = this.front.sum(v);
        this.center = this.center.sum(v);
    }

    public void rotate(double alpha){
        Vector2d v = this.front.sub(this.center);
        this.front = this.center.sum(v.rotate(alpha));
    }

    public Vector2d leftFront(){
        Vector2d v = this.front.sub(this.center);
        return this.center.sum(v.rotate(-3.14 * Car.sensorAngle));
    }

    public Vector2d rightFront(){
        Vector2d v = this.front.sub(this.center);
        return this.center.sum(v.rotate(3.14 * Car.sensorAngle));
    }

    public double calculateDistance(Line ray, Line line){
        Vector2d intersection = ray.intersection(line);
        if(intersection == null){
            return Car.veryFar;
        }
        else{
            return intersection.sub(this.center).len();
        }
    }

    public Distances processLines(ArrayList<Line> lines){
        Distances distances = new Distances(Car.veryFar, Car.veryFar, Car.veryFar);
        
        
        try{   
            Line leftLine;
            Line frontLine;
            Line rightLine;

            leftLine = new Line(this.center, this.leftFront(), true);
            frontLine = new Line(this.center, this.front, true);
            rightLine = new Line(this.center, this.rightFront(), true);
            
            for(Line line: lines){
                distances.minUpdate(new Distances(this.calculateDistance(leftLine, line), this.calculateDistance(frontLine, line), this.calculateDistance(rightLine, line)));
            }
        } catch(Exception e){
            System.out.println(e);
        }


        return distances;
    }

    public String toString(){
        return "[ " + this.center.toString() + " -> " + this.front.toString() + " ]";
    }
}