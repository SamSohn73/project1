package SmartPostItClient;


import javax.swing.JPanel;

import org.apache.log4j.Logger;


class SPIToDoView extends JPanel implements SPIContent
{
	private static final long serialVersionUID = 7915069899691710000L;
	private final transient Logger logger = Logger.getLogger(this.getClass());
	
	private JPanel panel;
	private SPIMemoPopup popup;
	
	public JPanel getPanel()
	{
		return panel;
	}
	public void setPanel(JPanel panel)
	{
		this.panel = panel;
	}
	
	public SPIMemoPopup getPopup()
	{
		return popup;
	}
	public void setPopup(SPIMemoPopup popup)
	{
		this.popup = popup;
	}
	/**
	 * Create the panel.
	 */
	public SPIToDoView()
	{

	}

}
