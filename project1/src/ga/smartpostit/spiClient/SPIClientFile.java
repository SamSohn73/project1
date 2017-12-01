package ga.smartpostit.spiClient;

import java.awt.Color;
import java.awt.Dimension;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.util.Vector;

import javax.swing.JEditorPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Document;
import javax.swing.text.EditorKit;
import javax.swing.text.rtf.RTFEditorKit;

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
	
	private Vector<SPIDocument>	spiDocs;
	private Vector<SPIDatum>	spiData;
	private SPIFactory			factory;
	private boolean				serviceStartedFlag	= true;
	private boolean				fileSaveFlag		= false;
	
	public boolean isFileSaveFlag()
	{
		return fileSaveFlag;
	}
	public void setFileSaveFlag(boolean fileSaveFlag)
	{
		this.fileSaveFlag = fileSaveFlag;
	}
	
	public boolean isServiceStartedFlag()
	{
		return serviceStartedFlag;
	}
	public void setServiceStartedFlag(boolean serviceStartedFlag)
	{
		this.serviceStartedFlag = serviceStartedFlag;
	}
	
	public SPIFactory getFactory()
	{
		return factory;
	}
	public void setFactory(SPIFactory factory)
	{
		this.factory = factory;
	}

	public SPIClientFile(Vector<SPIDocument> spiDocs, SPIFactory factory)
	{
		log.info("SPIClientFile Thread Initialization start.");
		
		this.spiDocs = spiDocs;
		this.factory = factory;
		
		spiData = new Vector<SPIDatum>();
		log.info("SPIClientFile Thread Initialization End.");
	}
	
	/**
	 * 
	 */
	public void run()
	{
		log.info("SPIClientFile Thread Starts running.");
		
		//QQQQQQQQQQ Need to make a thread pool or something
		while(true) {
			//First time loading
			if (this.serviceStartedFlag && this.factory != null) {
				log.info("File loading - serviceStartedFlag true. Start loading data from file.");
				doLoad();
				this.serviceStartedFlag = false;
				log.info("File loading - serviceStartedFlag false. End loading data from file.");
			}
			// Saving to a file
			try {Thread.sleep(1000);} catch (InterruptedException e) {}
			if (this.fileSaveFlag) {
				log.info("File saving - fileSaveFlag true. Start saving data to file.");
				doSave();
				this.fileSaveFlag = false;
				log.info("File saving - fileSaveFlag false. End saving data to file.");
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
		log.info("spiData Loading start.");
		doFileDeserializing();
		if (spiData != null)		factory.createSPIDocFromFile(spiData);
		else						log.error("spiData null.");
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
			//out.writeObject(spiData); //QQQQQQQQQQ Serialize all together. may not work properly I guess.

			for (SPIDatum spiDatum: spiData) {
				out.writeObject(spiDatum.getX());
				out.writeObject(spiDatum.getY());
				out.writeObject(spiDatum.getDim());
				out.writeObject(spiDatum.getBgColor());
				out.writeObject(spiDatum.getType());
				log.debug("QQQQQQQQQQQQQQQQ doFileSerializing 111 spiDatum.getType()=" + spiDatum.getType());
				switch (spiDatum.getType()) {
				case MEMO:
					log.debug("QQQQQQQQQQQQQQQQ doFileSerializing 222 spiDatum.getType()=" + spiDatum.getType());
					try {
						String editorPane = (String) spiDatum.getSpiPane();
						
						log.debug("QQQQQQQQQQQQQQQQ doFileSerializing 333 editorPane=" + editorPane);
						log.debug("QQQQQQQQQQQQQQQQ doFileSerializing 444 (Document) spiDatum.getSpiPane()=" + spiDatum.getSpiPane());
						out.writeObject(editorPane);
						log.debug("QQQQQQQQQQQQQQQQ doFileSerializing 555 (Document) spiDatum.getSpiPane()=" + spiDatum.getSpiPane());
						//((JEditorPane) spiDatum.getSpiPane()).getEditorKit().write(out, doc, 0, doc.getLength());
					} catch (Exception e) {
						log.fatal("Fail to save the Memo PostIt.");
						e.printStackTrace();
					}
					
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
	public void doFileDeserializing()
	{
		FileInputStream		fis			= null;
		BufferedInputStream	bis			= null;
		ObjectInputStream	in			= null;
		
		log.info("SPIClientFile Deserialization Start.");

		String filePath = getSaveFilePathByOS();
		File file = new File(filePath);
		// if not exists, it's first time running. return it
		if (!file.isFile()) {
			log.info("Save file not exists. Maybe It's your first time running SmartPostIt.");
			return;
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
			//tempDocs = (Vector<SPIDocument>) in.readObject();
			log.debug("QQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQ " + fis.available());
			while (fis.available() > 0) {
				SPIDatum spiDatum = new SPIDatum();
				if (fis.available() <= 0) {log.debug("ZZZZZZZZZZZZZZZZZZZZZZZZ 111 " + fis.available()); break;}
				spiDatum.setX((int) in.readObject());
				log.debug("spiDatum.getX()= " + spiDatum.getX());
				if (fis.available() <= 0) {log.debug("ZZZZZZZZZZZZZZZZZZZZZZZZ 222 " + fis.available()); break;}
				spiDatum.setY((int) in.readObject());
				log.debug("spiDatum.getY()= " + spiDatum.getY());
				if (fis.available() <= 0) {log.debug("ZZZZZZZZZZZZZZZZZZZZZZZZ 333 " + fis.available()); break;}
				spiDatum.setDim((Dimension) in.readObject());
				log.debug("spiDatum.getDim()= " + spiDatum.getDim());
				if (fis.available() <= 0) {log.debug("ZZZZZZZZZZZZZZZZZZZZZZZZ444 " + fis.available()); break;}
				spiDatum.setBgColor((Color) in.readObject());
				log.debug("spiDatum.getBgColor()= " + spiDatum.getBgColor());
				if (fis.available() <= 0) {log.debug("ZZZZZZZZZZZZZZZZZZZZZZZZ 555 " + fis.available()); break;}
				spiDatum.setType((SPIType) in.readObject());
				log.debug("spiDatum.getType()= " + spiDatum.getType());

				if (fis.available() <= 0) {log.debug("ZZZZZZZZZZZZZZZZZZZZZZZZ 666" + fis.available()); break;}
				switch (spiDatum.getType()) {
				case MEMO:
					//Document doc = new DefaultStyledDocument();					
					//JEditorPane tmpEP = new JEditorPane();
					//tmpEP.getEditorKit().read(in, doc, 0);
					log.debug("QQQQQQQQQQQQQQQQQQQQQQ in the MEMO switch");
					spiDatum.setSpiPane((String) in.readObject());
					log.debug("spiDatum.getSpiPane()= " + spiDatum.getSpiPane());
					//log.debug("spiDatum.getSpiPane()= " + ((Document) spiDatum.getSpiPane()).getText(0, ((Document) spiDatum.getSpiPane()).getLength()));
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
				
				spiData.add(spiDatum);
			}
			log.info("Successfully Deserialized. " + fis.available());
		} catch (Exception e) {
			log.fatal("Deserialization Failed");
			e.printStackTrace();
		} finally {
			try {
				fis.close();
				bis.close();
				in.close();
			} catch (IOException e) {
				log.fatal("Deserialization stream closing Failed");
				e.printStackTrace();
			}
		}

		return;
	}
	
	/**
	 * 
	 * @param spiDocs
	 * @return
	 */
	public Vector<SPIDatum> createSPIData(Vector<SPIDocument> spiDocs) {
		spiData = new Vector<SPIDatum>();
		
		for (SPIDocument spiDoc: spiDocs) {
			SPIDatum spiDatum = new SPIDatum();
			spiDatum.setX(spiDoc.getFrame().getX());
			spiDatum.setY(spiDoc.getFrame().getY());
			spiDatum.setDim(spiDoc.getFrame().getSize());
			spiDatum.setType(spiDoc.getType());
			
			switch (spiDatum.getType()) {
			case MEMO:
				JEditorPane editorPane = ((SPIMemoPanel) spiDoc.getPanel()).getEditorPane();
				spiDatum.setBgColor(editorPane.getBackground());
				//EditorKit editorKit = editorPane.getEditorKit();
				//DefaultStyledDocument tempDoc = (DefaultStyledDocument) editorKit.clone();
				/*RTFEditorKit rtfParser = new RTFEditorKit();
				Document document = rtfParser.createDefaultDocument();
				rtfParser.read(new ByteArrayInputStream(rtfBytes), document, 0);
				String text = document.getText(0, document.getLength());*/

				
				/* You get ""AWT-EventQueue-0" java.lang.NullPointerException
				 if you try to get the GUI Component in the run() method.*/
				log.debug("QQQQQQQQQQQQQQQ createSPIData 111 SpiPane= " + editorPane);
				try {
					spiDatum.setSpiPane(editorPane.getDocument().getText(0, editorPane.getDocument().getLength()));
				} catch (BadLocationException e) {
					log.error("fail to createSPIData");
					e.printStackTrace();
				}
				//spiDatum.setSpiPane(tempDoc);
				log.debug("QQQQQQQQQQQQQQQ createSPIData 222 SpiPane= " + editorPane);

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
			spiData.add(spiDatum);
		}
		
		return spiData;
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
}
