package se.miun.elal2203.dt187g.jpaint.gui;



import java.awt.Component;
import java.awt.event.ActionListener;
import java.util.jar.Attributes.Name;

import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;


/**
	 * This class handles all the JMenuBar items.
	 * For instance, they add items in menus.
	 * 
	 *
	 * @author elal2203
     * @version 1.0 
     * @since YYYY-MM-DD
	 * */

public class Menu extends JMenuBar {
    

    /*
	 * Eftersom Menu utökar JMenuBar så kommer en JMenu läggas till på precis samma
	 * sätt som man lägger till en JMenu till en JMenuBar
	 */
    public void addJMenu(String name){

        JMenu menu = new JMenu(name);
        menu.setName(name);
        
        this.add(menu); // method from JMenuBar
    }


    /*
	 * Använd metoden getComponentByName (du måste skriva den först)
	 * för att hitta vilken JMenu som ett JMenuItem ska läggas till.
	 * Tänk på att metoden getComponentByName kan returnera null!
	 */

     // Oavsett vilken av metoderna som anropas så ska alltid ett
     //  JMenuItem-objekt läggas till det JMenu-objekt som har samma namn som
     //  parametern parentName. Om argumentet som skickas till parentName
     //  inte motsvarar något av JMenu-objekten så hanterar du detta.
    public void addJMenuItem(String parantName, String itemName){

        try {
			JComponent parentMenu = getComponentByName(parantName);
			
			
			if (parentMenu != null && parentMenu instanceof JMenu ) {
				
				JMenu menu = (JMenu) parentMenu;
				JMenuItem itemInMenu = new JMenuItem(itemName);
				
				menu.add(itemInMenu);
			}
			else {
			//	System.err.println(parentMenu + " blivit null");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			//System.err.println("katastorfFFFFFFFFFFFFFFF");
		}
	}
    /*
	 * Ni får själv bestämma om ni vill anropa addJMenuItem(parentName, itemName)
	 * för att på så sätt "kedja" anropen och göra koden lättare att underhålla.
	 * ActionListener ska helt enkelt "addas" till det JMenuItem vi skapar.
	 */

	 //Skapa barnet (JMenuItem/JMenu) och ropa på förädlren (JMenu)
	public void addJMenuItem(String parentName, String itemName, ActionListener al) {
		// TODO

		addJMenuItem(parentName, itemName);
		JComponent parentInMenu = getComponentByName(parentName);
		JComponent childInMenu = getComponentByName(itemName);

		if (childInMenu != null && childInMenu instanceof JMenuItem) {
			JMenuItem jItemMenu = (JMenuItem) childInMenu ;
			
			jItemMenu.addActionListener(al);
			parentInMenu.add(childInMenu);
		}
	}

	/*
	 * För att kunna använda tangentbord-kommandon för att nå meny-alternativ, så anropar man
	 * metoden setAccelerator(KeyStroke keyStroke).
	 */
	public void addJMenuItem(String parentName, String itemName, ActionListener al, KeyStroke keyStroke) {
		
		this.addJMenuItem(parentName, itemName, al);
		
		JComponent parentMenu = getComponentByName(parentName);
		JMenuItem childInMenu = (JMenuItem) getComponentByName(itemName);

		if (parentMenu != null && parentMenu instanceof JMenu) {
			
			childInMenu.setAccelerator(keyStroke);
		}
	}

	/*
	 * Denna metod kommer lägga till ett JMenu-objekt
	 * till ett befintligt JMenu-objekt.
	 * parentName är namnet på den JMenu som sub-menyn ska
	 * tillhöra.
	 */
	public void addSubJMenu(String parentName, String subMenuName) {

		JComponent parentMenu = getComponentByName(parentName);

		if (parentMenu != null && parentMenu instanceof JMenu) {
			
			JMenu menu = (JMenu) parentMenu;
			JMenu subJMenuItem = new JMenu(subMenuName);
			menu.add(subJMenuItem);
		}
	}

	/*
	 * Ganska självförklarande. Läs dokumentationen över JMenuBar
	 * för att hitta lämplig metod
	 */
	public JMenu getJMenu(int index) {

		// TODO
		return super.getMenu(index);
	}


	/*
	 * Denna metod kommer returnera en komponent som har lagts till
	 * i våran Menu-klass, oavsett om komponenten är en JMenu eller ett JMenuItem,
	 * Vi är alltså ute efter komponenter utifrån "namn".
	 * Med namn så menar vi i det här fallet vad JMenu- och JMenuItem-objekt
	 * heter i vårat GUI, såsom "File", "Load...", "Drawing" osv.
	 * En sak som kan vara bra att veta är att JMenu faktiskt ärver från JMenuItem.
	 * Så även fast JMenuItem är något som ligger inuti en JMenu, såsom våra kodexempel 
	 * visar, så är JMenu en mer specificerad klass än JMenuItem.
	 */
	private JComponent getComponentByName(String name) {
		/*
		 * 1. JMenuBar ärver från 
		 * javax.swing.JComponent, som ärver från
		 * java.awt.Container, som ärver från
		 * java.awt.Component, som ärver från
		 * java.lang.Object.
		 * 
		 * ¯\_(ツ)_/¯
		 *  
		 * Tack vare java.awt.Container så ärver JMenuBar metoden getComponents().
		 * getComponents() returnerar en array med samtliga barn-komponenter (Component).
		 * Använd en for-each loop för att loopa över våran Menu-klass samtliga barn-komponenter.
		 */
		
		// TODO
		
        Component[] components = this.getComponents();
		// Toppnivåns menyer
        for (Component c : components) {
            //JMenu = barn, JMenuItem = pappa och barn
			if (c instanceof JMenuItem) {
				
				JMenuItem jMenuItem = (JMenuItem) c;
				String jMenuText = jMenuItem.getText();    //File eller Edit. föräldrar
				
				// "File" or "Edit"	
				if (name.equals(jMenuText)) {
					 return jMenuItem;
				}
				
				// Alla komponenter i denna meny (JMenuItem eller undermenyer)
				Component[] menuComponements = ((JMenu) jMenuItem).getMenuComponents();
				 
				 for (Component mc : menuComponements) {
					 
					 // Om komponenten är en JMenuItem (vanliga menyval som New..., Exit)
					 if (mc instanceof JMenuItem) {
						 
						 String mcText = ((JMenuItem) mc).getText();
						 if(mcText.equals(name)){
							return (JMenuItem) mc;
						 }
						 // Om det är en undermeny i en JMenu (Name/author)
						 if (mc instanceof JMenu ) {
							 
							 JMenu jSubMenu = (JMenu) mc;
							// if (jSubMenu.getText().equals(name)) {
							// 	return jSubMenu;
							// }
	
							// Nivå 3 - alla menuval i undermenyn
							for (Component sSubMenuComp : jSubMenu.getMenuComponents()) {
								if (sSubMenuComp instanceof JMenuItem) {
	
									JMenuItem sSubItem = (JMenuItem) sSubMenuComp;
									if (sSubItem.getText().equals(name)) {
										return sSubItem;
									}
								}
							}
						 }
					 }
					 
				 }
				}
			}
			return null;
           // System.out.println("Indata: " + name); // undo, name..., drwaing- barnen
		   // System.out.println("Jämför med det i listan: " + jMenuText);
		   


		/*
		 * 2. Vi är egentligen säkra på att alla "närmaste" barn-komponenter till våran Menu-klass
		 * kommer vara JMenu-objekt, så vi kan tryggt "casta" varje objekt som loopas igenom
		 * till ett JMenu-objekt. (egentligen kan vi casta till JMenuItem också. Hur kommer det sig tror du?)
		 */
		
		// TODO



		/*
		 * 3. Varför vill vi casta till ett JMenu-objekt? Jo, för att kunna anropa metoden
		 * getText() och jämföra med parametern "name". Om så är fallet,
		 * då har vi hittat våran komponent, och kan returnera den. Kom ihåg att INTE använda
		 * likhetsoperatorn (==) när du kollar efter att texten är densamma.
		 */
		
		// TODO
		
		/*
		 * 4. Ok, med den kod som skrivits fram till nu så kan vi hitta alla JMenu-objekt som är
		 * addade direkt till våran JMenuBar. Men hur gör vi för att nå JMenu-objekt som är addade
		 * till andra JMenu-objekt, såsom fallet med att Drawing är tillagd till Edit?
		 * Well, JMenu-klassen har en metod som heter getMenuComponents(), 
		 * så vi kan på precis samma sätt som innan köra en for-each loop. Denna loop ska
		 * ske inuti den befintliga loopen.
		 */
		
		// TODO

        //KLAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
		
		/*
		 * 5. I det här fallet kan vi inte casta allt som passerar loopen som JMenu.
		 * Just nu har denna klass ingen nytta av att komma åt JMenuItems, men det
		 * är ändå inte så snyggt att begränsa en metod med namnet "getComponentByName"
		 * att bara returnera JMenu-objekt.
		 * Men en till orsak är att JMenu är en subklass till JMenuItem, så att försöka casta
		 * ett JMenuItem till en JMenu kommer leda till att ett Exception kastas.
		 * Och sen har vi en JSeparator i en av menyerna, och den kan vi inte heller
		 * casta till vare sig JMenuItem eller JMenu.
		 * 
		 * Vi behöver använda oss av "instanceof"-operatorn. 
		 * Eftersom JMenu ärver från JMenuItem, och eftersom getText(), vilket är den metod vi behöver, 
		 * är en metod som tillhör en föräldraklassen till JMenuItem (javax.swing.AbstractButton), 
		 * så räcker det med att vi kollar om det rör sig om ett JMenuItem.
		 */
		
		// TODO
		
		/*
		 * 6. Om det objekt som passerar genom loopen är ett JMenuItem, så är det bara
		 * att casta objektet och returnera det om den har samma text som parametern. 
		 */
		
		// TODO
		
		/*
		 * 7. Om inga av dessa vilkor har kunna uppfyllas, då måste metoden returnera null.
		 */

	}


}
