package SmartPostItClient;

import javax.swing.JPopupMenu;
import java.awt.Component;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import java.awt.Dimension;
import org.eclipse.wb.swing.FocusTraversalOnArray;

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


	/**
	 * Create the panel.
	 */
	public SPIPopup()
	{
		popupMenu = new JPopupMenu();
		popupMenu.setPopupSize(new Dimension(150, 300));
		popupMenu.setBorderPainted(false);
		//addPopup(this, popupMenu);
		
		mntmAddNewNote = new JMenuItem("새 메모");
		popupMenu.add(mntmAddNewNote);
		
		mntmAddNewTodo = new JMenuItem("새 할 일");
		popupMenu.add(mntmAddNewTodo);
		
		
		JSeparator separator_1 = new JSeparator();
		popupMenu.add(separator_1);
		
		mntmBlue = new JMenuItem("파랑");
		popupMenu.add(mntmBlue);
		
		mntmGreen = new JMenuItem("녹색");
		popupMenu.add(mntmGreen);
		
		mntmPink = new JMenuItem("분홍");
		popupMenu.add(mntmPink);
		
		mntmPurple = new JMenuItem("자주");
		popupMenu.add(mntmPurple);
		
		mntmWhite = new JMenuItem("흰색");
		popupMenu.add(mntmWhite);
		
		mntmYellow = new JMenuItem("노랑");
		popupMenu.add(mntmYellow);
		popupMenu.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{mntmAddNewNote, mntmAddNewTodo, separator_1, mntmBlue, mntmGreen, mntmPink, mntmPurple, mntmWhite, mntmYellow}));

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
