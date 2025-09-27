package se.miun.elal2203.dt187g.jpaint.gui;

import java.awt.GridLayout;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
    * ColorPalettePanel is a subclass of JPanel that overrides all methods from JPanel (except private ones).
	* Contains 2 ctor where both handles the layout. The second ctor handles a ArrayList<ColorPanel> colorPanels, where it adds a 
	  colorPanel ( a color) to the ArrayList  
	* ColorPalettePanel is like a box of colors
    
    *
    * @author elal2203
    * @version 1.0 
    */



public class ColorPalettePanel extends JPanel {

	/*
	 * Varför behöver vi ha en lista som lagrar ColorPanel-objekt?
	 * Det går givetvis att lösa på olika sätt, men detta är ett enkelt
	 * sätt att kunna anropa addMouseListener på respektive ColorPanel-
	 * objekt som ColorPalettePanel håller. Detta kommer vi göra i metoden
	 * setMouseListenerForColorPanels(MouseListener listener). Mer om detta nedan.
	 * */
	private ArrayList<ColorPanel> colorPanels; 
	
	
	/*
	 * Oavsett vilken constructor som anropas layout till ColorPalettePanel
	 * sättas till GridLayout.*/
	
	/*
	 * För denna constructor så ska listan colorPanels initialiseras.
	 */
	public ColorPalettePanel() {

		// TODO
		colorPanels = new ArrayList<>();
		setLayout(new GridLayout());
	}
	
	/*
	 * 
	 * För denna constructor så skickas en ArrayList med ColorPanel-objekt,
	 * vilket betyder att våran instansvariabel colorPanels initialiseras 
	 * via constructorn parameter (dvs "this.colorPanels = colorPanels").
	 * Om denna constructor anropas så måste varje ColorPanel-objekt som finns 
	 * i listan läggas till denna klass genom att anropa add-metoden för 
	 * denna klass (så att de blir synliga).
	 */
	public ColorPalettePanel(ArrayList<ColorPanel> colorPanels) {
		// TODO
		this.colorPanels = colorPanels;
		for (ColorPanel cp : colorPanels) {
			if (cp != null) {
				add(cp);
			}
		}
		// sätter layouten till grid- format
		setLayout(new GridLayout());
	}

	/*
	 * När addColorPanel anropas så ska det ColorPanel-objekt som
	 * skickas som argument läggas till i listan colorPanels 
	 * (detta för att enkelt kunna iterera över de ColorPanels som 
	 * finns tillgängliga inom denna klass i samband med att en 
	 * MouseListener ska sättas).
	 * Den ColorPanel som skickas som argument ska också grafiskt läggas
	 * till denna klass genom att anropa add-metoden för denna klass 
	 * (så att de blir synliga).
	 */
	public void addColorPanel(ColorPanel cp) {
		// TODO
		colorPanels.add(cp);

		//läggs till i layouten /GIU
		 add(cp);
	}

	/*
	 * Klassen ColorPalettePanel har alltså ingen som helst aning om vad
	 * det MouseListener-objekt som skickas som argument till denna metod gör.
	 * Det är inte heller ColorPalettePanel's ansvar att ha koll på det. Den tar
	 * vilken listener som än skickas som argument och sätter den listenern
	 * på samtliga ColorPanel-objekt genom att anropa .addMouseListener(listener) på
	 * samtliga ColorPanel-objekt som finns i listan.
	 */
	public void setMouseListenerForColorPanels(MouseListener listener) {
		// TODO
		for (ColorPanel colorPanel : colorPanels) {
			
			colorPanel.addMouseListener(listener);
		}
	}

}
