package SmartPostItClient;

import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

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
class SPIMemoPanel extends JPanel implements SPIPanel//, UndoableEditListener
{
	private static final long serialVersionUID = 8552143505046031394L;
	private final transient static Logger log = Logger.getLogger(SPIMemoPanel.class);
	//transient private UndoManager undoMgr; 
	transient private SPIMemoPopup popup;
	
	private JEditorPane editorPane;
	private Vector<SPIDocument> spiDocs; 
	private SPIDocument spiDoc;
	
	
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
	
/*	public UndoManager getUndoMgr()
	{
		return undoMgr;
	}
	public void setUndoMgr(UndoManager undoMgr)
	{
		this.undoMgr = undoMgr;
	}*/
	
	
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
		editorPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		add(editorPane, BorderLayout.CENTER);
		
/*		undoMgr = new UndoManager();
		Document doc = editorPane.getDocument();
		doc.addUndoableEditListener(this);*/
		
		//QQQQQQQQQQ
		//if too much contents loaded in a editorPane, you have to do something
		
		
		this.popup = popup;		
		if (popup != null) {		addPopup(editorPane, popup);
		} else {					log.fatal("popup object = null");}
	}
	
	
	private static void addPopup(Component component, final SPIMemoPopup popup) {
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
	}
/*	@Override
	public void undoableEditHappened(UndoableEditEvent e)
	{
		undoMgr.addEdit(e.getEdit());
	}*/

}
