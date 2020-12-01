
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Visualization extends JFrame {

    public Car car;
    public Image background;
    public ArrayList<Line> lines;

    public Visualization(Car car, ArrayList<Line> lines) {
        super("FuzzyCar");
        this.car = car;
        this.lines = lines;
        this.setBounds(0, 0, 1121, 822);
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        try {
            background = ImageIO.read(new File("track1.png"));
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public Visualization() {
        this(new Car(new Vector2d(200,200)), new ArrayList<Line>());
    }

    public Visualization(Car car){
        this(car, new ArrayList<Line>());
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(background, 0, 30, this);

        g.setColor(Color.BLUE);
        for(Line line: this.lines){
            g.drawLine((int) line.p1.x , (int) line.p1.y + 30, (int) line.p2.x, (int) line.p2.y + 30);
            g.fillOval((int) line.p2.x - 5, (int) line.p2.y + 30 - 5,10,10);
        }
        
        Vector2d lf = this.car.leftFront();
        Vector2d rf = this.car.rightFront();
        Vector2d lb = this.car.center.mul(2).sub(rf);
        Vector2d rb = this.car.center.mul(2).sub(lf);
        g.setColor(Color.RED);
        Polygon p = new Polygon();
        p.addPoint((int) lf.x, (int) lf.y + 30);
        p.addPoint((int) rf.x, (int) rf.y + 30);
        p.addPoint((int) rb.x, (int) rb.y + 30);
        p.addPoint((int) lb.x, (int) lb.y + 30);
        g.fillPolygon(p);


    }

    public static void main(String[] args) {
        Visualization v = new Visualization();
        v.repaint();
    }
}