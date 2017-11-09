package SmartPostItClient;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.Insets;
import java.awt.Toolkit;
import java.util.Vector;

import org.apache.log4j.Logger;

import de.javasoft.plaf.synthetica.SyntheticaLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaStandardLookAndFeel;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;



/**
 * SmartPostIt 프레임
 * 
 * @author		sam
 * @Version		0.1
 */
//class SPIFrame extends JFrame	// with minimize, maximize button
class SPIFrame extends JDialog	// Without minimize, maximize button
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
		setBounds(100, 100, 250, 250);
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Program Files\\Java\\PostIt-Sam.png"));
		//Close All together if you open following line
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//How to remove the left, right side frame on windows 7 ???
		//this.getRootPane().setBorder(new EmptyBorder(0, 0, 0, 0));
		//setUndecorated(true);
		//this.setShape(Shape shape);
		
		/*setDefaultLookAndFeelDecorated(true);*/
		
		/*setUndecorated(true);
		getRootPane().setWindowDecorationStyle(JRootPane.FRAME);*/
		
		//QQQQQQQQQQ Hope this will do the work for me
		//getRootPane().setBorder(new LineBorder(new Color(0xEE0000), 0));
		
		//getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		
		
		
		// www.javasoft.de
		//getRootPane().putClientProperty("Synthetica.titlePane.enabled", false);
		  
		/* 		// www.javasoft.de
 		JRootPane root = getRootPane();
		SyntheticaLookAndFeel.findComponent("RootPane.titlePane.closeButton", root).setVisible(true);
		SyntheticaLookAndFeel.findComponent("RootPane.titlePane.toggleButton", root).setVisible(false);
		SyntheticaLookAndFeel.findComponent("RootPane.titlePane.iconifyButton", root).setVisible(false);
		SyntheticaLookAndFeel.findComponent("RootPane.titlePane.menuButton", root).setVisible(false);
*/
		
		
		//QQQQQQQQQQ
		// make control pane invisible when you double click Frame Bar
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 1) {
					if (spiDoc.getFrame().getContentPane().isVisible()) {
						log.debug("QQQQQQQQQQ fffff");
						spiDoc.getFrame().getContentPane().setVisible(false);
					} else {
						log.debug("QQQQQQQQQQ ttttt");
						spiDoc.getFrame().getContentPane().setVisible(true);
					}
				}
			}
		});
		
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
