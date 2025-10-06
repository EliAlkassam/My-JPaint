package se.miun.elal2203.dt187g.jpaint.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.nio.ReadOnlyBufferException;
import java.util.ArrayList;
import java.util.function.Consumer;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.Border;

/**
	 * This class handles all componements (statusbar,combobox, and topPanel) for the window and presents them
	 * How the x and y values changes depending on how the mouse is moved
	 * Adds colors to ArrayList<ColorPanel> panelList
	 
	 * @author elal2203
     * @version 1.0 
	 * */

public class JPaintFrame extends JFrame {

	public static final String APP_NAME= "JPaint"; 
	
	//exception
	private String drawingTitle = "";

	private String header;
	private Container c = this.getContentPane();
	private StatusBarPanel statusBarPanel;
	private DrawingPanel drawingPanel;
	private ColorPalettePanel colorPalettePanel;

	public JPaintFrame() {
		init();
	}
	
	@Override
	public void setMinimumSize(Dimension minimumSize) {
		// TODO Auto-generated method stub
		super.setMinimumSize(minimumSize);
	}
	private void init() {

		colorPalettePanel = new ColorPalettePanel();
		
		// 1. Sätt storleken på JFrame till vad ni nu känner för.
		setSize(800, 550);

		// 2. Se till att programmet stängs ner när man trycker på krysset upp i högra
		// hörnet.
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		/*
		 * 3. Välj ikon för programmet. Ni kan skapa en mapp som heter "img" i
		 * root-katalogen och hänvisa till den genom "img/filenameOfYourIcon.png".
		 */   
		ImageIcon myIcon = new ImageIcon("img/myicon.png");
		setIconImage(myIcon.getImage());

		/*
		 * 4. Initialisera strängen "header" med något värde ("JPaint" exempelvis), och
		 * sätt detta som title för programmet. Att vi lagrar vårat applikationsnamn i
		 * en String kommer bli tydligare till kommande uppgifter.
		 */
		setTitle(APP_NAME);

		/*
		 * 5. Sätt layout för denna klass till BorderLayout
		 */
		setLayout( new BorderLayout());
		
		/*
		 * 6. Följande kod skapar en JPanel där vi sätter en önskad storlek på höjden
		 * genom att skicka ett Dimension-objekt till prefferedSize (Dimension(width,
		 * height)). Att vi anger width till 0 är mest för att vi inte kommer kunna
		 * påverka detta ändå (den kommer bli så bred som applikationen är bred). Det är
		 * detta JPanel-objekt som kommer inhysa våran ColorPalettePanel samt våran
		 * JComboBox (den som visar vilken typ av form vi ritar).
		 */
		JPanel topPanel = new JPanel();
		topPanel.setPreferredSize(new Dimension(0, 50));

		/*
		 * 7. Initialisera ColorPalettePanel. Om du väljer att initialisera
		 * ColorPalettePanel via "default"-constructorn (den utan argument), då måste du
		 * anropa addColorPanel för varje ColorPanel-objekt du vill lägga till.
		 * 
		 * Alternativ så anropar du ColorPalettePanel(ArrayList<ColorPanel>) och då
		 * sköter ColorPalettePanel resten
		 */
		ArrayList<ColorPanel> panelList = new ArrayList<>();
		panelList.add(new ColorPanel(Color.blue));
		panelList.add(new ColorPanel(Color.red));
		panelList.add(new ColorPanel(Color.yellow));
		panelList.add(new ColorPanel(Color.green));
		panelList.add(new ColorPanel(Color.black));

		colorPalettePanel = new ColorPalettePanel(panelList);

		/*
		 * 8.
		 * 8.1 Skapa en String[] som håller "Rectangle" och "Circle" 
		 * 8.2 Skapa en JComboBox<String> och initalisera den med arrayen. 
		 * 8.3 Välj vilken form som ska vara default.
		 * 
		 * Våran JComboBox kommer vara bunden till den höjd som anges av topPanel.
		 * Däremot så har vi här möjlighet att ange bredd. Sätt bredden till något
		 * rimligt, exempelvis 100.
		 */
		String[] shapes = {"Rectangle", "Circle"};
		JComboBox<String> comboBox = new JComboBox<>(shapes);

		comboBox.setSelectedItem(shapes[0]);
		comboBox.setPreferredSize(new Dimension(100,0));

		comboBox.addActionListener( new ActionListener () {
			@Override
			public void actionPerformed(ActionEvent e){
				JComboBox<String> cb = (JComboBox<String>) e.getSource();
				String selectedShape = (String) cb.getSelectedItem();
				System.out.println("den valda är " + selectedShape);

				if (selectedShape == "Rectangle") {
					drawingPanel.setActiveShape(selectedShape);
				}
				if (selectedShape == "Circle") {
					drawingPanel.setActiveShape(selectedShape);
				}
			}
		});

		

		/*
		 * 9.
		 * 9.1 Initialisera DrawingPanel
		 * 9.2 Deklarera en CustomMouseAdapter och initialisera den.
		 * 9.3 Lägg till denna CustomMouseAdapter som MouseListener till drawingPanel
		 * 9.4 Lägg även till CustomMouseAdapter som MouseMotionListener till drawingPanel
		 */
		drawingPanel = new DrawingPanel();
		CustomMouseAdapter customMouseAdapter = new CustomMouseAdapter();
		drawingPanel.addMouseListener(customMouseAdapter);
		drawingPanel.addMouseMotionListener(customMouseAdapter);

		/*
		 * 10.
		 * 10.1 Initialisera StatusBarPanel
		 * 10.2 Sätt en rimlig höjd på StatusBarPanel, exempelvis 25.
		 */
		// TODO
		statusBarPanel = new StatusBarPanel();
		statusBarPanel.setPreferredSize(new Dimension( 0,25));
		
		/*
		 * 11. Nu när StatusBarPanel väl är initialiserad så kan vi
		 * sätta en MouseListener för våra ColorPanel's. Eftersom vi inte har gått igenom
		 * anonyma klasser än, och eftersom det enkaste sättet att uträtta detta är genom en
		 * anonym klass, så följer den med här. 
		 * Ni måste fortfarande implementera mousePressed dock.
		 * Det vi vill ska hända är att när ett objekt klickas på, så ska dess bakgrundsfärg skickas
		 * som argument till StatusBarPanel.updateSelectedColor(Color color).
		 * Vi kommer behöva anropa MouseEvent.getSource() (i ren syntax innebär det alltså "e.getSource()".
		 * MouseEvent.getSource() returnerar ett Object. Vi kan inte få reda på bakgrundsfärgen bara genom 
		 * ett Object. Så vi måste "casta" det Object som returneras från getSource() till en ColorPanel.
		 * */
		colorPalettePanel.setMouseListenerForColorPanels(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				ColorPanel colorPanel = (ColorPanel) e.getSource();
				drawingPanel.setdrawColor(colorPanel.getColor());
				statusBarPanel.updateSelectedColor(colorPanel.getBackground());
			}
		 });
		 

		/*
		 * 12.
		 * 12.1 Sätt layouten för topPanel till BorderLayout.
		 * 12.2 "adda" colorPalettePanel med lämplig constraint (dvs BorderLayout.LÄMPLIG_CONSTRAINT)
		 * 12.3 "adda" din JComboBox med lämplig constraint (dvs BorderLayout.LÄMPLIG_CONSTRAINT)
		 */
		topPanel.setLayout(new BorderLayout());
		topPanel.add(colorPalettePanel, BorderLayout.CENTER);
		topPanel.add(comboBox, BorderLayout.EAST);
		
		/*
		 * 13. Avslutningsvis, lägg till topPanel, drawingPanel och statusBarPanel till Container c.
		 */
		// TODO
		c.add(topPanel, BorderLayout.NORTH);
		c.add(drawingPanel, BorderLayout.CENTER);
		c.add(statusBarPanel, BorderLayout.SOUTH);

		MenuManager menuManager = new MenuManager(this, drawingPanel);
        setJMenuBar(menuManager.getMenu());


		
		statusBarPanel.setOnChangeListener(new OnChangeListener <StatusBarPanel>(){
			@Override
			public void onChange(StatusBarPanel object){
				drawingPanel.setdrawColor(object.getSelectedColor());
			}
		});
		
		// OnChangeListener <StatusbarPanel> l = new OnChangeListener <StatusBarPanel> (){
		// 	@Override
		// 	public void onChange(StatusBarPanel object){
		// 		drawingPanel.setdrawColor(object.getSelectedColor());
		// 	}
		// 	statusBarPanel.setOnChangeListener(l);
		// };


	}

	public void updateHeader(){
		setTitle(drawingTitle);
	}

	public void setDrawingTitle(String name, String author) {
		
			drawingTitle = APP_NAME;
			
			String n = name.trim();
			String a = author.trim();
	
			if ( !n.isEmpty() && !a.isEmpty() ) {
				drawingTitle +=  "-" + n + " " + "by" + " " + a;
			}
			if (!n.isEmpty() && a.isEmpty()) {
				drawingTitle += "-" + n;
			}
			if (n.isEmpty() && !a.isEmpty() ) {
				drawingTitle += "-" + "[untitled drawing]" + " " + "by"+ " " + a;
			}
			updateHeader();
	}

	public String getDrawingTitle(){
		return this.drawingTitle;
	}

	 @FunctionalInterface
		public interface OnChangeListener <T> {
			public void onChange( T listener);
		}

	class CustomMouseAdapter extends MouseAdapter {

		@Override
		public void mouseMoved(MouseEvent e) {
	
			// Uppdatera koordinater i statusBarPanel
			 int x = e.getX();
			 int y = e.getY();
			statusBarPanel.updateCoordinates(x,y);
			// statusBarPanel.updateCoordinates(e.getX(),e.getY());
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			if (((Component) e.getSource()).getMousePosition() != null) {
				//  Uppdatera koordinater i statusBarPanel
				int x = e.getX();
				int y = e.getY();
				statusBarPanel.updateCoordinates(x,y);
				
				drawingPanel.setDrawIsActive(true);
				drawingPanel.setEndPoint(x, y);

				repaint();
			} else {
				// Nollställ koordinater i statusBarPanel
				statusBarPanel.updateCoordinates(0, 0);
			}
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// Nollställ koordinater i statusBarPanel
			statusBarPanel.updateCoordinates(0, 0);
		}

		@Override
		public void mousePressed(MouseEvent e){			
			drawingPanel.setStartPoint(e.getX(), e.getY());			
		}
		
		@Override
		public void mouseReleased(MouseEvent e){
			drawingPanel.addShape();
			drawingPanel.setEndPoint(e.getX(), e.getY());
		}
	}
}
