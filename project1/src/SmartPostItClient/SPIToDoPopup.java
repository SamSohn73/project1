package SmartPostItClient;

import javax.swing.JPopupMenu;
import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import java.awt.Dimension;

import org.apache.log4j.Logger;
import org.eclipse.wb.swing.FocusTraversalOnArray;

class SPIToDoPopup extends SPIPopup
{
	private static final long serialVersionUID = 6767648445085689234L;
	
	JMenuItem mntmAddToDo;
	JMenuItem mntmModifyToDo;
	JMenuItem mntmDeleteToDo;
	JSeparator separator_2;
	
	private final transient Logger logger = Logger.getLogger(this.getClass());
	
	/**
	 * Create the panel.
	 */
	public SPIToDoPopup()
	{
		super();

		popupMenu.setPopupSize(new Dimension(130, 300));
		
		mntmAddToDo = new JMenuItem("할 일 추가");
		popupMenu.add(mntmAddToDo, 3);
		
		mntmModifyToDo = new JMenuItem("할 일 수정");
		popupMenu.add(mntmModifyToDo, 4);
		
		mntmDeleteToDo = new JMenuItem("할 일 삭제");
		popupMenu.add(mntmDeleteToDo, 5);
		
		separator_2 = new JSeparator();
		popupMenu.add(separator_2, 6);
		
		popupMenu.setFocusTraversalPolicy(new FocusTraversalOnArray(
				new Component[]{mntmAddNewNote, mntmAddNewTodo,  separator,
						mntmAddToDo, mntmModifyToDo, mntmDeleteToDo, separator_2, 
						mntmBlue, mntmGreen, mntmPink, mntmPurple, mntmWhite, mntmYellow}));

	}

}
