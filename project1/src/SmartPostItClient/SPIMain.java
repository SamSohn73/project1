package SmartPostItClient;

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
	private final static Logger log = Logger.getLogger(SPIMain.class);
	
	SPIMain()
	{
		super();
		
		// Create Vector Document List
		spiDocs = new Vector<SPIDocument>();
		
		// Create a Factory
		factory = new SPIFactory(spiDocs);
		log.info("SPI Factory Created.");
		
		//File/Network Management
		
	}
	
	
	public static void main(String[] args)
	{
		//Variables
		
		// Create a Main class
		log.info("**************************************************");
		log.info("SPI Client Main Start.");
		log.info("**************************************************");
		SPIMain spi = new SPIMain();
		
		// Create a PostIt for test
		spi.factory.createSPIDoc(SPIType.MEMO);
		/*
		//Thread for File Management (Serialization)
		Runnable spiFile = new SPIClientFile();
		Thread fileThread = new Thread(spiFile);
		fileThread.start();
		
		//Thread for Network Management (Serialization)
		Runnable spiNet = new SPIClientNet();
		Thread netThread = new Thread(spiNet);
		netThread.start();
		*/
		
		//Login Window
		
	}

}


/*//Logger HowTo
logger.fatal("log4j:logger.fatal()");
logger.error("log4j:logger.error()");
logger.warn("log4j:logger.warn()");
logger.info("log4j:logger.info()");
logger.debug("log4j:logger.debug()");
*/
