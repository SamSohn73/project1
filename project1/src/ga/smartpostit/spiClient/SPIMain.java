package ga.smartpostit.spiClient;


import java.awt.EventQueue;
import java.util.Vector;

import org.apache.log4j.Logger;

import ga.smartpostit.spiData.SPIType;



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
	SPIFactory			factory;
	Thread				spiClientFileThread;
	Vector<SPIDocument>	spiDocs;
	
	// Logger. use transient when you want to serialize this object 
	//private final transient static Logger logger = Logger.getLogger(SPIMain.class);
	private final static transient Logger log = Logger.getLogger(SPIMain.class);
	
	SPIMain()
	{
		super();
		
		// Create Vector Document List
		spiDocs = new Vector<SPIDocument>();
		
		// Create a Factory
		log.info("SPI Factory Service Thread start to load.");
		factory = new SPIFactory(spiDocs);
		log.info("SPI Factory Service Thread started OK.");
		
		/*
		//Thread for Network Management (Serialization)
		Runnable spiNet = new SPIClientNet();
		Thread netThread = new Thread(spiNet);
		netThread.start();
		 */
		//QQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQ need to change the order of factory creation and file thread creation
		//Thread for File Management (Serialization)
		log.info("File Service Thread start to load.");
		Thread spiClientFileThread = new SPIClientFile(spiDocs, factory);
		spiClientFileThread.start();
		factory.setSpiClientFileThread((SPIClientFile) spiClientFileThread);
		log.info("File Service Thread started OK.");
		
		
		
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
					//if (spi.spiClientFileThread != null && spi.spiDocs.isEmpty()) {
						// Create a PostIt for - remove first PostIt because the L&F is not the one I want.
						//QQQQQQQQQQQQQQQQQQQQQQ do following only there's no Doc in the file
						spi.factory.createSPIDoc(SPIType.MEMO, -1, -1);
						spi.factory.createSPIDoc(SPIType.MEMO, -1, -1);
						spi.spiDocs.get(0).getFrame().dispose();
						spi.spiDocs.remove(0);
						log.info("One Document removed by system. Doc count = " + spi.spiDocs.size());
					//}
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
