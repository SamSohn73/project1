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
	SPIPopup popup;
	SPIPanel panel;
	SPIFrame frame;
	
	private final Logger log = Logger.getLogger(this.getClass());
	
	
	/**
	 * Create real SPIDocument here
	 * 
	 * @param		type		type of the Post It to create 
	 * @return					SPIDocument Object  
	 */
	public SPIDocument createSPIDoc(SPIType type)
	{
		log.info("Start to Create a new SPI Document.");
		// I still can't understand why this is required.
		// I can't assign created frame, panel, popup object to SPIDocument without this.
		spi = new SPIDocument();		
		if (spi == null)	log.fatal("fail to get SPIDocument Object");
		
		if (type == SPIType.MEMO) {
			log.debug("Document Type MEMO.");
			//Create Frame, Panel, Popup Object
			try {
				popup	= new SPIMemoPopup();
				//log.debug("Popup created successfully popup = " + popup.toString());
				panel	= new SPIMemoPanel((SPIMemoPopup) popup);
				//log.debug("Panel creation successfully panel = " + panel.toString());
				frame	= new SPIFrame();
				//log.debug("Frame created successfully. frame = " + frame.toString());
			} catch (Exception e){
				log.fatal("Fail to Create Swing Object");
			}
			
			// Connect them to spiDoc object
			spi.setType(type);
			spi.setPopup(popup);
			spi.setPanel(panel);
			spi.setFrame(frame);
			
			// Connect popup object to panel object
			((SPIMemoPanel) panel).setPopup((SPIMemoPopup) spi.getPopup());
			// Connect panel object to frame object
			spi.getFrame().setContentPane((Container) spi.getPanel());
			// Make it Visible
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
