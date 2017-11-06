package SmartPostItClient;

import java.awt.Container;

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
	SPIPanel	panel;
	SPIPopup	popup;
	
	private final Logger log = Logger.getLogger(this.getClass());
	
	public SPIDocument createSPIDoc(SPIType type)
	{
		log.info("Start to Create a new SPI Document.");
		spi=new SPIDocument();
		
		if (type == SPIType.MEMO) {
			log.debug("Document Type MEMO.");
			popup	= new SPIMemoPopup();
			//log.debug("Popup created successfully popup = " + popup.toString());
			panel	= new SPIMemoPanel((SPIMemoPopup) popup);
			//log.debug("Panel creation successfully panel = " + panel.toString());
			frame	= new SPIFrame();
			//log.debug("Frame created successfully. frame = " + frame.toString());
			
			spi.setType(type);
			spi.setPopup(popup);
			spi.setPanel(panel);
			spi.setFrame(frame);

			((SPIMemoPanel) panel).setPopup((SPIMemoPopup) spi.getPopup());
			//log.debug("popup = " + ((SPIMemoPanel) spi.getPanel()).getPopup());
			
			spi.getFrame().setContentPane((Container) spi.getPanel());
			//log.debug("setContentPane = " + spi.getFrame().getContentPane());
			spi.getFrame().setVisible(true);
			
			log.info("A New SPI Memo Created successfully.");
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
