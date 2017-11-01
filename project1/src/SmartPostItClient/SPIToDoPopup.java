package SmartPostItClient;

import javax.swing.JPopupMenu;
import java.awt.Component;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import java.awt.Dimension;
import org.eclipse.wb.swing.FocusTraversalOnArray;

class SPIToDoPopup extends SPIPopup
{
	private static final long serialVersionUID = 6767648445085689234L;
	
	JMenuItem mntmAddToDo;
	JMenuItem mntmModifyToDo;
	JMenuItem mntmDeleteToDo;
	
	/**
	 * Create the panel.
	 */
	public SPIToDoPopup()
	{
		popupMenu = new JPopupMenu();
		popupMenu.setPopupSize(new Dimension(150, 380));
		popupMenu.setBorderPainted(false);
		//addPopup(this, popupMenu);
		
		mntmAddNewNote = new JMenuItem("새 메모");
		popupMenu.add(mntmAddNewNote);
		
		mntmAddNewTodo = new JMenuItem("새 할 일");
		popupMenu.add(mntmAddNewTodo);
		
		JSeparator separator = new JSeparator();
		popupMenu.add(separator);
		
		mntmAddToDo = new JMenuItem("할 일 추가");
		popupMenu.add(mntmAddToDo);
		
		mntmModifyToDo = new JMenuItem("할 일 수정");
		popupMenu.add(mntmModifyToDo);
		
		mntmDeleteToDo = new JMenuItem("할 일 삭제");
		popupMenu.add(mntmDeleteToDo);
		
		JSeparator separator_2 = new JSeparator();
		popupMenu.add(separator_2);
		
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
		popupMenu.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{mntmAddNewNote, mntmAddNewTodo, separator, mntmAddToDo, mntmModifyToDo, mntmDeleteToDo, separator_2, mntmBlue, mntmGreen, mntmPink, mntmPurple, mntmWhite, mntmYellow}));

	}

}
