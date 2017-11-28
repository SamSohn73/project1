package ga.smartpostit.spiClient;

import java.io.Serializable;

import org.apache.log4j.Logger;

import ga.smartpostit.spiData.SPIType;


/**
 * SmartPostIt Object Class. One Post It Document.
 * 
 * @author		sam
 * @Version		0.1
 *
 */
public class SPIDocument implements Serializable
{
	/**
	 * @serial		SmartPostIt 객체 
	 * @serialField	frame	SPIFrame	PostIt Frame
	 * @serialField	type	SPIType		PostIt Type, enum type
	 * @serialField	content	SPIContent	PostIt Content. Interface. Will be Strategy Pattern.
	 */
	private static final long serialVersionUID = 1L;
	private final transient Logger log = Logger.getLogger(this.getClass());
	//PostIt Frames
	private				SPIFrame	frame;
	private				SPIType		type;
	private				SPIPanel	panel;
	private transient	SPIPopup	popup;
	
	
	public SPIFrame getFrame()
	{
		return frame;
	}
	public void setFrame(SPIFrame frame)
	{
		this.frame = frame;
	}
	
	public SPIType getType()
	{
		return type;
	}
	public void setType(SPIType type)
	{
		this.type = type;
	}
	
	public SPIPanel getPanel()
	{
		return panel;
	}
	public void setPanel(SPIPanel panel)
	{
		this.panel = panel;
	}
	
	public SPIPopup getPopup()
	{
		return popup;
	}
	public void setPopup(SPIPopup popup)
	{
		this.popup = popup;
	}
	
}
