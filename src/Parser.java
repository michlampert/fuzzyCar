package src;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Parser {
    public static ArrayList<Line> parse(String filename){
        ArrayList<Line> lines = new ArrayList<Line>();

        try {
            File file = new File(filename);
            Scanner reader = new Scanner(file);
            int i = Integer.parseInt(reader.nextLine());
            for(int j = 0; j<i; j++){
                double x1 = Double.parseDouble(reader.nextLine());
                double y1 = Double.parseDouble(reader.nextLine());
                double x2 = Double.parseDouble(reader.nextLine());
                double y2 = Double.parseDouble(reader.nextLine());

                Vector2d p1 = new Vector2d(x1,y1);
                Vector2d p2 = new Vector2d(x2,y2);
                lines.add(new Line(p1,p2));
            }

            reader.close();
        } catch (Exception e) {
            System.out.println("An error occurred.");
        }    

        return lines;
    }    
}
