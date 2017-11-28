package ga.smartpostit.spiData;

import java.awt.Color;
import java.io.Serializable;

import org.apache.log4j.Logger;

public class SPIUtil implements Serializable
{
	private static final long serialVersionUID = -5726585300553268829L;

	private final transient Logger log = Logger.getLogger(this.getClass());
	
	public static Color YELLOW	= new Color(255, 255, 153);	//YELLOW
	public static Color GREEN	= new Color(204, 255, 153);	//GREEN
	public static Color BLUE	= new Color(153, 204, 255);	//BLUE
	public static Color PINK	= new Color(255, 204, 204);	//PINK
	public static Color PURPLE	= new Color(204, 153, 255);	//PURPLE
	public static Color WHITE	= new Color(255, 255, 255);	//WHITE
}
