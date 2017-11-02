package SmartPostItClient;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.apache.log4j.Logger;

class SPIToDoAlarm extends JFrame
{
	private static final long serialVersionUID = 4475055436434896148L;
	private JPanel contentPane;

	private final transient static Logger logger = Logger.getLogger(SPIToDoAlarm.class);
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
					SPIToDoAlarm frame = new SPIToDoAlarm();
					frame.setVisible(true);
				} catch (Exception e) {
					logger.fatal("Thread Fail.");
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SPIToDoAlarm()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

}
