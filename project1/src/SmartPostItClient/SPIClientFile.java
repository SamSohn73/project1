package SmartPostItClient;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;

import org.apache.log4j.Logger;

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
	
	Vector<SPIDocument> spiDocs;
	private boolean fileSaveFlag;
	
	
	public boolean isFileSaveFlag()
	{
		return fileSaveFlag;
	}
	public void setFileSaveFlag(boolean fileSaveFlag)
	{
		this.fileSaveFlag = fileSaveFlag;
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
		
		//QQQQQQQQQQ
		while(true) {
			try {Thread.sleep(1000);} catch (InterruptedException e) {}
			if (this.fileSaveFlag) {
				log.info("QQQQQQQQQQ fileSaveFlag true");
				doFileSerializing();
			}
		}
	}

	/**
	 * 
	 * 
	 * @return
	 */
	public void init()
	{
		log.info("SPIClientFile Initialization.");
		doFileDeserializing();
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
		
		//check save file exist	
		String filePath = getSaveFilePathByOS();
		File file = new File(filePath);
		
		try {
			fos = new FileOutputStream(file);
			bos = new BufferedOutputStream(fos);
			out = new ObjectOutputStream(bos);

			//QQQQQQQQQQ Probably this would be more complicated
			out.writeObject(spiDocs);
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
		//check save file exist	
		String filePath = getSaveFilePathByOS();
		File file = new File(filePath);
		// if not exists, it's first time running. return it
		if (!file.isFile()) {
			log.info("Save file not exists.");
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
			spiDocs = (Vector<SPIDocument>) in.readObject();

			//QQQQQQQQQQ Just for checking - will be removed
			for (SPIDocument spiDoc: spiDocs)	log.debug(spiDoc.toString());
			
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
			filePath =  System.getenv("APPDATA") + "\\spiData\\savedata.dat";
		else
			filePath =  System.getProperty("user.home") + "/spiData/savedata.dat";
		
		log.debug("file path= " + filePath);
		
		return filePath;
	}
}
