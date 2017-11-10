package SmartPostItClient;

import java.util.Vector;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.JEditorPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.Document;
import javax.swing.undo.UndoManager;

import org.apache.log4j.Logger;


/**
 * SmartPostIt 메모장 화면. rtf 문서 형식을 이용.
 * 
 * @author sam
 * @Version 0.1
 */
class SPIMemoPanel extends JPanel implements SPIPanel, UndoableEditListener
{
	private static final long serialVersionUID = 8552143505046031394L;
	private final transient static Logger log = Logger.getLogger(SPIMemoPanel.class);
	transient private UndoManager undoMgr;
	
	private SPIMemoPopup popup;
	private Vector<SPIDocument> spiDocs; 
	private SPIDocument spiDoc;
	
	private JEditorPane editorPane;
	
	JEditorPane getEditorPane()
	{
		return editorPane;
	}
	void setEditorPane(JEditorPane editorPane)
	{
		this.editorPane = editorPane;
	}

	public SPIMemoPopup getPopup()
	{
		return popup;
	}
	public void setPopup(SPIMemoPopup popup)
	{
		this.popup = popup;
	}
	
	public UndoManager getUndoMgr()
	{
		return undoMgr;
	}
	public void setUndoMgr(UndoManager undoMgr)
	{
		this.undoMgr = undoMgr;
	}
	
	
	/**
	 * Create the panel.
	 */
	public SPIMemoPanel(SPIMemoPopup popup, Vector<SPIDocument> spiDocs, SPIDocument spiDoc)
	{
		super();
		setSize(250, 250);
		setLocation(100, 100);
		setLayout(new BorderLayout(0, 0));
		setBorder(new EmptyBorder(0, 0, 0, 0));
		
		editorPane = new JEditorPane("text/rtf", "");

		editorPane.setBackground(SPIUtil.YELLOW);
		editorPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		add(editorPane, BorderLayout.CENTER);
		
		undoMgr = new UndoManager();
		Document doc = editorPane.getDocument();
		doc.addUndoableEditListener(this);
		
		this.popup = popup;	
		editorPane.add(this.popup);

		
		//QQQQQQQQQQ
		//if too much contents loaded in a editorPane, you have to do something
		
		
		//QQQQQQQQQQ
		//Add new function to enlarge / en
				
		/*if (this.popup != null) {		addPopup(editorPane, this.popup);
		} else {						log.fatal("popup object = null");}*/
		
		editorPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == 3)
					popup.show((Component)e.getSource(), e.getX(), e.getY());
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				if(e.getButton() == 3)
					popup.show((Component)e.getSource(), e.getX(), e.getY());
			}
		});
	}
	
	
	/*private static void addPopup(Component component, final SPIMemoPopup popup) {
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
	
	
	@Override
	public void undoableEditHappened(UndoableEditEvent e)
	{
		undoMgr.addEdit(e.getEdit());
	}
	
	

}
