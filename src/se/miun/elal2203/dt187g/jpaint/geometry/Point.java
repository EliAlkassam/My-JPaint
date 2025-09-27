package se.miun.elal2203.dt187g.jpaint.geometry;
/**
* A class that represents a dot in a coordinatesystem(x,y)
* empty ctor that sets (0,0) for the dots 
* @author elal2203
* @version 1.0 
*/



public class Point{

    protected double x, y;

    
    public Point() {
        this.x = 0;
        this.y = 0;
    }


    public Point (double x, double y){
        setX(x);
        setY(y);
    }

    public void setX(double x){ 
        this.x = x;
    }

    public void setY(double y){ 
        this.y = y;
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    @Override
    public String toString(){
       // Point p = new Point(23.2, 19.2);
        return "["+ x +", " + y + "]";
        //System.out.println(p);

    }

    








}