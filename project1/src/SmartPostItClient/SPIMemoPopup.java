package SmartPostItClient;

import java.awt.Component;

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
		setPopupSize(new Dimension(130, 350));
		
		mntmCut = new JMenuItem("잘라내기");
		mntmCut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));
		add(mntmCut, 3);
		
		mntmCopy = new JMenuItem("복사");
		mntmCopy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
		add(mntmCopy, 4);
		
		mntmPaste = new JMenuItem("붙여넣기");
		mntmPaste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK));
		add(mntmPaste, 5);
		
		separator_2 = new JSeparator();
		add(separator_2, 6);
		
		mntmSelAll = new JMenuItem("모두 선택");
		mntmSelAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
		add(mntmSelAll, 7);
		
		separator_3 = new JSeparator();
		add(separator_3, 8);
		
		setFocusTraversalPolicy(new FocusTraversalOnArray(
				new Component[]{mntmAddNewNote, mntmAddNewTodo,  //separator,
						mntmCut, mntmCopy, mntmPaste,separator_2, mntmSelAll, separator_3,
						mntmBlue, mntmGreen, mntmPink, mntmPurple, mntmWhite, mntmYellow}));
	}

}
