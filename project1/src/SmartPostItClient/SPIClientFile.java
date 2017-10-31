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
 * SmartPostIt 네트워크 파일 관리 기능 구현을 위한 클래스
 * 
 * @author sam
 * @version 0.1
 */
class SPIClientFile implements Runnable
{
	private final static Logger logger = Logger.getLogger(SPIClientFile.class);
	
	public void run()
	{
		//conductFileSerializing(spiDocs);
	}

	public static void conductFileSerializing(Vector<SPIDocument> spiDocs)
	{
		FileOutputStream		fos = null;
		BufferedOutputStream	bos = null;
		ObjectOutputStream		out = null;
		
		try {
			fos = new FileOutputStream("C:/serial.obj");
			bos = new BufferedOutputStream(fos);
			out = new ObjectOutputStream(bos);

			out.writeObject(spiDocs);

		} catch (Exception e) {
			logger.info("Serialization Failed");
			e.printStackTrace();
		} finally {
			try {
				out.close();
				bos.close();
				fos.close();
			} catch (IOException e) {
				logger.info("Serialization stream closing Failed");
				e.printStackTrace();
			}

			logger.info("Successfully Serialized");
		}
	}

	private static Vector<SPIDocument> conductFileDeserializing()
	{
		FileInputStream		fis = null;
		BufferedInputStream	bis = null;
		ObjectInputStream	in = null;
		Vector<SPIDocument> spiDocs = null;
		
		try {
			fis = new FileInputStream("C:/serial.obj");
			bis = new BufferedInputStream(fis);
			in = new ObjectInputStream(bis);

			//@SuppressWarnings("unchecked")
			spiDocs = (Vector<SPIDocument>) in.readObject();

			for (SPIDocument spiDoc: spiDocs)
				logger.debug(spiDoc.toString());
			
		} catch (Exception e) {
			logger.info("Deerialization Failed");
			e.printStackTrace();
		} finally {
			try {
				in.close();
				bis.close();
				fis.close();
			} catch (IOException e) {
				logger.info("Deserialization stream closing Failed");
				e.printStackTrace();
			}
		}
		logger.info("Successfully Deserialized");
		return spiDocs;
	}
}
