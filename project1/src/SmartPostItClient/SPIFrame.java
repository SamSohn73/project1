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
	
	//private Vector<SPIDocument> spiDocs;
	//private SPIDocument spiDoc;	
	private JPanel panel;

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
	public SPIFrame(Vector<SPIDocument> spiDocs, SPIDocument spiDoc)
	{
		super();
		
		//this.spiDocs = spiDocs;
		//this.spiDoc = spiDoc;
		
		setBounds(100, 100, 250, 250);
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Program Files\\Java\\PostIt-Sam.png"));
		//Close All together if you open following line
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//How to remove the side frame on windows 7
		//this.getRootPane().setBorder(new EmptyBorder(0, 0, 0, 0));
		//setUndecorated(true);
		//this.setShape(Shape shape);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				//spiDocs.add
				if (spiDocs.contains(spiDoc)) {
					//QQQQQQQQQQ
					//Need Save to File and Network Code here
					
					spiDocs.remove(spiDoc);
					log.info("One Document removed by the user.");
				}
				//Finish if there's no Document left
				if (spiDocs.isEmpty()) {
					//QQQQQQQQQQ
					//Need Save to File and Network Code here
					
					log.info("**************************************************");
					log.info("SPI Client Service Exited.");
					log.info("**************************************************");
					System.exit(0);
				}
			}
		});
	}
	
	
}
