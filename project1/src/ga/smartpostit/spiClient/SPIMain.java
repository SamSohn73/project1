package ga.smartpostit.spiClient;


import java.awt.EventQueue;
import java.util.Vector;

import org.apache.log4j.Logger;



/**
 * SmartPostIt Client Main
 * Manage all Post It Document.
 * And Saving / Loading personal post it file on proper times.
 * Also Save and Load personal post it information with the server.
 * You need to add log4j framework for logging
 * And cron4j.jar for ToDo Alarms
 * 
 * @author		sam
 * @Version		0.1
 */
class SPIMain
{
	//Variables
	SPIFactory factory;
	Vector<SPIDocument> spiDocs;
	
	// Logger. use transient when you want to serialize this object 
	//private final transient static Logger logger = Logger.getLogger(SPIMain.class);
	private final static transient Logger log = Logger.getLogger(SPIMain.class);
	
	SPIMain()
	{
		super();
		
		// Create Vector Document List
		spiDocs = new Vector<SPIDocument>();
		
		/*
		//Thread for Network Management (Serialization)
		Runnable spiNet = new SPIClientNet();
		Thread netThread = new Thread(spiNet);
		netThread.start();
		 */

		//Thread for File Management (Serialization)
		//QQQQQQQQQQQQQQQQQQQQQQQQQQQQQ
		Thread spiClientFileThread = new SPIClientFile(spiDocs);
		spiClientFileThread.start();
		log.info("File Service Thread started.");
		
		// Create a Factory
		factory = new SPIFactory(spiDocs, spiClientFileThread);
		((SPIClientFile) spiClientFileThread).setFactory(factory);
		log.info("SPI Factory Created.");
	}
	
	
	public static void main(String[] args)
	{
		//Variables

		
		// Create a Main class
		log.info("**************************************************");
		log.info("SPI Client Main Start.");
		log.info("**************************************************");
		
		//Sea Glass Look & Feel - Looks very unstable
		/*try {
			UIManager.setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel");
		} catch (Exception e) {
			log.fatal("SeaGlassLookAndFeel library loading fail.");
			e.printStackTrace();
		}*/
		
		//Start Main thread
		//QQQQQQQQQQ Need to think over Factory and spiDocs could be in the main() method.
		SPIMain spi = new SPIMain();
		
		
		//Login Window
		
		//Start PostIt Swing thread
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try {
					// Create a PostIt for test - remove first PostIt because the L&F is not the one I want.
					spi.factory.createSPIDoc(SPIType.MEMO);
					spi.factory.createSPIDoc(SPIType.MEMO);
					spi.spiDocs.get(0).getFrame().dispose();
					spi.spiDocs.remove(0);
					log.info("One Document removed by the system. Doc count = " + spi.spiDocs.size());
				} catch (Exception e) {
					log.fatal("EventQueue.invokeLater Thread failed.");
					e.printStackTrace();
				}
			}
		});
		
	}

}


/*//Logger HowTo
logger.fatal("log4j:logger.fatal()");
logger.error("log4j:logger.error()");
logger.warn("log4j:logger.warn()");
logger.info("log4j:logger.info()");
logger.debug("log4j:logger.debug()");
*/
