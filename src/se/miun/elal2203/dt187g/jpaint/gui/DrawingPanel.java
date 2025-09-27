package se.miun.elal2203.dt187g.jpaint.gui;

import java.awt.Color;

import javax.swing.JPanel;

import se.miun.elal2203.dt187g.jpaint.Drawing;

/**
	 * This class with 2 constructor handles the background, either by setting it as the given argument (Color background) in the second constructor
	   or setting it white if no parameter is required (first ctor)
	 * This class is the paint space for the user
	 
	   @author elal2203
       @version 1.0 
	 */



public class DrawingPanel extends JPanel {
	
	//exception
	private Drawing drawing;

	
	public DrawingPanel() {
		setBackground(Color.white);
	}
	
	public DrawingPanel(Color background) {
		setBackground(background);
	}

	
	public Drawing getDrawing()throws DrawingException{
		
		if (drawing == null ) {
			throw new DrawingException("getDrawing(): Could not get drawing"); 
		} 
		return drawing;
	}


	public void setDrawing(Drawing drawing) throws DrawingException{

		if (drawing == null) {
			throw new DrawingException("Could not set Drawing"); 
		}
		this.drawing = drawing;
	}
}
