package SmartPostItClient;

import java.util.Vector;

import javax.swing.JMenuItem;
import javax.swing.JSeparator;

import java.awt.Component;
import java.awt.Dimension;

import org.eclipse.wb.swing.FocusTraversalOnArray;
import org.apache.log4j.Logger;

class SPIToDoPopup extends SPIPopup
{
	private static final long serialVersionUID = 1L;
	private final transient Logger log = Logger.getLogger(this.getClass());
	
	transient JMenuItem mntmAddToDo;
	transient JMenuItem mntmModifyToDo;
	transient JMenuItem mntmDeleteToDo;
	transient JSeparator separator_2;
	
	
	/**
	 * Create the panel.
	 */
	public SPIToDoPopup(SPIFactory factory, Vector<SPIDocument> spiDocs, SPIDocument spiDoc, Thread spiClientFileThread)
	{
		super(factory, spiDocs, spiDoc, spiClientFileThread);

		setPopupSize(new Dimension(130, 300));
		
		mntmAddToDo = new JMenuItem("할 일 추가");
		add(mntmAddToDo, 3);
		
		mntmModifyToDo = new JMenuItem("할 일 수정");
		add(mntmModifyToDo, 4);
		
		mntmDeleteToDo = new JMenuItem("할 일 삭제");
		add(mntmDeleteToDo, 5);
		
		separator_2 = new JSeparator();
		add(separator_2, 6);
		
		setFocusTraversalPolicy(new FocusTraversalOnArray(
				new Component[]{mntmAddNewNote, mntmAddNewTodo,  separator,
						mntmAddToDo, mntmModifyToDo, mntmDeleteToDo, separator_2, 
						mntmBlue, mntmGreen, mntmPink, mntmPurple, mntmWhite, mntmYellow}));

	}

}
