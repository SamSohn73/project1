package SmartPostItClient;

import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Insets;

import javax.swing.Action;
import javax.swing.JEditorPane;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;
import javax.swing.text.StyledEditorKit;

import org.apache.log4j.Logger;

import javax.swing.JButton;

/**
 * SmartPostIt 메모장 화면. rtf 문서 형식을 이용.
 * 
 * @author sam
 * @Version 0.1
 */
class SPIMemoView extends JPanel implements SPIContent
{
	private static final long serialVersionUID = 8552143505046031394L;

	private final transient Logger log = Logger.getLogger(this.getClass());
	private JEditorPane editorPane;
	private JPanel panel;
	private SPIMemoPopup popup;
	
	
	JEditorPane getEditorPane()
	{
		return editorPane;
	}
	void setEditorPane(JEditorPane editorPane)
	{
		this.editorPane = editorPane;
	}

	public JPanel getPanel()
	{
		return panel;
	}
	public void setPanel(JPanel panel)
	{
		this.panel = panel;
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
					SPIMemoView a = new SPIMemoView();
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
	public SPIMemoView()
	{
		setSize(250, 250);
		setLocation(100, 100);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(new BorderLayout(0, 0));
		add(panel, BorderLayout.CENTER);
		
		editorPane = new JEditorPane("text/rtf", "");
		editorPane.setMargin(new Insets(3, 5, 0, 0));
		editorPane.setBackground(SPIUtil.YELLOW);
		panel.add(editorPane, BorderLayout.CENTER);

		Action boldAction = new StyledEditorKit.BoldAction();
		boldAction.putValue(Action.NAME, "굵게");
		boldAction.putValue(Action.ACCELERATOR_KEY, makeAccelerator("B"));

		Action italicAction = new StyledEditorKit.ItalicAction();
		italicAction.putValue(Action.NAME, "기울임");
		italicAction.putValue(Action.ACCELERATOR_KEY, makeAccelerator("I"));

		Action underlineAction = new StyledEditorKit.UnderlineAction();
		underlineAction.putValue(Action.NAME, "밑줄");
		underlineAction.putValue(Action.ACCELERATOR_KEY, makeAccelerator("U"));
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





}
