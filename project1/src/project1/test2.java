package project1;

import javax.swing.JPanel;
import javax.swing.JComboBox;
import java.awt.BorderLayout;
import javax.swing.JEditorPane;

public class test2 extends JPanel
{

	/**
	 * Create the panel.
	 */
	public test2()
	{
		setLayout(new BorderLayout(0, 0));
		
		JEditorPane editorPane = new JEditorPane();
		add(editorPane, BorderLayout.CENTER);

	}
	
	public static void main(String[] args)
	{
		test2 ttt = new test2();
		ttt.setVisible(true);
	}

}
