package se.miun.elal2203.dt187g.jpaint.gui;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import se.miun.elal2203.dt187g.jpaint.Drawing;

/**
 * Manages the items in Menu.
 *
 * @author elal2203
 * @version 0.1
 * @since 2025-09-27
 */
public class MenuManager {
    private JPaintFrame frame;
    private DrawingPanel drawingPanel;
    private Menu menu;
    

    public MenuManager(JPaintFrame frame, DrawingPanel drawingPanel) {
        this.frame = frame;
        this.drawingPanel = drawingPanel;
        this.menu = new Menu();
        createMenu();
    }
    
    public Menu getMenu() {
        return menu;
    }

    private void createMenu() {
        createFileMenu();
        createEditMenu();
       // createFilterMenu(); // Empty for now
    }

    private void createFileMenu() {
		String sFile = "File";
		menu.addJMenu(sFile);
		menu.getJMenu(0).setMnemonic(KeyEvent.VK_F);

		menu.addJMenuItem(sFile, "New...", createNewDrawingAction(),
				KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK));
		menu.addJMenuItem(sFile, "Load...", createLoadAction(),
				KeyStroke.getKeyStroke(KeyEvent.VK_L, KeyEvent.CTRL_DOWN_MASK));
		menu.addJMenuItem(sFile, "Save As...", createSaveAction(),
				KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
		menu.addJMenuItem(sFile, "Info", showInfoAction());

		menu.getJMenu(0).addSeparator();
		menu.addJMenuItem(sFile, "Exit", al -> System.exit(0));

	}

    private void createEditMenu() {
		String sEdit = "Edit";
		String sDrawing = "Drawing";
		menu.addJMenu(sEdit);
		menu.addSubJMenu(sEdit, sDrawing);
		menu.getJMenu(1).setMnemonic(KeyEvent.VK_E);

		menu.addJMenuItem(sEdit, "Undo", createUndoAction(),
				KeyStroke.getKeyStroke(KeyEvent.VK_Z, KeyEvent.CTRL_DOWN_MASK));
		menu.addJMenuItem(sDrawing, "Name...", createChangeNameAction());
		menu.addJMenuItem(sDrawing, "Author...", createChangeAuthorAction());

		/* Denna rad, som du inte får ta bort, kommer skapa ett NullException.
		 * Du måste hantera denna situation i Menu-klassen. I vanliga fall
		 * hade det varit rimligt att ett Exception kastades (klienten bör 
		 * i vanliga fall göras medveten om att den försöker skapa ett 
		 * JMenuItem till en JMenu som inte existerar), men nu räcker
		 * det med att ingenting alls händer i det läget man anropar
		 * addJMenuItem med en sträng som inte kan hittas.
		 */
		menu.addJMenuItem("This JMenu doesn't exist", "abc");
	}
    
    private void createFilterMenu() {
		// TODO for assignment 6
	}
    
    /*
     * Flera av metoderna nedan kommer anropa JOptionPane.showInputDialog(...).
     * Denna metod returnerar en String. Tänk på att om användaren trycker på
     * "Cancel" så kommer null att returneras. När en användare trycker på "Cancel"
     * så ska givetvis ingenting alls hända; inget felmeddelande till användaren,
     * inget ändring av det grafiska gränssnittets tillstånd (en teckning ska
     * inte plötsligt få namnet "null"). Jag har sett många inlämningar där
     * "Cancel" har hanterats på tämligen oväntade sätt. Så håll det i åtanke,
     * att Cancel/Avbryt innebär just den saken.
     * 
     */
    
    private ActionListener createNewDrawingAction() {
		return al -> {
			
			Drawing newDrawing = new Drawing();
			
			String drawingName = JOptionPane.showInputDialog(drawingPanel, "Enter name of the DRAWAING");
			
			try {
				if (drawingName == null) {
					return; // cancel click
				}
				while (drawingName.isEmpty() || drawingName == null ) {
					drawingName = JOptionPane.showInputDialog(drawingPanel, " A drawing MUST have a name");
				}
				newDrawing.setName(drawingName);				
				
			} catch (DrawingException e) {
				JOptionPane.showMessageDialog(drawingPanel,e.getMessage());
				e.printStackTrace();
			}

			String authorName = JOptionPane.showInputDialog(drawingPanel, "Enter authorname");
			try {
				if (authorName == null ) {
					return; //cancel click
				}
				while (authorName.isEmpty() || authorName == null ) {
					authorName = JOptionPane.showInputDialog(drawingPanel, " A author MUST have a name");
				}
				newDrawing.setAuthor(authorName);
				drawingPanel.setDrawing(newDrawing);
			    frame.setDrawingTitle(drawingName,authorName);
			
			} catch (DrawingException e) {
				JOptionPane.showMessageDialog(drawingPanel, e.getMessage());
				e.printStackTrace();
	
			}
		};
	}

    private ActionListener createChangeNameAction() {
		return al -> {

			try {
				Drawing currentDrawing = drawingPanel.getDrawing();

				String newDrawingName = JOptionPane.showInputDialog(drawingPanel, "Change name of the current drawing name:" +  currentDrawing.getName() );
				if (newDrawingName ==null) {
					return;
				}
				currentDrawing.setName(newDrawingName);
				frame.setDrawingTitle(newDrawingName,currentDrawing.getAuthor());
			} catch (DrawingException e) {
				JOptionPane.showMessageDialog(drawingPanel, e.getMessage());
				e.printStackTrace();
			}
		};
			
	}

	private ActionListener createChangeAuthorAction() {
		return al -> {
			
			try {
				Drawing currentDrawing = drawingPanel.getDrawing();

				String newAuthorName = JOptionPane.showInputDialog(drawingPanel, "Change name of the current author: " + currentDrawing.getAuthor());
				 if (newAuthorName ==null) {
				 	return;
				}

				currentDrawing.setAuthor(newAuthorName);
				frame.setDrawingTitle(currentDrawing.getName(), newAuthorName);

			} catch (DrawingException e) {
				JOptionPane.showMessageDialog(drawingPanel, e.getMessage());
				e.printStackTrace();
			}
		};
	}

	private ActionListener createUndoAction() {
		return al -> {
			// TODO for assignment 5
		};
	}
	
	private ActionListener showInfoAction() {
		return al -> {
			// TODO for assignment 5
		};
	}

	private ActionListener createLoadAction() {
		return al -> {
			// TODO for assignment 6
		};
	}

	private ActionListener createSaveAction() {
		return al -> {
			// TODO for assignment 6
		};
	}
    
}
