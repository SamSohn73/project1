package SmartPostItClient;

import java.io.Serializable;
import javax.swing.JFrame;

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
	SPIFrame	frame;
	SPIType		type;
	SPIContent	content;
	
	
	public SPIDocument(SPIType type)
	{
		super();
	}

	
	
	
}
