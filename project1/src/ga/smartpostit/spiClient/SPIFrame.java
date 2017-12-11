package ga.smartpostit.spiClient;


import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.plaf.synth.ColorType;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import org.apache.log4j.Logger;

import ga.smartpostit.spiData.SPIUtil;

import java.awt.event.WindowFocusListener;
import java.awt.Color;


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
	public SPIFrame(SPIFactory factory, Vector<SPIDocument> spiDocs, SPIDocument spiDoc, Thread spiClientFileThread, int x, int y, Dimension dim)
	{
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
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
		
		//BasicFrameTitlePane titlePane = (BasicInternalFrameTitlePane) ((BasicInternalFrameUI) this.getRootPane().getUI()).getNorthPane();
		
		setDefaultLookAndFeelDecorated(true);
		if (x >= 0 && y >= 0)	setBounds(x, y, 250, 250);
		else					setBounds(100, 100, 250, 250);
		if (dim != null)		this.setPreferredSize(dim);
		
		
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Program Files\\Java\\PostIt-Sam.png"));
		//getRootPane().setBorder(new EmptyBorder(3, 3, 3, 3));
		getRootPane().setBorder(new LineBorder(SPIUtil.YELLOW, 3));
		
		
		//getRootPane().setBorder(new LineBorder(new Color(0xEE0000), 0));
		//All frame Removed
		//setUndecorated(true);
		//getRootPane().setBackground(SPIUtil.YELLOW);
		//Closed All together if you open following line
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// www.javasoft.de
		/*
 		JRootPane root = getRootPane();
		//getRootPane().putClientProperty("Synthetica.titlePane.enabled", false);
		SyntheticaLookAndFeel.findComponent("RootPane.titlePane.closeButton", root).setVisible(true);
		SyntheticaLookAndFeel.findComponent("RootPane.titlePane.toggleButton", root).setVisible(false);
		SyntheticaLookAndFeel.findComponent("RootPane.titlePane.iconifyButton", root).setVisible(false);
		SyntheticaLookAndFeel.findComponent("RootPane.titlePane.menuButton", root).setVisible(false);*/
		
		/*
		UIManager.getLookAndFeelDefaults().put("Desktop.background", Color.RED);
		UIManager.getLookAndFeelDefaults().put("InternalFrame.background", Color.RED);
		UIManager.getLookAndFeelDefaults().put("InternalFrame.inactiveTitleBackground", Color.RED);
		UIManager.getLookAndFeelDefaults().put("InternalFrame.paletteBackground", Color.RED);
		UIManager.getLookAndFeelDefaults().put("Panel.background", Color.RED);
		UIManager.getLookAndFeelDefaults().put("Viewport.background", Color.RED);
		UIManager.getLookAndFeelDefaults().put("control", Color.RED);
		UIManager.getLookAndFeelDefaults().put("desktop", Color.RED);
		UIManager.getLookAndFeelDefaults().put("window", Color.RED);
		UIManager.getLookAndFeelDefaults().put("InternalFrame.activeTitleBackground", Color.RED);
		UIManager.getLookAndFeelDefaults().put("InternalFrame.inactiveTitleBackground", Color.RED);
		UIManager.getLookAndFeelDefaults().put("TitledBorder.titleColor", Color.RED);*/
		
		
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
				int dialogButton = JOptionPane.showConfirmDialog (null, 
																"Would You Like to Delete this PostIt?",
																"Warning",
																JOptionPane.YES_NO_OPTION);
				if(dialogButton == JOptionPane.YES_OPTION) {
					//spiDocs.remove
					if (spiDocs.contains(spiDoc)) {
						spiDocs.remove(spiDoc);
						log.info("One Document removed by the user. Doc count = " + spiDocs.size());
						
						//Save to file
						((SPIClientFile) spiClientFileThread).setFileSaveFlag(true);
						log.info("File saving flag setted - One  Document removed.");
					}
					//Finish if there's no Document left
					if (spiDocs.isEmpty()) {
						log.info("**************************************************");
						log.info("SPI Client Service Exited.");
						log.info("**************************************************");
						System.exit(0);
					}
					dispose();
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
	
	/**
	 * Create the frame.
	 */
/*	public SPIFrame(SPIFactory factory, Vector<SPIDocument> spiDocs, SPIDocument spiDoc, Thread spiClientFileThread, int x, int y, Dimension dim)
	{	

	}*/

}
