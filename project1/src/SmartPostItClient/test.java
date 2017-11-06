package SmartPostItClient;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import javax.swing.JTextArea;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class test extends JFrame
{
	private static final long serialVersionUID = 1L;
	static test frame;
	private JPanel contentPane;
	//QQQQQQQQQQ
	SPIMemoPopup popup = new SPIMemoPopup();
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
					frame = new test();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public test()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JTextArea textArea = new JTextArea();
		textArea.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				System.out.println("QQQQ test1");
				popup.show(e.getComponent(), e.getX(), e.getY());
				//QQQQQQQQQQ
				if (e.getButton() == 3) {
					System.out.println("QQQQ test2 " + e.getComponent()+ " " + e.getX() + " " + e.getY());
					popup.show(e.getComponent(), e.getX(), e.getY());
					System.out.println("QQQQ test3");
				}
			}
		});
		contentPane.add(textArea, BorderLayout.CENTER);
		
	}

}
