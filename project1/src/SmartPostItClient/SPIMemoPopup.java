package SmartPostItClient;

import java.awt.Component;

import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.Vector;

import org.apache.log4j.Logger;
import org.eclipse.wb.swing.FocusTraversalOnArray;

class SPIMemoPopup extends SPIPopup implements ActionListener
{
	private static final long serialVersionUID = -2925244852858865203L;

	JMenuItem mntmCut;
	JMenuItem mntmCopy;
	JMenuItem mntmPaste;
	JMenuItem mntmSelAll;
	JSeparator separator_2;
	JSeparator separator_3;

	private final transient Logger log = Logger.getLogger(this.getClass());
	/**
	 * Create the panel.
	 */
	public SPIMemoPopup(SPIFactory factory, Vector<SPIDocument> spiDocs)
	{
		super(factory, spiDocs);
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
		
		mntmAddNewNote.addActionListener(this);
		mntmAddNewTodo.addActionListener(this);
		
		mntmBlue.addActionListener(this);
		mntmGreen.addActionListener(this);
		mntmPink.addActionListener(this);
		mntmPurple.addActionListener(this);
		mntmWhite.addActionListener(this);
		mntmYellow.addActionListener(this);
		
		mntmCut.addActionListener(this);
		mntmCopy.addActionListener(this);
		mntmPaste.addActionListener(this);
		mntmSelAll.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		// TODO Auto-generated method stub
		if (e.getSource() == mntmAddNewNote) {
			factory.createSPIDoc(SPIType.MEMO);
		}
		if (e.getSource() == mntmAddNewTodo) {
			
		}
		
		if (e.getSource() == mntmBlue) {
			
		}
		if (e.getSource() == mntmGreen) {
			
		}
		if (e.getSource() == mntmPink) {
			
		}
		if (e.getSource() == mntmPurple) {
			
		}
		if (e.getSource() == mntmWhite) {
			
		}
		if (e.getSource() == mntmYellow) {
			
		}
		
		if (e.getSource() == mntmCut) {
			
		}
		if (e.getSource() == mntmCopy) {
			
		}
		if (e.getSource() == mntmPaste) {
			
		}
		if (e.getSource() == mntmSelAll) {
			
		}
	}

}
