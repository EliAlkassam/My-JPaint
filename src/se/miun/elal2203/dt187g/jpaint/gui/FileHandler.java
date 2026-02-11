package se.miun.elal2203.dt187g.jpaint.gui;

import se.miun.elal2203.dt187g.jpaint.Drawing;
import se.miun.elal2203.dt187g.jpaint.geometry.Circle;
import se.miun.elal2203.dt187g.jpaint.geometry.Point;
import se.miun.elal2203.dt187g.jpaint.geometry.Rectangle;
import se.miun.elal2203.dt187g.jpaint.geometry.Shape;

import java.nio.file.*;

import java.util.Arrays;
import java.util.List;

import javax.swing.JFileChooser;

import java.io.*;

public class FileHandler {

    public FileHandler() {

    }

    JFileChooser chooser = new JFileChooser();

    public static void save(Drawing drawing, String fileName) {

        try {
            if (!fileName.endsWith(".shape")) {
                fileName += ".shape";
            }

            File file = new File(fileName);
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fileWriter);

            String name = drawing.getName().trim();
            String author = drawing.getAuthor().trim();
            String form = "";

            bw.write(checkString(name));
            bw.newLine();
            bw.write(checkString(author));
            for (Shape s : drawing.getShapes()) {

                List<Point> points = s.getPoints();
                String pointString = "";
                for (Point point : points) {

                    int x = (int) point.getX();
                    int y = (int) point.getY();
                    pointString += x + "," + y + ",";
                }

                if (s instanceof Circle) {
                    form = "Circle";
                }
                if (s instanceof Rectangle) {
                    form = "Rectangle";
                }
                form += "," + pointString + s.getColor();
                bw.newLine();
                bw.write(form);
            }
            bw.close();
        } catch (IOException e) {
            System.err.println("Save failed:" + e.toString());
            e.printStackTrace();
        }
    }

    public static Drawing load(String fileName) throws FileNotFoundException, IOException {
        Drawing drawing = new Drawing();
        try {

            if (!fileName.endsWith(".shape")) {
                fileName += ".shape";
            }
            Path path = Path.of(fileName);
            List<String> data = java.nio.file.Files.readAllLines(path);

            String name = data.get(0);
            String author = data.get(1);

            try {
                drawing.setName(name);
                drawing.setAuthor(author);

            } catch (DrawingException e) {
                e.getMessage();
                e.printStackTrace();

            }

            for (int i = 2; i < data.size(); i++) {
                String info = data.get(i);

                String splitetArray[] = info.split(",");

                String shapeAsString = splitetArray[0];
                String color = splitetArray[splitetArray.length - 1];
                int x1Parse;
                int y1Parse;
                int x2Parse;
                int y2Parse;

                var points = Arrays.copyOfRange(splitetArray, 1, splitetArray.length - 1); // 233,177,425,362
                for (int j = 1; j < points.length; j++) {

                    String x = points[j - 1];
                    String y = points[j];
                    String x2 = points[j + 1];
                    String y2 = points[j + 2];

                    y1Parse = Integer.parseInt(y);
                    x1Parse = Integer.parseInt(x);
                    y2Parse = Integer.parseInt(y2);
                    x2Parse = Integer.parseInt(x2);

                    if (shapeAsString.equals("Circle")) {
                        Circle c = new Circle(x1Parse, y1Parse, x2Parse, y2Parse, color);
                        drawing.addShape(c);
                        break;
                    } else if (shapeAsString.equals("Rectangle")) {
                        Rectangle r = new Rectangle(x1Parse, y1Parse, x2Parse, y2Parse, color);
                        drawing.addShape(r);
                        break;
                    }
                }
            }
            return drawing;

        } catch (FileNotFoundException e) {
            System.err.println("Load failed:" + e.toString());
            e.printStackTrace();
            return null;
        }
    }

    private static String checkString(String string) {
        return (string == null || string.isBlank() ? "[not specified]" : string);
    }

}
