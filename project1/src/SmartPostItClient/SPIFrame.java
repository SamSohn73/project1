package SmartPostItClient;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Insets;
import java.awt.Toolkit;
import java.util.Vector;

import org.apache.log4j.Logger;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;



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
	
	Vector<SPIDocument> spiDocs;
	
	JPanel		panel;

	JPanel getPanel()
	{
		return panel;
	}
	void setPanel(JPanel panel)
	{
		this.panel = panel;
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
	public SPIFrame(Vector<SPIDocument> spiDocs)
	{
		super();
		
		this.spiDocs = spiDocs;
		
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 250, 250);
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Program Files\\Java\\PostIt-Sam.png"));
		//this.getRootPane().setBorder(new EmptyBorder(0, 0, 0, 0));
		//setUndecorated(true);
		//this.setShape(Shape shape);
		
	}
	
	
}
