package ga.smartpostit.spiClient;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;

import javax.swing.JEditorPane;
import javax.swing.text.Document;

import org.apache.log4j.Logger;

import ga.smartpostit.spiData.SPIDatum;
import ga.smartpostit.spiData.SPIType;

/**
 * SmartPostIt 파일 관리 기능 구현을 위한 클래스.
 * 벡터 컬렉션의 SmartPostIt 객체들을 직렬화하여 파일로 저장하거나
 * 파일에서 역직렬화하여 객체로 바꿔주는 기능을 수행.
 * 역직렬화의 경우는 최초 실행 시 한 번만 수행하면 되지만
 * 직렬화의 경우는 객체들 중 메모의 내용이 바뀌는 것이 있을 때마다 수행해주어야 하므로
 * 스레드에서 수행해주는 것이 좋다.
 * 
 * @author sam
 * @version 0.1
 */
class SPIClientFile extends Thread
{
	private final static transient Logger log = Logger.getLogger(SPIClientFile.class);
	
	private Vector<SPIDocument> spiDocs;
	private SPIFactory factory;
	private boolean fileSaveFlag;
	
	
	public boolean isFileSaveFlag()
	{
		return fileSaveFlag;
	}
	public void setFileSaveFlag(boolean fileSaveFlag)
	{
		this.fileSaveFlag = fileSaveFlag;
	}
	
	public SPIFactory getFactory()
	{
		return factory;
	}
	public void setFactory(SPIFactory factory)
	{
		this.factory = factory;
	}

	public SPIClientFile(Vector<SPIDocument> spiDocs)
	{
		this.fileSaveFlag = false;
		this.spiDocs = spiDocs;
		
		init();
	}
	
	/**
	 * 
	 */
	public void run()
	{
		log.info("SPIClientFile Thread Starts.");
		
		//QQQQQQQQQQ Need to make a thread pool or something
		while(true) {
			try {Thread.sleep(5000);} catch (InterruptedException e) {}
			if (this.fileSaveFlag) {
				log.info("QQQQQQQQQQ fileSaveFlag true");
				doSave();
			}
		}
	}

	/**
	 * 
	 */
	private void doSave()
	{
		doFileSerializing();
	}
	
	/**
	 * 
	 */
	private void doLoad()
	{
	
	}
	
	/**
	 * 
	 * 
	 * @return
	 */
	public void init()
	{
		log.info("SPIClientFile Initialization.");
		doLoad();
	}
	

	
	/**
	 * 
	 * @return
	 */
	static String getSaveFilePathByOS()
	{
		String	filePath	=	null;
		
		String osname = (System.getProperty("os.name")).toUpperCase();
		if (osname.contains("WIN"))
			filePath =  System.getenv("APPDATA") + "\\spiData";
		else
			filePath =  System.getProperty("user.home") + "/spiData";
		
		File targetDir = new File(filePath);
		if(!targetDir.exists()) {
			targetDir.mkdirs();
			log.info(filePath + " Directory Created.");
		}
		
		if (osname.contains("WIN"))
			filePath =  System.getenv("APPDATA") + "\\spiData\\savedata.spi";
		else
			filePath =  System.getProperty("user.home") + "/spiData/savedata.spi";
		
		log.debug("file path= " + filePath);
		
		return filePath;
	}
	
	
	/**
	 * 
	 * @param spiDocs
	 */
	public void doFileSerializing()
	{
		FileOutputStream		fos	= null;
		BufferedOutputStream	bos	= null;
		ObjectOutputStream		out	= null;
		
		log.info("SPIClientFile Serialization Start.");
		
		Vector<SPIDatum> spiData;
		spiData = createSPIData(spiDocs); 

		String filePath = getSaveFilePathByOS();
		File file = new File(filePath);
		
		try {
			fos = new FileOutputStream(file);
			bos = new BufferedOutputStream(fos);
			out = new ObjectOutputStream(bos);
			log.debug("QQQQQQQQQQQQQQQQQ 1");
			out.writeObject(spiData); //QQQQQQQQQQ Serialize all together. may not work properly I guess.
			log.debug("QQQQQQQQQQQQQQQQQ 2");
			
			fileSaveFlag = false;
			log.info("Successfully Serialized");
		} catch (Exception e) {
			fileSaveFlag = false;
			log.fatal("Serialization Failed.");
			e.printStackTrace();
		} finally {
			try {
				out.close();
				bos.close();
				fos.close();
			} catch (IOException e) {
				fileSaveFlag = false;
				log.fatal("Serialization stream closing Failed");
				e.printStackTrace();
			}
		}
	}

	
	/**
	 * 
	 * @return
	 */
	public Vector<SPIDocument> doFileDeserializing()
	{
		FileInputStream		fis			= null;
		BufferedInputStream	bis			= null;
		ObjectInputStream	in			= null;

		log.info("SPIClientFile Deserialization Start.");

		String filePath = getSaveFilePathByOS();
		File file = new File(filePath);
		// if not exists, it's first time running. return it
		if (!file.isFile()) {
			log.info("Save file not exists. Maybe first time running Smart Post It.");
			return spiDocs;
		}
		
		try {
			//if exists, deserialize it 
			//if deserialize fail, return with message dialog.
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);
			in = new ObjectInputStream(bis);
			
			//QQQQQQQQQQ Looks very very suspicious
			//QQQQQQQQQQ Probably this would be more complicated
			//@SuppressWarnings("unchecked")
			//QQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQ
			Vector<SPIDocument> tempDocs = new Vector<SPIDocument>();
			//tempDocs = (Vector<SPIDocument>) in.readObject();
			
			while (in.available() <= 0){
				SPIDocument tmpDoc = new SPIDocument();
				
				tmpDoc.setFrame((SPIFrame) in.readObject());
				tmpDoc.setType((SPIType) in.readObject());
				switch (tmpDoc.getType()) {
				case MEMO:
					tmpDoc.setPanel((SPIMemoPanel) in.readObject());
					break;
				case TODO:
					tmpDoc.setPanel((SPIToDoPanel) in.readObject());
					break;
				case FAVORITE:
					tmpDoc.setPanel((SPIFavoritePanel) in.readObject());
					break;
				case GRAPHIC:
					
					break;
				case CALCULATOR:
					
					break;
				case VOICE_RECOGNITION:
					
					break;
				case CHAR_RECOGNITION:
					
					break;
				case CAMERA:
					
					break;
				case CALENDAR:
					
					break;
				case STOPWATCH:
					
					break;
				default:
					break;
				}
				
				spiDocs.add(tmpDoc);
			}
			
			factory.createDeserializedSPIDocs(tempDocs);
			
			fileSaveFlag = false;
			log.info("Successfully Deserialized.");
		} catch (Exception e) {
			fileSaveFlag = false;
			log.fatal("Deserialization Failed");
			e.printStackTrace();
		} finally {
			try {
				fis.close();
				bis.close();
				in.close();
			} catch (IOException e) {
				fileSaveFlag = false;
				log.fatal("Deserialization stream closing Failed");
				e.printStackTrace();
			}
		}

		return spiDocs;
	}
	
	/**
	 * 
	 * @param spiDocs
	 * @return
	 */
	public Vector<SPIDatum> createSPIData(Vector<SPIDocument> spiDocs) {
		Vector<SPIDatum> spiData = new Vector<SPIDatum>();
		
		for (SPIDocument spiDoc: spiDocs) {
			log.debug("QQQQQQQQQQ 1");
			SPIDatum spiDatum = new SPIDatum();
			log.debug("QQQQQQQQQQ 2");
			spiDatum.setX(spiDoc.getFrame().getX());
			log.debug("QQQQQQQQQQ 3 x= " + spiDatum.getX());
			spiDatum.setY(spiDoc.getFrame().getY());
			log.debug("QQQQQQQQQQ 4 y= " + spiDatum.getY());
			spiDatum.setDim(spiDoc.getFrame().getSize());
			log.debug("QQQQQQQQQQ 5 Dim= " +spiDatum.getDim().toString());
			spiDatum.setType(spiDoc.getType());
			log.debug("QQQQQQQQQQ 6 type= " + spiDatum.getType());
			
			switch (spiDatum.getType()) {
			case MEMO:
				spiDatum.setBgColor(((SPIMemoPanel) spiDoc.getPanel()).getEditorPane().getBackground());
				log.debug("QQQQQQQQQQ 7 bgColor = " + spiDatum.getBgColor().toString());
				//QQQQQQQQQQQQQQQQQQQQ
				spiDatum.setSpiPane((Document) ((SPIMemoPanel) spiDoc.getPanel()).getEditorPane().getDocument());
				//log.debug("QQQQQQQQQQ 8 pane= " + spiDatum.getSpiPane().toString());
				
				spiData.add(spiDatum);
				log.debug("QQQQQQQQQQ 9");
				break;
			case TODO:
				
				break;
			case FAVORITE:

				break;
			case GRAPHIC:
				
				break;
			case CALCULATOR:
				
				break;
			case VOICE_RECOGNITION:
				
				break;
			case CHAR_RECOGNITION:
				
				break;
			case CAMERA:
				
				break;
			case CALENDAR:
				
				break;
			case STOPWATCH:
				
				break;
			default:
				break;
			}
		}
		
		return spiData;
	}
}
