package se.miun.elal2203.dt187g.jpaint.geometry;
// import java.awt.List;
import java.util.ArrayList;

import se.miun.elal2203.dt187g.jpaint.Drawable;   // <-- viktigt


public abstract class Shape implements Drawable {

    /**
    * abstract superclass that implemets the interface Drawable
    * "superclass" for ex rectangle and circle that have different shapes
    * An interface for different shapes that dont look alike
    * Every superclass must implement all the methods that excists here 
    * Tells what all the geometric figures has in common( x and y dots, area, circumference, and color)

         - * En abstract superklass som representerar en geometrisk
            * figur, iaf grunderna för. Innehåller information om
            * figurens x- och y- koordinat. Denn abstrakta metoden
            * beraknaArea används för att beräkna arean av en viss
            * figur. Då vi här inte kan beräkna arean av alla figurer
            * men vill att alla subklasser ska ha metoden, deklarerar
            * vi den som abstrakt.


    *
    * @author elal2203
    * @version 1.0 
    */

    protected String color;
    protected ArrayList<Point> points;

    // protected ArrayList<Point> points = new ArrayList<Point>();


    // ctor garanterar att alla subklasser har Point och color
    public Shape( Point p, String colorString){
        points = new ArrayList<Point>();
        points.add(p);

        color = colorString;
    }

    public void setColor (String colour){
       this.color = colour;
    }
    public String getColor(){
        return color;
    }

    public abstract double getCircumference();

    public abstract double getArea();

    public void addPoint(Point p){
        points.add(p);
    }
    
    public void addPoint(double x, double y){
        
        Point point = new Point(x,y);
        this.addPoint(point);
        // this( new Point(point));    
    }
    
    public abstract boolean hasEndpoint();
    

}
