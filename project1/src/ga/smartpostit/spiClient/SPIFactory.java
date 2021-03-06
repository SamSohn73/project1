package ga.smartpostit.spiClient;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.util.Vector;

import javax.swing.JEditorPane;

import org.apache.log4j.Logger;

import ga.smartpostit.spiData.SPIDatum;
import ga.smartpostit.spiData.SPIType;

/**
 * Smart Post It 객체 생성 공장
 * 
 * @author		sam
 * @version		0.1
 */
class SPIFactory
{
	//QQQQQQQQQQQQQQQQQQQQQQQ make it Singleton
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
	
	public Thread getSpiClientFileThread()
	{
		return spiClientFileThread;
	}
	public void setSpiClientFileThread(Thread spiClientFileThread)
	{
		this.spiClientFileThread = spiClientFileThread;
	}
	
	
	SPIFactory(Vector<SPIDocument> spiDocs)
	{
		this.spiDocs = spiDocs;
	}
	
	/**
	 * 
	 * @param type		SPI Document Type
	 * @param x			Frame Starting position X
	 * @param y			Frame Starting position Y
	 * @param dim		Frame Length and Height
	 * @param bgColor	Frame Background Color
	 */
	public void createSPIDoc(SPIType type, int x, int y)
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
				panel	= new SPIMemoPanel((SPIMemoPopup) popup, spiDocs, spiDoc, null, null);
				log.debug("Panel creation successfully panel = " + panel.toString());
				frame	= new SPIFrame(this, spiDocs, spiDoc, spiClientFileThread, x, y, null, null);
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
		if (type == SPIType.TODO) {

		}
		if (type == SPIType.FAVORITE) {

		}
		if (type == SPIType.GRAPHIC) {

		}
		if (type == SPIType.CALCULATOR) {

		}
		if (type == SPIType.VOICE_RECOGNITION) {

		}
		if (type == SPIType.CHAR_RECOGNITION) {

		}
		if (type == SPIType.CAMERA) {

		}
		if (type == SPIType.CALENDAR) {
			
		}
		if (type == SPIType.STOPWATCH) {

		}
	}
	
	/**
	 * 
	 * @param spiData
	 */
	public void createSPIDocFromFile(Vector<SPIDatum> spiData)
	{
		log.info("Start to create SPI Document from loaded spiData.");
		
		//Check if spiData is null
		//QQQQQQQQQQQQQQQQQQQQQQQQQQQQQ if spiData is null, create basic PostIt, if not, don't.
		if (spiData == null)	{
			log.error("spiData null.");
			return;
		}
		
		for (SPIDatum spiDatum:spiData) {
			log.debug("Start for looping.");
			SPIDocument spiDoc = new SPIDocument();
			SPIType type = spiDatum.getType();
			spiDoc.setType(type);
			if (type == SPIType.MEMO) {
				log.debug("Document Type MEMO.");
				//Create Frame, Panel, Popup Object
				try {
					popup	= new SPIMemoPopup(this, spiDocs, spiDoc, spiClientFileThread);
					log.debug("Popup created successfully popup = " + popup.toString());
					panel	= new SPIMemoPanel((SPIMemoPopup) popup, spiDocs, spiDoc, (JEditorPane) spiDatum.getSpiPane(), spiDatum.getBgColor());
					log.debug("Panel creation successfully ((JEditorPane) spiDatum.getSpiPane()).getText() = " + ((JEditorPane) spiDatum.getSpiPane()).getText());
					//QQQQQQQQQQ Create new PostIt near the requested PostIt position.
					frame	= new SPIFrame(this, spiDocs, spiDoc, spiClientFileThread, spiDatum.getX(), spiDatum.getY(), spiDatum.getDim(), spiDatum.getBgColor());
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
			if (type == SPIType.TODO) {

			}
			if (type == SPIType.FAVORITE) {

			}
			if (type == SPIType.GRAPHIC) {

			}
			if (type == SPIType.CALCULATOR) {

			}
			if (type == SPIType.VOICE_RECOGNITION) {

			}
			if (type == SPIType.CHAR_RECOGNITION) {

			}
			if (type == SPIType.CAMERA) {

			}
			if (type == SPIType.CALENDAR) {
				
			}
			if (type == SPIType.STOPWATCH) {

			}
		}
		log.info("End to create SPI Document from loaded spiData.");
	}
}
