package SmartPostItClient;

import java.util.Vector;

import javax.swing.JPopupMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.ImageIcon;

import java.awt.Component;
import java.awt.Dimension;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;

import org.eclipse.wb.swing.FocusTraversalOnArray;
import org.apache.log4j.Logger;


class SPIPopup extends JPopupMenu implements ActionListener
{
	private static final long serialVersionUID = -2925244852858865203L;
	private final transient Logger log = Logger.getLogger(this.getClass());
	
	SPIFactory factory;
	Vector<SPIDocument> spiDocs;
	SPIDocument spiDoc;
	transient Thread spiClientFileThread;
	
	transient JMenuItem mntmAddNewNote;
	transient JMenuItem mntmAddNewTodo;
	
	transient JMenuItem mntmBlue;
	transient JMenuItem mntmGreen;
	transient JMenuItem mntmPink;
	transient JMenuItem mntmPurple;
	transient JMenuItem mntmWhite;
	transient JMenuItem mntmYellow;
	
	transient JMenuItem mntmExit;
	
	transient JSeparator separator;
	transient JSeparator separator_e;
	
	
	/**
	 * Create the panel.
	 */
	public SPIPopup(SPIFactory factory, Vector<SPIDocument> spiDocs, SPIDocument  spiDoc, Thread spiClientFileThread)
	{
		super();
		this.factory = factory;
		this.spiDocs = spiDocs;
		this.spiDoc = spiDoc;
		this.spiClientFileThread = spiClientFileThread;
		
		setPopupSize(new Dimension(150, 300));
		setBorderPainted(false);   
		
		mntmAddNewNote = new JMenuItem("새 메모");
		mntmAddNewNote.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
		add(mntmAddNewNote);
		
		mntmAddNewTodo = new JMenuItem("새 할 일");
		add(mntmAddNewTodo);
		
		separator = new JSeparator();
		add(separator);
		
		
		mntmBlue = new JMenuItem("파랑");
		mntmBlue.setIcon(new ImageIcon("C:\\Program Files\\Java\\Blue.png"));
		add(mntmBlue);
		
		mntmGreen = new JMenuItem("녹색");
		mntmGreen.setIcon(new ImageIcon("C:\\Program Files\\Java\\Green.png"));
		add(mntmGreen);
		
		mntmPink = new JMenuItem("분홍");
		mntmPink.setIcon(new ImageIcon("C:\\Program Files\\Java\\Pink.png"));
		add(mntmPink);
		
		mntmPurple = new JMenuItem("자주");
		mntmPurple.setIcon(new ImageIcon("C:\\Program Files\\Java\\Purple.png"));
		add(mntmPurple);
		
		mntmWhite = new JMenuItem("흰색");
		mntmWhite.setIcon(new ImageIcon("C:\\Program Files\\Java\\White.png"));
		add(mntmWhite);
		
		mntmYellow = new JMenuItem("노랑");
		mntmYellow.setIcon(new ImageIcon("C:\\Program Files\\Java\\Yellow.png"));
		add(mntmYellow);
		
		separator_e = new JSeparator();
		add(separator_e);
				
		mntmExit = new JMenuItem("종료");
		//QQQQQQQQQQ key does not work
		mntmExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.CTRL_MASK + InputEvent.ALT_MASK));
		add(mntmExit);
		
		setFocusTraversalPolicy(new FocusTraversalOnArray(
				new Component[]{mntmAddNewNote, mntmAddNewTodo, separator, 
								mntmBlue, mntmGreen, mntmPink, mntmPurple, mntmWhite, mntmYellow, separator_e, 
								mntmExit}));
	}

	@Override
	public void actionPerformed(ActionEvent e){}
}
