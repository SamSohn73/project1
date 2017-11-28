package projectTest;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.StyledEditorKit;
import javax.swing.text.rtf.RTFEditorKit;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JEditorPane;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;

import java.awt.Component;
import java.awt.Dimension;

public class SPITest extends JFrame
{
	private JPanel contentPane;
	private JEditorPane editorPane;
	private JPopupMenu popupMenu;
	static Vector<SPITest> spiDocs;
	
	JMenuItem mntmCut;
	JMenuItem mntmCopy;
	JMenuItem mntmPaste;
	JMenuItem mntmDelete;
	JSeparator separator_3;
	JMenuItem mntmSelAll;
	JSeparator separator_2;
	
	JMenuItem mntmAddNewNote;
	JMenuItem mntmAddNewTodo;
	JSeparator separator;
	
	JMenuItem mntmBlue;
	JMenuItem mntmGreen;
	JMenuItem mntmPink;
	JMenuItem mntmPurple;
	JMenuItem mntmWhite;
	JMenuItem mntmYellow;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try {
					SPITest frame = new SPITest();
					frame.setVisible(true);
					spiDocs.add(frame);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SPITest()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 300);
		setBackground(new Color(255, 255, 153));
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Program Files\\Java\\PostIt-Sam.png"));
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.setBackground(new Color(255, 255, 153));
		setContentPane(contentPane);
		
		editorPane = new JEditorPane("text/rtf", "");
		/*editorPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == 3) {
					popupMenu.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});*/
		editorPane.setBackground(new Color(255, 255, 153));
		DefaultEditorKit editorKit = new DefaultEditorKit();
		editorPane.setEditorKitForContentType("text/rtf", editorKit);
		contentPane.add(editorPane, BorderLayout.CENTER);
		
		popupMenu = new JPopupMenu();
		popupMenu.setPopupSize(new Dimension(150, 350));
		popupMenu.setBorderPainted(false);


		mntmCut = new JMenuItem("잘라내기");
		mntmCut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));
		//mntmCut.setAction(new DefaultEditorKit.CutAction());
		mntmCut.setActionCommand(new RTFEditorKit().cutAction);
		popupMenu.add(mntmCut);
		
		mntmCopy = new JMenuItem("복사");
		mntmCopy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
		//mntmCopy.setAction(new DefaultEditorKit.CopyAction());
		popupMenu.add(mntmCopy);
		
		mntmPaste = new JMenuItem("붙여넣기");
		mntmPaste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK));
		//mntmPaste.setAction(new DefaultEditorKit.PasteAction());
		popupMenu.add(mntmPaste);
		
		mntmDelete = new JMenuItem("삭제");
		mntmDelete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, InputEvent.CTRL_MASK));
		//QQQQQQQQQQ mntmDelete.setAction(new DefaultEditorKit.);
		//editorPane.replaceSelection("");
		popupMenu.add(mntmDelete);
		
		separator_3 = new JSeparator();
		popupMenu.add(separator_3);
		
		mntmSelAll = new JMenuItem("모두 선택");
		mntmSelAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
		//QQQQQQQQQQ mntmDelete.setAction(new DefaultEditorKit.);
		//editorPane.selectAll();
		popupMenu.add(mntmSelAll);
		
		separator_2 = new JSeparator();
		popupMenu.add(separator_2);
		
		
		
		mntmAddNewNote = new JMenuItem("새 메모");
		mntmAddNewNote.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
		popupMenu.add(mntmAddNewNote);
		
		mntmAddNewTodo = new JMenuItem("새 할 일");
		popupMenu.add(mntmAddNewTodo);
		
		
		separator = new JSeparator();
		popupMenu.add(separator);
		
		mntmBlue = new JMenuItem("파랑");
		mntmBlue.setIcon(new ImageIcon("C:\\Program Files\\Java\\Blue.png"));
		popupMenu.add(mntmBlue);
		
		mntmGreen = new JMenuItem("녹색");
		mntmGreen.setIcon(new ImageIcon("C:\\Program Files\\Java\\Green.png"));
		popupMenu.add(mntmGreen);
		
		mntmPink = new JMenuItem("분홍");
		mntmPink.setIcon(new ImageIcon("C:\\Program Files\\Java\\Pink.png"));
		popupMenu.add(mntmPink);
		
		mntmPurple = new JMenuItem("자주");
		mntmPurple.setIcon(new ImageIcon("C:\\Program Files\\Java\\Purple.png"));
		popupMenu.add(mntmPurple);
		
		mntmWhite = new JMenuItem("흰색");
		mntmWhite.setIcon(new ImageIcon("C:\\Program Files\\Java\\White.png"));
		popupMenu.add(mntmWhite);
		
		mntmYellow = new JMenuItem("노랑");
		mntmYellow.setIcon(new ImageIcon("C:\\Program Files\\Java\\Yellow.png"));
		popupMenu.add(mntmYellow);
		popupMenu.setFocusTraversalPolicy(new FocusTraversalOnArray(
				new Component[]{mntmAddNewNote, mntmAddNewTodo, separator, 
								mntmBlue, mntmGreen, mntmPink, mntmPurple, mntmWhite, mntmYellow}));

		addPopup(editorPane, popupMenu);

	}

	private static void addPopup(Component component, final JPopupMenu popup) {
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
	

}
