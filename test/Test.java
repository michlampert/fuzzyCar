package test;

import src.*;

public class Test {
    public static void main(String[] args) throws Exception {
        
        Vector2d v1 = new Vector2d();
        Vector2d v2 = new Vector2d(0,1);
        Vector2d v3 = new Vector2d(1,1);
        Vector2d v4 = new Vector2d(1,0);
        Vector2d vinf = new Vector2d(1000,1000);

        System.out.println(v1.sum(v3));

        System.out.println(v1.sub(v3));

        System.out.println(v2.mul(10));

        System.out.println(v1.div(10));

        System.out.println(v3.neg());

        System.out.println(v3.len());

        System.out.println(v3.unit());

        System.out.println(v3.scalar(v2));

        System.out.println(v2);
        System.out.println(v2.transverse());
        System.out.println(v2.transverse().transverse());
        System.out.println(v2.transverse().transverse().transverse());
        System.out.println(v2.transverse().transverse().transverse().transverse());

        System.out.println(v2);
        System.out.println(v2.rotate(3.14/6));
        System.out.println(v2.rotate(3.14/6).rotate(3.14/6));
        System.out.println(v2.rotate(3.14/6).rotate(3.14/6).rotate(3.14/6));


        System.out.println("-----------");

        Car car = new Car(new Vector2d(200,200));
        System.out.println(car);        

        System.out.println(car.leftFront());
        System.out.println(car.rightFront());

        car.move();
        System.out.println(car);       
        
        car.rotate(3.14 / 6);
        System.out.println(car);

        System.out.println("-----------");

        Line line1 = new Line(v2.sum(vinf), v2.neg().sum(vinf));
        Line line2 = new Line(v3.sum(vinf), v3.neg().sum(vinf));

        System.out.println(line1.intersection(line2));

    }
}
