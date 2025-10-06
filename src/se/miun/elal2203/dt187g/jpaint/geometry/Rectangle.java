package se.miun.elal2203.dt187g.jpaint.geometry;


import java.awt.Color;

/**
    * Rectangle is a subclass of Shape that overrides all methods from Shape.
    * Calls the subclass ctor with indata (Point p, String color and then calls its "sister" ctor that takes (double x, double y, String color) 
    * Reuse methods from subclass but with different logic depending of the shape 
    *
    * @author elal2203
    * @version 1.0 
    */

import java.awt.Graphics;
// import java.util.ArrayList;

public class Rectangle extends Shape { //extends- ärva

    public Rectangle(Point p, String color){
        super(p, color);

        // här anropar jag konstruktor som finns i superklassens ctor Shape
        // när jag skapar en Rectangle MÅSTE först Shape bli korrekt initierad
        // därför kallar Rectangle på super(...) för att ge Shape de värden den behöver
    }

    public Rectangle(double x, double y, String color){
        //systerkonstruktor till ctor1 där den återanvänder koden Rectangle(Point p, String color)
        this( new Point (x,y), color);
    }

    // genom att använda this() och sedan anropa super() slipper jag duplicera kod


    // public Shape( Point p, String colorString){
    //     points = new ArrayList<Point>();
    //     points.add(p);

    //     color = colorString;
    // }

    public double getWidth(){
        
        if (!hasEndpoint()) {
            return 0.0;
        }
    
        double startX = points.get(0).getX();
       
        // double endX = points[1].getX();
        double endX = points.get(1).getX();
        points.get(1);

     
        double width = Math.abs(startX - endX);
        return width;
    }
   
    public double getHeight(){
        
        if (!hasEndpoint()) {
            return 0;
        }
        double startY = points.get(0).getY();
        double endY = points.get(1).getY();

        double height = Math.abs( startY - endY);
        return height;
    }

   
    @Override
    public void draw() {
        System.out.println(toString());
    }

    @Override
    public void draw(Graphics g) {

        int x = (int) points.get(0).getX();
        int y = (int) points.get(0).getY();

        String color = this.getColor();
        Color c = Color.decode(color);
        g.setColor(c);
        
        int width = (int) getWidth();
        int height = (int) getHeight();

        g.fillRect(x, y, width, height);
    }

   //implementation to count circumference
    @Override
    public double getCircumference() {

        if (!hasEndpoint()) {
            return 0;
            // rectangle HAS TO have endpoint to count circumference
        }
        double height = getHeight();
        double width = getWidth();

        double circumference = 2 * (width + height) ;

        return circumference;
    }

   //implementation to count area
    @Override
    public double getArea() {
        if (!hasEndpoint()) {
            return 0;
        }
        double height = getHeight();
        double width = getWidth();

      double area = height * width;
      return area;
    }


    @Override
    public void addPoint(Point p) {
        points.add(p) ;
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

    // @Override
    // public boolean hasEndpoint() {
        
    //     if (points.get(1) == null) {
    //         return false;
    //     }
    //     return true;
    // }

    @Override
    public String toString(){

        boolean hasEndPoint = hasEndpoint();
        

        String startPoint = points.getFirst().toString();
        String endPoint = hasEndPoint ? points.getLast().toString() : "N/A"; 

        String width = hasEndPoint ? Double.toString(getWidth()) : "N/A"; 
        String height = hasEndPoint ? Double.toString(getHeight()) : "N/A";
        String colour = getColor();

        return "Rectangle" + "[start="+ startPoint  + " end=" + endPoint + " width=" + width + " height=" + height + " color=" + colour + "]"; 
    

    }
}
