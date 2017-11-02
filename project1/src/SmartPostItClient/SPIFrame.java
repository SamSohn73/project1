package SmartPostItClient;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.apache.log4j.Logger;

import java.awt.Toolkit;

/**
 * SmartPostIt 프레임
 * 
 * @author		sam
 * @Version		0.1
 */
class SPIFrame extends JFrame
{
	private static final long serialVersionUID = 7111347458428723034L;
	SPIFrame frame;
	private JPanel		contentPane;

	private static final transient Logger logger = Logger.getLogger(SPIFrame.class);
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
					SPIFrame frame = new SPIFrame();
					frame.setVisible(true);
					
				} catch (Exception e) {
					logger.fatal("Thread fail.");
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SPIFrame()
	{
		//frame = new SPIFrame();
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Program Files\\Java\\PostIt-Sam.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		/*setBounds(100, 100, 300, 300);
		contentPane = new JPanel();
		contentPane.setBackground(SPIUtil.PINK);	//YELLOW
		contentPane.setBorder(new EmptyBorder(5,5,5,5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);*/
	}

}
