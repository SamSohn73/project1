package ga.smartpostit.spiClient;


import java.util.Vector;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import org.apache.log4j.Logger;
import java.awt.event.WindowFocusListener;


/**
 * SmartPostIt 프레임
 * 
 * @author		sam
 * @Version		0.1
 */
public class SPIFrame extends JFrame	// with minimize, maximize button
//class SPIFrame extends JDialog	// Without minimize, maximize button
{
	private static final long serialVersionUID = 1L;
	private static final transient Logger log = Logger.getLogger(SPIFrame.class);
	
	private JPanel panel;

	public JPanel getPanel()
	{
		return panel;
	}
	public void setPanel(JPanel panel)
	{
		this.panel = panel;
	}
	
	
	/**
	 * Create the frame.
	 */
	public SPIFrame(SPIFactory factory, Vector<SPIDocument> spiDocs, SPIDocument spiDoc, Thread spiClientFileThread, int x, int y)
	{

		//super();
		
		//SyntheticaPlainLookAndFeel LookAndFeel is beautiful but you can't use Korean Language with it
		/*try {UIManager.setLookAndFeel(new SyntheticaPlainLookAndFeel());} 
		catch (Exception e) {e.printStackTrace();}*/
		
		/* sea glass L&F looks very unstable.
		try {
			UIManager.setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel");
		} catch (Exception e) {
			log.fatal("SeaGlassLookAndFeel library loading fail.");
			e.printStackTrace();
		}*/
		
		setDefaultLookAndFeelDecorated(true);
		if (x >= 0 && y >= 0)
			setBounds(x, y, 250, 250);
		else
			setBounds(100, 100, 250, 250);
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Program Files\\Java\\PostIt-Sam.png"));
		getRootPane().setBorder(new EmptyBorder(0, 0, 0, 0));
		//getRootPane().setBorder(new LineBorder(new Color(0xEE0000), 0));
		
		//All frame Removed
		//setUndecorated(true);
		
		//Closed All together if you open following line
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// www.javasoft.de
/* 		JRootPane root = getRootPane();
		//getRootPane().putClientProperty("Synthetica.titlePane.enabled", false);
		SyntheticaLookAndFeel.findComponent("RootPane.titlePane.closeButton", root).setVisible(true);
		SyntheticaLookAndFeel.findComponent("RootPane.titlePane.toggleButton", root).setVisible(false);
		SyntheticaLookAndFeel.findComponent("RootPane.titlePane.iconifyButton", root).setVisible(false);
		SyntheticaLookAndFeel.findComponent("RootPane.titlePane.menuButton", root).setVisible(false);*/
		
		
		/**
		 * Open a new PostIt when you double click the frame.
		 */
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {

					spiDoc.getFrame().setResizable(false);
					factory.createSPIDoc(spiDoc.getType(), spiDoc.getFrame().getX()-25, spiDoc.getFrame().getY()+25);

					//Save to file
					((SPIClientFile) spiClientFileThread).setFileSaveFlag(true);
					log.info("File saving flag setted - Create New PostIt Document by mouse double click.");
				}
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				spiDoc.getFrame().setResizable(true);
				spiDoc.getFrame().repaint();
			}
		});
		
		
		/**
		 * Close PostIt. 
		 * Closing a PostIt does not mean that it's removed on the memory.
		 * If there's no post it left, exit the program.
		 */
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				//spiDocs.add
				if (spiDocs.contains(spiDoc)) {
					spiDocs.remove(spiDoc);
					log.info("One Document removed by the user. Doc count = " + spiDocs.size());
					
					//Save to file
					((SPIClientFile) spiClientFileThread).setFileSaveFlag(true);
					log.info("File saving flag setted - One  Document removed.");
				}
				//Finish if there's no Document left
				if (spiDocs.isEmpty()) {
					//QQQQQQQQQQ
					//Need Code to Save File and Network here
					log.info("**************************************************");
					log.info("SPI Client Service Exited.");
					log.info("**************************************************");
					System.exit(0);
				}
			}
		});
		
		/**
		 * 
		 */
		addWindowFocusListener(new WindowFocusListener() {
			public void windowGainedFocus(WindowEvent e) {}
			public void windowLostFocus(WindowEvent e) {
				//Save to file
				((SPIClientFile) spiClientFileThread).setFileSaveFlag(true);
				log.info("WindowsLostFocus() - File saving flag setted.");
			}
		});
	}
	
}
