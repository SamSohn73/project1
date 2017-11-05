package SmartPostItClient;

import org.apache.log4j.Logger;

/**
 * Smart Post It 객체 생성 공장
 * 
 * @author		sam
 * @version		0.1
 */
class SPIFactory
{
	SPIDocument spi;
	SPIFrame	frame;
	SPIContent	content;
	SPIPopup	popup;
	
	private final Logger log = Logger.getLogger(this.getClass());
	
	public SPIDocument createSPIDoc(SPIType type)
	{
		log.info("Start to Create a new SPI Document.");
		spi=new SPIDocument();
		log.debug("A new SPIDocument Object created. spi = " + spi.toString());
		if (type == SPIType.MEMO) {
			log.debug("Document Type MEMO.");
			try {
			frame	= new SPIFrame();
			} catch (Exception e) {
				log.fatal("Fail to create a SPIFrame fram = " + frame);
			}
			log.debug("Frame created successfully. frame = " + frame.toString());
			content	= new SPIMemoView();
			log.debug("Panel creation successfully");
			popup	= new SPIMemoPopup();
			log.debug("Popup created successfully");
			
			spi.setType(type);
			spi.setFrame(frame);
			spi.setContent(content);
			spi.setPopup(popup);
			log.debug("A New SPI Memo Created successfully.");
		}
		/*
			else if (type == SPIType.TODO) {
			spi		= null;
			frame	= new SPIFrame();
			content	= new SPIToDoView();
			popup	= new SPIToDoPopup();
			
			spi.setFrame(frame);
			spi.setType(type);
			spi.setContent(content);
			spi.setPopup(popup);
		}
		
		else if (type == SPIType.FAVORITE) {
			spi = new SPIDocument(SPIType.TODO);
		}
		else if (type == SPIType.GRAPHIC) {
			spi = new SPIDocument(SPIType.TODO);
		}
		else if (type == SPIType.CALCULATOR) {
			spi = new SPIDocument(SPIType.TODO);
		}
		else if (type == SPIType.VOICE_RECOGNITION) {
			spi = new SPIDocument(SPIType.TODO);
		}
		else if (type == SPIType.CHAR_RECOGNITION) {
			spi = new SPIDocument(SPIType.TODO);
		}
		else if (type == SPIType.CAMERA) {
			spi = new SPIDocument(SPIType.TODO);
		}
		else if (type == SPIType.CALENDAR) {
			spi = new SPIDocument(SPIType.TODO);
		}
		else if (type == SPIType.STOPWATCH) {
			spi = new SPIDocument(SPIType.TODO);
		}*/
		
		return spi; 
	}
}
