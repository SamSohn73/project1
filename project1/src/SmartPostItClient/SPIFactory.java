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
	//SPIDocument spi;
	SPIFrame	frame;
	SPIContent	content;
	SPIPopup	popup;
	
	private final Logger logger = Logger.getLogger(this.getClass());
	
	public SPIDocument createSPIDoc(SPIType type)
	{
		SPIDocument spi=new SPIDocument();
		if (type == SPIType.MEMO) {
			frame	= new SPIFrame();
			content	= new SPIMemoView();
			popup	= new SPIMemoPopup();
			
			spi.setType(type);
			spi.setFrame(frame);
			spi.setContent(content);
			spi.setPopup(popup);
		}/*
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
