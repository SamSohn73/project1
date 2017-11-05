package SmartPostItClient;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.apache.log4j.Logger;

import java.awt.Toolkit;
import java.awt.Color;

/**
 * SmartPostIt 프레임
 * 
 * @author		sam
 * @Version		0.1
 */
class SPIFrame extends JFrame
{
	private static final long serialVersionUID = 7111347458428723034L;
	private static final transient Logger log = Logger.getLogger(SPIFrame.class);
	
	JPanel		panel;
	SPIPopup	popup;

	JPanel getPanel()
	{
		return panel;
	}
	void setPanel(JPanel panel)
	{
		this.panel = panel;
	}
	
	public SPIPopup getPopup()
	{
		return popup;
	}
	public void setPopup(SPIPopup popup)
	{
		this.popup = popup;
	}
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try {
					SPIFrame frame = new SPIFrame();
					frame.setVisible(true);
					
				} catch (Exception e) {
					log.fatal("Thread fail.");
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public SPIFrame()
	{
		super();
		log.debug("SPIFrame Constructor Starts.");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		log.debug("SPIFrame Constructor 1.");
		setBounds(100, 100, 300, 300);
		log.debug("SPIFrame Constructor 2.");
		setBackground(SPIUtil.YELLOW);
		log.debug("SPIFrame Constructor 3.");
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Program Files\\Java\\PostIt-Sam.png"));
		log.debug("SPIFrame Constructor 4.");
		
		log.debug("SPIFrame Constructor Ends.");
		/*panel = new JPanel();
		panel.setBackground(SPIUtil.YELLOW);	//PINK
		panel.setBorder(new EmptyBorder(5,5,5,5));
		panel.setLayout(new BorderLayout());
		setContentPane(panel);*/
	}

}
