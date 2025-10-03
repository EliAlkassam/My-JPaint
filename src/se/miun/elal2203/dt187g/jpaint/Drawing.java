package se.miun.elal2203.dt187g.jpaint;

import se.miun.elal2203.dt187g.jpaint.geometry.Shape;
import se.miun.elal2203.dt187g.jpaint.gui.DrawingException;

import java.awt.Graphics;
import java.util.List;
import java.util.ArrayList;


/**
    * Drawing implements from the interface Drawable.
    * Calls the subclass ctor with indata (Point p, String color and then calls its "sister" ctor that takes (double x, double y, String color) 
    * Reuse methods from subclass but with different logic depending of the shape 
    * Gets total area and circumference from every object Shape in list shapes  
    * @author elal2203
    * @version 1.0 
    */

public class Drawing implements Drawable {

    private String name;
    private String author;
    private List<Shape> shapes;

    public Drawing() {
        this.shapes = new ArrayList<>();
        name="";
        author ="";

    }
    
    public Drawing(String name, String author) throws DrawingException
    {
        this(); // calls the standard ctor - no need for this.shaped = new ArrayList<>();
        if (name == null || name.isEmpty() || author == null || author.isEmpty()) {
            throw new DrawingException("name and author can't be null or empty");
        }
        else{
            this.author = author;
            this.name = name;
        }
    }

    public void setName(String name) throws DrawingException{
        if (name == null) {
            throw new DrawingException("name can't be null");
        }
        else if (name.isEmpty()){ 
            throw new DrawingException("name can't be empty");
        }
        this.name = name;
    }

    public void setAuthor(String author) throws DrawingException{
        if (author == null ) {
            throw new DrawingException("setAuthor(): Author can't be null ");
        }
        else if (author.isEmpty()) {
            throw new DrawingException("setAuthor(): Author can't empty");
        }
        this.author = author;
    }

    public String getName(){
        return name;
    }

    public String getAuthor(){
        return author;
    }

    public void addShape ( Shape shape){
        if (shape !=null) {
            shapes.add(shape);
        }
    }

    public void removeShape(int index){
        
        shapes.remove(index);
    }

    public int getSize(){
        return shapes.size(); 
    }

    public double getTotalCircumference(){
        double totalCircumference = 0;
        for (Shape shape : shapes) {
            
            double shapeCircumference = shape.getCircumference();

            if (shapeCircumference != 0) {
                totalCircumference += shapeCircumference;
            }            
        }
        return totalCircumference;
    }

    public double getTotalArea(){
        double totalArea = 0;
        for (Shape shape : shapes) {
            
            double shapeArea = shape.getArea();

            if (shapeArea != 0) {
                totalArea += shapeArea;
            }
        }
        return totalArea;
    }


    @Override
    public void draw() {
        System.out.println(name + author + toString());
    }

    @Override
    public void draw(Graphics g) {
        for (Shape shape : shapes) {
            shape.draw(g);
        }
    }

    public String toString(){
        String name = getName();
        String author = getAuthor();
        
        String size = Integer.toString(getSize());
        
        String totalCircumferece = Double.toString(getTotalCircumference()) ;
        String totalArea = Double.toString(getTotalArea());

        return "Drawing[" + "name="+ name +";" + "author=" + author +";" + "size=" + size +";" + "circumference=" + totalCircumferece +";" + "area=" + totalArea + ";";
    }





}
