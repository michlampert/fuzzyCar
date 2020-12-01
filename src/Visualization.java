package src;

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
    public int margin = 0;

    public Visualization(String trackName, Car car, ArrayList<Line> lines) {
        super("FuzzyCar");
        this.car = car;
        this.lines = lines;
        this.setBounds(0, 0, 1123, 794 + margin);
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        try {
            background = ImageIO.read(new File("tracks/"+trackName+".png"));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public Visualization() {
        this("track1", new Car(new Vector2d(400,400)), new ArrayList<Line>());
    }

    public Visualization(String trackName, Car car){
        this(trackName, car, new ArrayList<Line>());
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(background, 0, margin, this);
        
        // g.setColor(Color.WHITE);
        // g.fillRect(0, 0, 1121, 822);

        g.setColor(Color.BLUE);
        for(Line line: this.lines){
            g.drawLine((int) line.p1.x , (int) line.p1.y + margin, (int) line.p2.x, (int) line.p2.y + margin);
            g.fillOval((int) line.p2.x - 3, (int) line.p2.y + margin - 3,6,6);
        }
        
        Vector2d lf = this.car.leftFront();
        Vector2d rf = this.car.rightFront();
        Vector2d lb = this.car.center.mul(2).sub(rf);
        Vector2d rb = this.car.center.mul(2).sub(lf);

        Polygon p = new Polygon();
        p.addPoint((int) lf.x, (int) lf.y + margin);
        p.addPoint((int) rf.x, (int) rf.y + margin);
        p.addPoint((int) rb.x, (int) rb.y + margin);
        p.addPoint((int) lb.x, (int) lb.y + margin);

        g.setColor(Color.RED);
        g.fillPolygon(p);
    }

    public static void main(String[] args) {
        Visualization v = new Visualization();
        v.repaint();

        try{
            BufferedImage image = new BufferedImage(1123, 794, BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics2D = image.createGraphics();
            v.paint(graphics2D);
            ImageIO.write(image,"png", new File("screen.png"));
        }catch(Exception exception)
        {
            System.out.println(exception);
        }
    }
}