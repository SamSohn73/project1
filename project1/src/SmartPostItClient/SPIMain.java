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
	}
	
	
	public static void main(String[] args)
	{
		//Variables
		
		/*//Logger HowTo
		logger.fatal("log4j:logger.fatal()");
		logger.error("log4j:logger.error()");
		logger.warn("log4j:logger.warn()");
		logger.info("log4j:logger.info()");
		logger.debug("log4j:logger.debug()");*/
		
		log.info("SPI Client Main Start.");
		SPIMain spi = new SPIMain();
		// Create a Factory
		spi.factory = new SPIFactory();
		log.info("SPI Factory Created.");
		
		// Create Vector
		spi.spiDocs = new Vector<SPIDocument>();
		
		// 사용자 파일이 없으면 하나의 기본 문서를 열고
		// 있으면 사용자 파일을 읽어 객체화
		
		// PostIt 문서의 추가/삭제
		
		// Create a Doc for Test
		SPIDocument spiDoc = null;
		spiDoc = spi.factory.createSPIDoc(SPIType.MEMO);
		spi.spiDocs.add(spiDoc);

		
		//Thread for Swing Components
		/*EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try {
					//JFramTest1 frame = new JFramTest1();
					//frame.setVisible(true);
					//get the Objects from the files and make them alive or create one new memo Post It 
					
					
				} catch (Exception e) {
					logger.fatal("Thread Exception");
					e.printStackTrace();
				}
			}
		});
		
		//Thread for File Management (Serialization)
		Runnable spiFile = new SPIClientFile();
		Thread fileThread = new Thread(spiFile);
		fileThread.start();
		
		//Thread for Network Management (Serialization)
		Runnable spiNet = new SPIClientNet();
		Thread netThread = new Thread(spiNet);
		netThread.start();*/
		
	}

}
