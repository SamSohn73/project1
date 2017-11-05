package SmartPostItClient;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.apache.log4j.Logger;



/**
 * SmartPostIt Main Class
 * Managing Creation and Deletion of Every Posts. 
 * Also Managing Logging, Saving and Loading Post from the file
 * and Networking under the threads
 * You need log4j framework and cron4j.jar libraries to start.
 * 
 * @author		sam
 * @Version		0.1
 */
class SPIMain
{
	//Variables
	// Logger. use "transient" when you don't want to serialize this object 
	//private final transient static Logger log = Logger.getLogger(SPIMain.class);
	private final static Logger log = Logger.getLogger(SPIMain.class);

	SPIFactory				factory;

	static Vector<SPIDocument>		spiDocs;
	SPIDocument				spiDoc;
	
	
	SPIMain()
	{
		log.info("SPI Client Main Constructor Starts.");
		
		log.info("SPI Factory Creation Starts.");
		try {
			factory = new SPIFactory();
			log.info("SPI Factory Created Successfully.");
		} catch (Exception e) {log.fatal("SPI Factory Creation Failed.");}
		
		log.info("SPI Factory Creation Ends.");
		log.info("SPI Client Main Constructor Ends.");
	}
	
	
	//QQQQQQQQQQ Test Creation
	SPIDocument createADoc()
	{
		//QQQQQQQQQQ Test Start from here 
		log.debug("Start to create a Document.");
		spiDoc = null;
		try {
			log.debug("Request a new Document to Factory.");
			spiDoc = factory.createSPIDoc(SPIType.MEMO);
			log.debug("Create a new Document successfully.");
		} catch (Exception e) {
			log.debug("Fail to Create a new Document.");
		}

		log.debug("Create links for me.");
		SPIFrame ff = spiDoc.getFrame();
		SPIContent pp = spiDoc.getContent();
		
		log.debug("Connecting objects together.");
		ff.setVisible(true);
		ff.setPanel(pp.getPanel());
		ff.setPopup(pp.getPopup());
		ff.setBackground(SPIUtil.YELLOW);
		
		log.debug("Basic Settings for the new document.");
		JPanel pan = ff.getPanel();
		pan.setBackground(SPIUtil.YELLOW);	//PINK
		pan.setBorder(new EmptyBorder(5,5,5,5));
		pan.setLayout(new BorderLayout());
		ff.setContentPane(pan);
		log.debug("Creating a Document Finished Successfully.");
		
		return spiDoc;
	}
	
	
	public static void main(String[] args)
	{
		//Variables
		
		/*//Logger Example
		log.fatal("log4j:log.fatal()");
		log.error("log4j:log.error()");
		log.warn("log4j:log.warn()");
		log.info("log4j:log.info()");
		log.debug("log4j:log.debug()");*/
		
		log.info("********************************************************************************");
		log.info("Starting SPI Client.");
		log.info("********************************************************************************");
		
		SPIMain spiMain = new SPIMain();
		
		
		spiDocs.add(spiMain.createADoc());
		
		
		//Thread for Swing Components
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try {
					//JFramTest1 frame = new JFramTest1();
					//frame.setVisible(true);
					//get the Objects from the files and make them alive or create one new memo Post It 
					//SPIMain spiMain = new SPIMain();
					//spiMain.spiDocs.add(spiMain.createADoc());

					
				} catch (Exception e) {
					log.fatal("SPI Main Thread Exception.");
					e.printStackTrace();
				}
			}
		});
		
		/*//Thread for File Management (Serialization)
		Runnable spiFile = new SPIClientFile();
		Thread fileThread = new Thread(spiFile);
		fileThread.start();
		
		//Thread for Network Management (Serialization)
		Runnable spiNet = new SPIClientNet();
		Thread netThread = new Thread(spiNet);
		netThread.start();*/
		System.exit(0);
	}


}
