package se.miun.elal2203.dt187g.jpaint;



import javax.swing.SwingUtilities;

import se.miun.elal2203.dt187g.jpaint.gui.JPaintFrame;
import se.miun.elal2203.dt187g.jpaint.geometry.Circle;
import se.miun.elal2203.dt187g.jpaint.geometry.Rectangle;
import se.miun.elal2203.dt187g.jpaint.gui.FileHandler;


public class AppStart {

	public static void main(String[] args) {
		
		
		// Make sure GUI is created on the event dispatching thread
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new JPaintFrame().setVisible(true);
				Drawing drawing = new Drawing();
				FileHandler fileHandler = new FileHandler();

				Rectangle r1 = new Rectangle(233,177,425, 362,"#0000ff");
				Rectangle r2 = new Rectangle(744,151,861,259,"#0000ff");
				// r1.addPoint(425, 362);
				drawing.addShape(r1);
				// r1.addPoint(425, 362);
				drawing.addShape(r2);

				Circle c = new Circle(744,151,861, 259,"#0000ff");
				//c.addPoint(861, 259);
				drawing.addShape(c);
				fileHandler.save(drawing,"hej");
				
				//fileHandler.load("hej");

			}
		});
	}
}
