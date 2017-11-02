package SmartPostItClient;

import java.awt.EventQueue;
import java.util.Vector;
import org.apache.log4j.Logger;



/**
 * SmartPostIt 메인
 * 모든 포스트잇의 생성/삭제를 관리한다.
 * 또한 로깅, 파일관리, 네트워크 기능을 스레드로 구현.
 * 실행을 위해서는 log4j 프레임워크와 cron4j.jar의 추가가 필요하다. 
 * 
 * @author		sam
 * @Version		0.1
 */
class SPIMain
{
	//Variables
	// Logger. use transient when you want to serialize this object 
	//private final transient static Logger logger = Logger.getLogger(SPIMain.class);
	private final static Logger logger = Logger.getLogger(SPIMain.class);
	//Post It Frames
	SPIFactory factory;
	Vector<SPIDocument> spiDocs;
	SPIClientFile spiCliFile;
	
	SPIMain(SPIFactory factory)
	{
		super();
		this.factory = factory;
		
		//spiDocs = SPIClientFile.conductFileDeserializing();
	}
	
	
	
	public static void main(String[] args)
	{
		//Variables
		
		/*//Logger Test
		logger.fatal("log4j:logger.fatal()");
		logger.error("log4j:logger.error()");
		logger.warn("log4j:logger.warn()");
		logger.info("log4j:logger.info()");
		logger.debug("log4j:logger.debug()");*/
		
		logger.info("SPI Client Main Start.");
		
		SPIFactory factory = new SPIFactory();
		logger.info("SPI Factory Created.");
		
		SPIDocument a = factory.createSPIDoc(SPIType.MEMO);
		a.getFrame().setVisible(true);
		
		/*QQQQQQQQQQ Start from here
		/*JPanel cp = (SPIMemoView)a.getContent()       ..getContentPane();
		contentPane = new SPIMemoView();
		contentPane.setBackground(SPIUtil.YELLOW);	//YELLOW
		contentPane.setBorder(new EmptyBorder(5,5,5,5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		*/
		
		
		
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
