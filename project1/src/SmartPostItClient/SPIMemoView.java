package SmartPostItClient;

import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Insets;

import javax.swing.Action;
import javax.swing.JEditorPane;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;
import javax.swing.text.StyledEditorKit;

import org.apache.log4j.Logger;

/**
 * SmartPostIt 메모장 화면. rtf 문서 형식을 이용.
 * 
 * @author sam
 * @Version 0.1
 */
class SPIMemoView extends JPanel implements SPIContent
{
	private static final long serialVersionUID = 8552143505046031394L;
	private JPanel contentPane;
	private JEditorPane editorPane;

	private final transient Logger logger = Logger.getLogger(this.getClass());
	/**
	 * Create the panel.
	 */
	public SPIMemoView()
	{
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		setLayout(new BorderLayout(0, 0));

		editorPane = new JEditorPane("text/rtf", "");
		editorPane.setMargin(new Insets(3, 5, 0, 0));
		add(editorPane, BorderLayout.CENTER);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		
		setSize(250, 250);
		setLocation(50, 50);

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


	JPanel getContentPane()
	{
		return contentPane;
	}
	void setContentPane(JPanel contentPane)
	{
		this.contentPane = contentPane;
	}

	JEditorPane getEditorPane()
	{
		return editorPane;
	}
	void setEditorPane(JEditorPane editorPane)
	{
		this.editorPane = editorPane;
	}
	
}
