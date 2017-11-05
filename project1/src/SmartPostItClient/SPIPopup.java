package SmartPostItClient;

import javax.swing.JPopupMenu;
import java.awt.Component;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import java.awt.Dimension;

import org.apache.log4j.Logger;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import javax.swing.ImageIcon;

class SPIPopup extends JPopupMenu
{
	private static final long serialVersionUID = -2925244852858865203L;
	JPopupMenu popupMenu;
	JMenuItem mntmAddNewNote;
	JMenuItem mntmAddNewTodo;
	
	JMenuItem mntmBlue;
	JMenuItem mntmGreen;
	JMenuItem mntmPink;
	JMenuItem mntmPurple;
	JMenuItem mntmWhite;
	JMenuItem mntmYellow;
	JSeparator separator;

	private final transient Logger log = Logger.getLogger(this.getClass());
	/**
	 * Create the panel.
	 */
	public SPIPopup()
	{
		popupMenu = new JPopupMenu();
		popupMenu.setPopupSize(new Dimension(130, 300));
		popupMenu.setBorderPainted(false);   
		//addPopup(this, popupMenu);
		
		mntmAddNewNote = new JMenuItem("새 메모");
		mntmAddNewNote.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
		popupMenu.add(mntmAddNewNote);
		
		mntmAddNewTodo = new JMenuItem("새 할 일");
		popupMenu.add(mntmAddNewTodo);
		
		
		separator = new JSeparator();
		popupMenu.add(separator);
		
		mntmBlue = new JMenuItem("파랑");
		mntmBlue.setIcon(new ImageIcon("C:\\Program Files\\Java\\Blue.png"));
		popupMenu.add(mntmBlue);
		
		mntmGreen = new JMenuItem("녹색");
		mntmGreen.setIcon(new ImageIcon("C:\\Program Files\\Java\\Green.png"));
		popupMenu.add(mntmGreen);
		
		mntmPink = new JMenuItem("분홍");
		mntmPink.setIcon(new ImageIcon("C:\\Program Files\\Java\\Pink.png"));
		popupMenu.add(mntmPink);
		
		mntmPurple = new JMenuItem("자주");
		mntmPurple.setIcon(new ImageIcon("C:\\Program Files\\Java\\Purple.png"));
		popupMenu.add(mntmPurple);
		
		mntmWhite = new JMenuItem("흰색");
		mntmWhite.setIcon(new ImageIcon("C:\\Program Files\\Java\\White.png"));
		popupMenu.add(mntmWhite);
		
		mntmYellow = new JMenuItem("노랑");
		mntmYellow.setIcon(new ImageIcon("C:\\Program Files\\Java\\Yellow.png"));
		popupMenu.add(mntmYellow);
		popupMenu.setFocusTraversalPolicy(new FocusTraversalOnArray(
				new Component[]{mntmAddNewNote, mntmAddNewTodo, separator, 
								mntmBlue, mntmGreen, mntmPink, mntmPurple, mntmWhite, mntmYellow}));

	}

/*	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}*/


}
