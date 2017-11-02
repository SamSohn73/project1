package SmartPostItClient;

import java.awt.Color;
import java.io.Serializable;

import org.apache.log4j.Logger;

class SPIUtil implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5726585300553268829L;

	private final transient Logger logger = Logger.getLogger(this.getClass());
	
	static Color YELLOW	= new Color(255, 255, 153);	//YELLOW
	static Color GREEN	= new Color(204, 255, 153);	//GREEN
	static Color BLUE	= new Color(153, 204, 255);	//BLUE
	static Color PINK	= new Color(255, 204, 204);	//PINK
	static Color PURPLE	= new Color(204, 153, 255);	//PURPLE
	static Color WHITE	= new Color(255, 255, 255);	//WHITE
}
