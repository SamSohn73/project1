package SmartPostItClient;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
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
class SPIClientFile implements Runnable
{
	private final static transient Logger log = Logger.getLogger(SPIClientFile.class);
	
	public void run()
	{
		// ToDo 스레드 구현 필요.
		//init(spiDocs);
	}

	/**
	 * 
	 * @param spiDocs
	 */
	public static void doFileSerializing(Vector<SPIDocument> spiDocs)
	{
		FileOutputStream		fos			= null;
		BufferedOutputStream	bos			= null;
		ObjectOutputStream		out			= null;
		
		try {
			fos = new FileOutputStream(getSaveFilePath());
			bos = new BufferedOutputStream(fos);
			out = new ObjectOutputStream(bos);

			out.writeObject(spiDocs);

		} catch (Exception e) {
			log.fatal("Serialization Failed");
			e.printStackTrace();
		} finally {
			try {
				out.close();
				bos.close();
				fos.close();
			} catch (IOException e) {
				log.fatal("Serialization stream closing Failed");
				e.printStackTrace();
			}

			log.info("Successfully Serialized");
		}
	}

	
	/**
	 * 
	 * @return
	 */
			
	private static Vector<SPIDocument> doFileDeserializing()
	{
		FileInputStream		fis			= null;
		BufferedInputStream	bis			= null;
		ObjectInputStream	in			= null;
		Vector<SPIDocument> spiDocs		= null;
		String				filePath	= null;
		
		try {
			String osname = (System.getProperty("os.name")).toUpperCase();
			if (osname.contains("WIN"))
				filePath =  System.getenv("APPDATA") + "\\spiData\\savedata.dat";
			else
				filePath =  System.getProperty("user.home") + "\\spiData\\savedata.dat";
			
			fis = new FileInputStream(getSaveFilePath());
			bis = new BufferedInputStream(fis);
			in = new ObjectInputStream(bis);

			//QQQQQQQQQQ Looks very very suspicious
			//@SuppressWarnings("unchecked")
			spiDocs = (Vector<SPIDocument>) in.readObject();

			for (SPIDocument spiDoc: spiDocs)
				log.debug(spiDoc.toString());
			
		} catch (Exception e) {
			log.fatal("Deerialization Failed");
			e.printStackTrace();
		} finally {
			try {
				in.close();
				bis.close();
				fis.close();
			} catch (IOException e) {
				log.fatal("Deserialization stream closing Failed");
				e.printStackTrace();
			}
		}
		log.info("Successfully Deserialized");
		return spiDocs;
	}
	
	static String getSaveFilePath()
	{
		String	filePath	=	null;
		
		String osname = (System.getProperty("os.name")).toUpperCase();
		if (osname.contains("WIN"))
			filePath =  System.getenv("APPDATA") + "\\spiData\\savedata.dat";
		else
			filePath =  System.getProperty("user.home") + "\\spiData\\savedata.dat";
		
		return filePath;
	}
}
