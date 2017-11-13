package SmartPostItClient;

import java.util.Vector;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Action;
import javax.swing.JEditorPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.border.EmptyBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Document;
import javax.swing.text.StyledDocument;
import javax.swing.text.StyledEditorKit;
import javax.swing.undo.UndoManager;

import org.apache.log4j.Logger;


/**
 * SmartPostIt 메모장 화면. rtf 문서 형식을 이용.
 * 
 * @author sam
 * @Version 0.1
 */
class SPIMemoPanel extends JPanel implements SPIPanel, UndoableEditListener, KeyListener
{
	private static final long serialVersionUID = 8552143505046031394L;
	private final transient static Logger log = Logger.getLogger(SPIMemoPanel.class);
	private UndoManager undoMgr;
	private StyledDocument styledDocument;
	
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
		this.popup = popup;
		
		setSize(250, 250);
		setLocation(100, 100);
		setLayout(new BorderLayout(0, 0));
		setBorder(new EmptyBorder(0, 0, 0, 0));
		
		editorPane = new JEditorPane("text/rtf", "");

		editorPane.setBackground(SPIUtil.YELLOW);
		editorPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		editorPane.addKeyListener(this);

		add(editorPane, BorderLayout.CENTER);
		
		// Undo, Redo
		undoMgr = new UndoManager();
		Document doc = editorPane.getDocument();
		doc.addUndoableEditListener(this);
		
		// Bold, Italic, Underline, font smaller, font bigger
		/*styledDocument = new DefaultStyledDocument();
		editorPane.setDocument(styledDocument);
		editorPane.addCaretListener(new SelectedText());*/
		
		
		if (this.popup != null) {		addPopup(editorPane, this.popup);
		} else {						log.fatal("popup object = null");}
		//editorPane.add(this.popup);
		
/*		JMenuBar bar = new JMenuBar();
		JMenu editMenu = new JMenu();
		Action boldAction = new StyledEditorKit.BoldAction();
		boldAction.putValue(Action.NAME, "Bold");
		boldAction.putValue(Action.ACCELERATOR_KEY, makeAccelerator("B"));
		editMenu.add(boldAction);
		bar.add(editMenu);
		bar.setVisible(true);
		spiDoc.getFrame().setJMenuBar(bar);*/
		
		//QQQQQQQQQQ
		//if too much contents loaded in a editorPane, you have to do something
		
		
		//QQQQQQQQQQ
		//Add new function to enlarge / en
/*		
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
		});*/
		

		 StyledEditorKit editorKit = new StyledEditorKit();		 
		 editorPane.setEditorKit(editorKit);
		 
		//Use Ctrl+B to BOLD/unBOLD selected text
		Action boldAction = new StyledEditorKit.BoldAction();
		KeyStroke boldKey = KeyStroke.getKeyStroke("ctrl B");
		editorPane.getInputMap().put(boldKey, boldAction);
		
		//Use Ctrl+I to Italic/unItalic selected text
		Action italicAction = new StyledEditorKit.ItalicAction();
		KeyStroke italicKey = KeyStroke.getKeyStroke("ctrl I");
		editorPane.getInputMap().put(italicKey, italicAction);
		
		//Use Ctrl+U to Underline/unUnderline selected text
		Action underlineAction = new StyledEditorKit.UnderlineAction();
		KeyStroke underlineKey = KeyStroke.getKeyStroke("ctrl U");
		editorPane.getInputMap().put(underlineKey, underlineAction);
		
		//Use Ctrl+U to Underline/unUnderline selected text
		Action fontsizeBiggerAction = 
				new StyledEditorKit.FontSizeAction("fontsizeBiggerAction", editorPane.getFont().getSize()+10);
		KeyStroke fontsizeBiggerKey = KeyStroke.getKeyStroke("ctrl W");
		editorPane.getInputMap().put(fontsizeBiggerKey, fontsizeBiggerAction);
		
		//Use Ctrl+U to Underline/unUnderline selected text
		Action fontsizeSmallerAction = 
				new StyledEditorKit.FontSizeAction("fontsizeSmallerAction", editorPane.getFont().getSize()-1);
		KeyStroke fontsizeSmallerKey = KeyStroke.getKeyStroke("ctrl S");
		editorPane.getInputMap().put(fontsizeSmallerKey, fontsizeSmallerAction);
	}
	
	
	private static void addPopup(Component component, final SPIMemoPopup popup)
	{
		component.addMouseListener(new MouseAdapter()
		{
			public void mousePressed(MouseEvent e)
			{
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e)
			{
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e)
			{
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
	
	
	@Override
	public void undoableEditHappened(UndoableEditEvent e)
	{
		undoMgr.addEdit(e.getEdit());
	}
	public void undo()
	{
		if(getUndoMgr().canUndo())		getUndoMgr().undo();
		else							log.warn("Unable to Undo.");
	}
	
	public void redo()
	{
		if(getUndoMgr().canRedo())		getUndoMgr().redo();
		else							log.warn("Unable to Redo.");
	}
	

	@Override	public void keyReleased(KeyEvent e){}
	@Override	public void keyTyped(KeyEvent e){}
	@Override	public void keyPressed(KeyEvent e)
	{
		if (e.getKeyCode() == KeyEvent.VK_Z && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {

			undo();
		}
		if (e.getKeyCode() == KeyEvent.VK_Y && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {
			redo();
		}
		
		/*if (e.getKeyCode() == KeyEvent.VK_B && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {
			log.debug("QQQQQQQQQQ BBBBBBB");
			//Action boldAction = new StyledEditorKit.BoldAction();
		}
		if (e.getKeyCode() == KeyEvent.VK_I && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {
			new StyledEditorKit.ItalicAction();
		}
		if (e.getKeyCode() == KeyEvent.VK_U && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {
			new StyledEditorKit.UnderlineAction();
		}*/
		
		if (e.getKeyCode() == KeyEvent.VK_UP && 
				((e.getModifiers() & KeyEvent.CTRL_MASK) != 0) && (e.getModifiers() & KeyEvent.ALT_MASK) != 0) {
			
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN && 
				((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)&& (e.getModifiers() & KeyEvent.ALT_MASK) != 0) {
			
		}
	}
	
}


