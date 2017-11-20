package SmartPostItClient;

import java.awt.Container;
import java.util.Vector;

import org.apache.log4j.Logger;

/**
 * Smart Post It 객체 생성 공장
 * 
 * @author		sam
 * @version		0.1
 */
class SPIFactory
{
	private final transient Logger log = Logger.getLogger(this.getClass());

	private transient Thread spiClientFileThread;
	private Vector<SPIDocument> spiDocs;
	private SPIPopup popup;
	private SPIPanel panel;
	private SPIFrame frame;

	Vector<SPIDocument> getSpiDocs()
	{
		return spiDocs;
	}
	void setSpiDocs(Vector<SPIDocument> spiDocs)
	{
		this.spiDocs = spiDocs;
	}

	SPIPopup getPopup()
	{
		return popup;
	}
	void setPopup(SPIPopup popup)
	{
		this.popup = popup;
	}

	SPIPanel getPanel()
	{
		return panel;
	}
	void setPanel(SPIPanel panel)
	{
		this.panel = panel;
	}
	
	SPIFactory(Vector<SPIDocument> spiDocs, Thread spiClientFileThread)
	{
		this.spiDocs = spiDocs;
		this.spiClientFileThread = spiClientFileThread;
	}
	
	/**
	 * Create real SPIDocument here
	 * 
	 * @param		type		type of the Post It to create 
	 * @return					SPIDocument Object  
	 */
	public void createSPIDoc(SPIType type)
	{
		log.info("Start to Create a new SPI Document.");

		SPIDocument spiDoc = new SPIDocument();		
		//QQQQQQQQQQ Create new PostIt near the requested PostIt.
		if (type == SPIType.MEMO) {
			log.debug("Document Type MEMO.");
			//Create Frame, Panel, Popup Object
			try {
				popup	= new SPIMemoPopup(this, spiDocs, spiDoc, spiClientFileThread);
				log.debug("Popup created successfully popup = " + popup.toString());
				panel	= new SPIMemoPanel((SPIMemoPopup) popup, spiDocs, spiDoc);
				log.debug("Panel creation successfully panel = " + panel.toString());
				frame	= new SPIFrame(this, spiDocs, spiDoc, spiClientFileThread);
				log.debug("Frame created successfully. frame = " + frame.toString());
			} catch (Exception e){
				log.fatal("Fail to Create Swing Object");
			}
			
			// Connect them to spiDoc object
			spiDoc.setType(type);
			spiDoc.setPopup(popup);
			spiDoc.setPanel(panel);
			spiDoc.setFrame(frame);
			
			// Connect popup object to panel object
			((SPIMemoPanel) panel).setPopup((SPIMemoPopup) spiDoc.getPopup());
			// Connect panel object to frame object
			spiDoc.getFrame().setContentPane((Container) spiDoc.getPanel());
			// Make it Visible
			spiDoc.getFrame().setVisible(true);	
			
			//Add it to Vector List
			boolean res;	res = spiDocs.add(spiDoc);
			if (!res)	log.error("Fail to add Docs to Vector List");

			log.info("A New SPI Memo Created successfully. Doc count = " + spiDocs.size());
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

	}
}
