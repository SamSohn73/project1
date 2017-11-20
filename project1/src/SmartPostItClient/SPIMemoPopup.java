package SmartPostItClient;

import java.util.Vector;

import javax.swing.JEditorPane;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import org.apache.log4j.Logger;
import org.eclipse.wb.swing.FocusTraversalOnArray;

class SPIMemoPopup extends SPIPopup implements ActionListener
{
	private static final long serialVersionUID = -2925244852858865203L;
	private final transient Logger log = Logger.getLogger(this.getClass());

	transient JMenuItem mntmUndo;
	transient JMenuItem mntmRedo;
	
	transient JMenuItem mntmCut;
	transient JMenuItem mntmCopy;
	transient JMenuItem mntmPaste;
	
	transient JMenuItem mntmSelAll;
	
	transient JSeparator separator_2;
	transient JSeparator separator_3;
	transient JSeparator separator_4;

	/**
	 * Create the Memo Popup 
	 * You can not add it to EditorPane, you can make it show.
	 * This means your shorcut keys in popup menu will not work in your EditorPane.
	 *
	 */
	public SPIMemoPopup(SPIFactory factory, Vector<SPIDocument> spiDocs, SPIDocument spiDoc, Thread spiClientFileThread)
	{
		super(factory, spiDocs, spiDoc, spiClientFileThread);
		setPopupSize(new Dimension(180, 350));
		
		mntmUndo = new JMenuItem("실행 취소");
		mntmUndo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_MASK));
		add(mntmUndo, 3);
		
		mntmRedo = new JMenuItem("다시 실행");
		mntmRedo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, InputEvent.CTRL_MASK));
		add(mntmRedo, 4);
		
		separator_2 = new JSeparator();
		add(separator_2, 5);
		
		mntmCut = new JMenuItem("잘라내기");
		mntmCut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));
		add(mntmCut, 6);
		
		mntmCopy = new JMenuItem("복사");
		mntmCopy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
		add(mntmCopy, 7);
		
		mntmPaste = new JMenuItem("붙여넣기");
		mntmPaste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_MASK));
		add(mntmPaste, 8);
		
		separator_3 = new JSeparator();
		add(separator_3, 9);
		
		mntmSelAll = new JMenuItem("모두 선택");
		mntmSelAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
		add(mntmSelAll, 10);
		
		separator_4 = new JSeparator();
		add(separator_4, 11);
		
		setFocusTraversalPolicy(new FocusTraversalOnArray(
				new Component[]{  mntmAddNewNote, mntmAddNewTodo, separator, mntmUndo, mntmRedo, separator_2,
						mntmCut, mntmCopy, mntmPaste,separator_3, mntmSelAll, separator_4,
						mntmBlue, mntmGreen, mntmPink, mntmPurple, mntmWhite, mntmYellow, separator_e, 
						mntmExit}));
		
		mntmAddNewNote	.addActionListener(this);
		mntmAddNewTodo	.addActionListener(this);
		
		mntmRedo		.addActionListener(this);
		mntmUndo		.addActionListener(this);
		
		mntmCut			.addActionListener(this);
		mntmCopy		.addActionListener(this);
		mntmPaste		.addActionListener(this);
		mntmSelAll		.addActionListener(this);
		
		mntmBlue		.addActionListener(this);
		mntmGreen		.addActionListener(this);
		mntmPink		.addActionListener(this);
		mntmPurple		.addActionListener(this);
		mntmWhite		.addActionListener(this);
		mntmYellow		.addActionListener(this);
		
		mntmExit		.addActionListener(this);
	}
	
	//QQQQQQQQQQ You have to do something with this. You can't write the same code here and there
	@Override
	public void actionPerformed(ActionEvent e)
	{
		SPIMemoPanel	spiMemoPanel	= (SPIMemoPanel) spiDoc.getPanel();
		JEditorPane		editorPane		= ((SPIMemoPanel) spiDoc.getPanel()).getEditorPane();
		
		if (e.getSource() == mntmAddNewNote) {
			factory.createSPIDoc(SPIType.MEMO);
			//Save to file
			//QQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQ
			((SPIClientFile) spiClientFileThread).setFileSaveFlag(true);
			log.info("File saving flag setted - Create New PostIt Document by click new memo menu.");
		}
		if (e.getSource() == mntmAddNewTodo) {
			
		}
		
		if (e.getSource() == mntmBlue) {
			editorPane.setBackground(SPIUtil.BLUE);
		}
		if (e.getSource() == mntmGreen) {
			editorPane.setBackground(SPIUtil.GREEN);
		}
		if (e.getSource() == mntmPink) {
			editorPane.setBackground(SPIUtil.PINK);
		}
		if (e.getSource() == mntmPurple) {
			editorPane.setBackground(SPIUtil.PURPLE);
		}
		if (e.getSource() == mntmWhite) {
			editorPane.setBackground(SPIUtil.WHITE);
		}
		if (e.getSource() == mntmYellow) {
			editorPane.setBackground(SPIUtil.YELLOW);
		}
		if (e.getSource() == mntmUndo) {
			spiMemoPanel.undo();
		}
		if (e.getSource() == mntmRedo) {
			spiMemoPanel.redo();
		}
		
		if (e.getSource() == mntmCut) {
			editorPane.cut();
		}
		if (e.getSource() == mntmCopy) {
			editorPane.copy();
		}
		if (e.getSource() == mntmPaste) {
			editorPane.paste();
		}
		if (e.getSource() == mntmSelAll) {
			editorPane.selectAll();
		}
		if (e.getSource() == mntmExit) {
			//QQQQQQQQQQ
			//Save files and network
			
			System.exit(0);
		}
		
	}

}
