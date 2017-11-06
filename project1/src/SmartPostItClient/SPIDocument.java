package SmartPostItClient;

import java.io.Serializable;

import org.apache.log4j.Logger;


/**
 * SmartPostIt 객체 클래스. 이 객체 하나가 포스트잇 한 장에 해당하는 셈.
 * 
 * @author		sam
 * @Version		0.1
 *
 */
class SPIDocument implements Serializable
{
	/**
	 * @serial		SmartPostIt 객체 
	 * @serialField	frame	SPIFrame	PostIt Frame
	 * @serialField	type	SPIType		PostIt Type, enum type
	 * @serialField	content	SPIContent	PostIt Content. Interface. Will be Strategy Pattern.
	 */
	private static final long serialVersionUID = 426972755561118174L;
	//Post It Frames
	private SPIFrame	frame;
	private SPIType		type;
	private SPIPanel	panel;
	private SPIPopup	popup;
	
	private final transient Logger log = Logger.getLogger(this.getClass());
	
	SPIFrame getFrame()
	{
		return frame;
	}
	void setFrame(SPIFrame frame)
	{
		this.frame = frame;
	}
	
	SPIType getType()
	{
		return type;
	}
	void setType(SPIType type)
	{
		this.type = type;
	}
	
	SPIPanel getPanel()
	{
		return panel;
	}
	void setPanel(SPIPanel panel)
	{
		this.panel = panel;
	}
	
	SPIPopup getPopup()
	{
		return popup;
	}
	void setPopup(SPIPopup popup)
	{
		this.popup = popup;
	}
	
}
