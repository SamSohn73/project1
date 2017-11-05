package SmartPostItClient;

import javax.swing.JPopupMenu;
import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;

import java.awt.Dimension;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import org.apache.log4j.Logger;
import org.eclipse.wb.swing.FocusTraversalOnArray;

class SPIMemoPopup extends SPIPopup
{
	private static final long serialVersionUID = -2925244852858865203L;

	JMenuItem mntmCut;
	JMenuItem mntmCopy;
	JMenuItem mntmPaste;
	JMenuItem mntmDelete;
	JMenuItem mntmSelAll;
	JSeparator separator_2;
	JSeparator separator_3;

	private final transient Logger log = Logger.getLogger(this.getClass());
	/**
	 * Create the panel.
	 */
	public SPIMemoPopup()
	{
		super();
		popupMenu.setPopupSize(new Dimension(130, 350));

		mntmCut = new JMenuItem("잘라내기");
		mntmCut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));
		popupMenu.add(mntmCut);
		
		mntmCopy = new JMenuItem("복사");
		mntmCopy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
		popupMenu.add(mntmCopy);
		
		mntmPaste = new JMenuItem("붙여넣기");
		mntmPaste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK));
		popupMenu.add(mntmPaste);
		
		mntmDelete = new JMenuItem("삭제");
		mntmDelete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, InputEvent.CTRL_MASK));
		popupMenu.add(mntmDelete);
		
		separator_2 = new JSeparator();
		popupMenu.add(separator_2);
		
		mntmSelAll = new JMenuItem("모두 선택");
		mntmDelete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
		popupMenu.add(mntmSelAll);
		
		separator_3 = new JSeparator();
		popupMenu.add(separator_3);
		
		popupMenu.setFocusTraversalPolicy(new FocusTraversalOnArray(
				new Component[]{mntmAddNewNote, mntmAddNewTodo,  //separator,
						mntmCut, mntmCopy, mntmPaste, mntmDelete, separator_2, mntmSelAll, separator_3,
						mntmBlue, mntmGreen, mntmPink, mntmPurple, mntmWhite, mntmYellow}));

	}

}
