package SmartPostItClient;

import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Action;
import javax.swing.JEditorPane;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;
import javax.swing.text.StyledEditorKit;

import org.apache.log4j.Logger;

import javafx.geometry.Insets;


/**
 * SmartPostIt 메모장 화면. rtf 문서 형식을 이용.
 * 
 * @author sam
 * @Version 0.1
 */
class SPIMemoPanel extends JPanel implements SPIPanel
{
	private static final long serialVersionUID = 8552143505046031394L;

	private final transient static Logger log = Logger.getLogger(SPIMemoPanel.class);
	private JEditorPane editorPane;
	private SPIMemoPopup popup;
	
	
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
	
	
	
	public static void main(String[] args)
	{

		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try {
					SPIMemoPanel a = new SPIMemoPanel();
					a.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the panel.
	 */
	public SPIMemoPanel()
	{
		super();
		setSize(250, 250);
		setLocation(100, 100);
		setLayout(new BorderLayout(0, 0));
		setBorder(new EmptyBorder(0, 0, 0, 0));
/*		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(new BorderLayout(0, 0));
		add(panel, BorderLayout.CENTER);*/
		
		editorPane = new JEditorPane("text/rtf", "");
		//editorPane.add(popup);

		//.setMargin(new Insets(0, 0, 0, 0));
		editorPane.setBackground(SPIUtil.YELLOW);
		editorPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		add(editorPane, BorderLayout.CENTER);
		editorPane.setVisible(true);

		Action boldAction = new StyledEditorKit.BoldAction();
		boldAction.putValue(Action.NAME, "굵게");
		boldAction.putValue(Action.ACCELERATOR_KEY, makeAccelerator("B"));

		Action italicAction = new StyledEditorKit.ItalicAction();
		italicAction.putValue(Action.NAME, "기울임");
		italicAction.putValue(Action.ACCELERATOR_KEY, makeAccelerator("I"));

		Action underlineAction = new StyledEditorKit.UnderlineAction();
		underlineAction.putValue(Action.NAME, "밑줄");
		underlineAction.putValue(Action.ACCELERATOR_KEY, makeAccelerator("U"));
		
/*		if (popup != null) {			log.debug("popup - " + popup.toString());
										addPopup(editorPane, popup);
		} else {						log.debug("QQQQQQQQQQ popup = null");}*/
	}	
		
		
	public SPIMemoPanel(SPIMemoPopup popup)
	{
		this();
		this.popup = popup;
		
		if (popup != null) {		addPopup(editorPane, popup);
		} else {					log.fatal("popup object = null");}
	}

	/**
	 * Create a KeyStroke that uses the control key 
	 * 
	 * @param description
	 *            a string that describes the keystroke, without the "ctrl"; 
	 *            for example, "S" or "shift Z" or "alt F1"
	 * @return a keystroke created from the description string with either "ctrl "
	 */
	private static KeyStroke makeAccelerator(String description)
	{
		String commandKey;
		commandKey = "ctrl";

		return KeyStroke.getKeyStroke(commandKey + " " + description);
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
					//log.debug("Popup Triggered.");
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				//log.debug("e.getComponent() = " + e.getComponent());
				//log.debug("e.getX() = " + e.getX());
				//log.debug("e.getY() = " + e.getY());
				//log.debug("popup = " + popup.toString());
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
