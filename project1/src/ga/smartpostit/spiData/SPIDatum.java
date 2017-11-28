package ga.smartpostit.spiData;

import java.awt.Color;
import java.awt.Dimension;
import java.io.Serializable;
import java.util.Vector;

import ga.smartpostit.spiClient.SPIDocument;
import ga.smartpostit.spiClient.SPIMemoPanel;


public class SPIDatum implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int				x;
	int				y;
	Dimension		dim;
	Color			bgColor;
	SPIType			type;
	Object			spiPane;
	
	
	public int getX()
	{
		return x;
	}
	public void setX(int x)
	{
		this.x = x;
	}

	public int getY()
	{
		return y;
	}
	public void setY(int y)
	{
		this.y = y;
	}

	public Dimension getDim()
	{
		return dim;
	}
	public void setDim(Dimension dim)
	{
		this.dim = dim;
	}

	public Color getBgColor()
	{
		return bgColor;
	}
	public void setBgColor(Color bgColor)
	{
		this.bgColor = bgColor;
	}

	public SPIType getType()
	{
		return type;
	}
	public void setType(SPIType type)
	{
		this.type = type;
	}

	public Object getSpiPane()
	{
		return spiPane;
	}
	public void setSpiPane(Object spiPane)
	{
		this.spiPane = spiPane;
	}

}


