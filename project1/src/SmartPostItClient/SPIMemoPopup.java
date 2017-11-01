package SmartPostItClient;

import javax.swing.JPopupMenu;
import java.awt.Component;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import java.awt.Dimension;
import org.eclipse.wb.swing.FocusTraversalOnArray;

class SPIMemoPopup extends SPIPopup
{
	private static final long serialVersionUID = -2925244852858865203L;

	JMenuItem mntmCut;
	JMenuItem mntmCopy;
	JMenuItem mntmNewMenuItem;
	JMenuItem mntmDelete;
	JMenuItem mntmSelAll;
	

	/**
	 * Create the panel.
	 */
	public SPIMemoPopup()
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
		
		mntmCut = new JMenuItem("잘라내기");
		popupMenu.add(mntmCut);
		
		mntmCopy = new JMenuItem("복사");
		popupMenu.add(mntmCopy);
		
		mntmNewMenuItem = new JMenuItem("붙여넣기");
		popupMenu.add(mntmNewMenuItem);
		
		mntmDelete = new JMenuItem("삭제");
		popupMenu.add(mntmDelete);
		
		JSeparator separator_1 = new JSeparator();
		popupMenu.add(separator_1);
		
		mntmSelAll = new JMenuItem("모두 선택");
		popupMenu.add(mntmSelAll);
		
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
		popupMenu.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{mntmAddNewNote, mntmAddNewTodo, separator, mntmCut, mntmCopy, mntmNewMenuItem, mntmDelete, separator_1, mntmSelAll, separator_2, mntmBlue, mntmGreen, mntmPink, mntmPurple, mntmWhite, mntmYellow}));

	}

}
