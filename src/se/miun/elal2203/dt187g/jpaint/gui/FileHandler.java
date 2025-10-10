package se.miun.elal2203.dt187g.jpaint.gui;

import se.miun.elal2203.dt187g.jpaint.Drawing;
import se.miun.elal2203.dt187g.jpaint.geometry.Circle;
import se.miun.elal2203.dt187g.jpaint.geometry.Point;
import se.miun.elal2203.dt187g.jpaint.geometry.Rectangle;
import se.miun.elal2203.dt187g.jpaint.geometry.Shape;

import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;

import java.io.*;

public class FileHandler {

    public FileHandler() {
        // FileReader in = FileReader("filereader");
        // FileWriter out = FileWriter("filwriter");
    }
    
     JFileChooser chooser = new JFileChooser();
    // int option = chooser.showOpenDialog(this);

    public static void save(Drawing drawing, String fileName){
        
        try {
            
            if (!fileName.endsWith(".shape")) {
                fileName +=".shape";
            }
            
            File file = new File(fileName);
            
            // för att skriva text(tecken) till en fil. tecken för tecken eller rad för rad
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fileWriter);

            String name = drawing.getName().trim();
            String author = drawing.getAuthor().trim();

            if (author.isEmpty()) {
                author = "[not specified]";
            }
            if (name.isEmpty()) {
                name = "[not specified]";
            }
            String form ="";
            
            bw.write(name);
            bw.newLine();
            bw.write(author);
            for (Shape s : drawing.getShapes()) {

                List<Point> points = s.getPoints();
                String pointString = "";
                for (Point point : points) {

                    int x = (int) point.getX();
                    int y = (int) point.getY();
                    pointString +=  x + ","+ y + ",";
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
                // TODO: handle exception
                System.err.println("Save failed:" + e.toString());
            }
                // String row =  br.readLine();
                // System.out.println("row:" + row);

                // while (row != null) {
                // System.out.println(row);
                // row = br.readLine();


            // PrintWriter pw = new PrintWriter(bw);
            // pw.println("test");
            // pw.println("aaaa");
            // pw.close();
            
            // ArrayList<String> listStrings = new ArrayList();
            // listStrings.add(drawing.getAuthor());
            // listStrings.add(drawing.getName());
            // for (String string : listStrings) {
                // bw.newLine();
                
                // bw.write(drawing.getName());
                // bw.newLine();
                
            // }
            // var path =  java.nio.file.Files.write(Path.of(fileName), listStrings, StandardCharsets.UTF_8);
            //System.out.println("strings: " + " " + listStrings);
            
    }

    public static Drawing load(String fileName){
        // hitta drawing som har samma namn som filname
        Drawing drawing = new Drawing();
        
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fileReader); //läser från filen som läser hela blocket av data istället för en char eller byte i taget
            String row = br.readLine();

            while (row!=null) {
                System.out.println(row);
                row = br.readLine();
            }
           
            // List<Shape> drawingList = drawingList.
            
        } catch (Exception e) {
            // TODO: handle exception
        }
        return drawing;
    }

    
}
