package se.miun.elal2203.dt187g.jpaint.geometry;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;


/**
    * Circle is a subclass of Shape that overrides all methods from Shape.
    * Calls the subclass ctor with indata (Point p, String color) and then calls its "sister" ctor that takes (double x, double y, String color) 
    * Reuse methods from subclass but with different logic depending of the shape structure
    * Has the method "getRadius()" because of the shape structure
    
    *
    * @author elal2203
    * @version 1.0 
    */

public class Circle extends Shape {

    protected final double pi =  3.14159265;
    
    // ctor- same principle as Rectangle
    public Circle(Point p, String color) {
        super(p, color);
    }
    public Circle(double x, double y, String color) {
        this(new Point (x,y), color);
    }

    public double getRadius(){
        if (!hasEndpoint()) {
            return 0;
        }

        double x = ((Point)this.points.get(1)).getX() - ((Point)this.points.get(0)).getX();
        double y = ((Point)this.points.get(1)).getY() - ((Point)this.points.get(0)).getY();

        return Math.sqrt(x * x  + y * y);
    }

    @Override
    public void draw() {
        System.out.println(toString());
    }

    @Override
    public void draw(Graphics g) {

        int x = (int) points.get(0).getX() - (int) getRadius() ;
        int y = (int) points.get(0).getY() - (int) getRadius();
        int diameter = (int) getRadius() * 2;
    
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        String color = this.getColor();
        Color c = Color.decode(color);
        
        g2.setColor(c);
        g2.fillOval(x, y, diameter, diameter) ;
    }

    @Override
    public double getCircumference() {
         if (!hasEndpoint()) {
            return 0;
        }
        double radius = getRadius();
        double circumference = (2 * pi * radius) ;
        return circumference;
    }

    @Override
    public double getArea() {
        if (!hasEndpoint()) {
            return 0;
        }
        double radius= getRadius();

        double area = radius*radius * pi;
        return area;
    }

    @Override
    public void addPoint(Point p) {
         points.add(p);
    }

    @Override
    public void addPoint(double x, double y) {
        this.addPoint( new Point(x,y));
    }

    @Override
    public boolean hasEndpoint() {
        if (points.size() > 1 ) {
            return true;
        }
        return false;
    }

    public String toString(){

        boolean hasEndPoint = hasEndpoint();
        
        String startpoint = points.get(0).toString();
        String endpoint = hasEndPoint ? points.get(1).toString() : "N/A";

        String radius = hasEndPoint ? Double.toString(getRadius()) : "N/A";
        String color = getColor();

        return "Circle" + "[ start=" + startpoint + " end=" + endpoint + " radius=" + radius + " color=" + color + "]";

    }

}
