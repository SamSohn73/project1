package ga.smartpostit.spiClient;

import javax.swing.JPanel;

import org.apache.log4j.Logger;


/**
 * SmartPostIt 타입 중 즐겨찾기 정보 관리 포스트잇 화면
 * 일단 JTRee 에 정보를 저장해 볼 생각인데 자료구조를 바꿔야 할 수도 있다.
 * 
 * @author		sam
 * @Version		0.1
 * @serial		SmartPostIt 중 즐겨찾기 정보의 저장 및 전송 
 */
class SPIFavoritePanel extends JPanel implements SPIPanel
{
	private static final long serialVersionUID = 1L;
	private final transient Logger log = Logger.getLogger(this.getClass());
	
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
	public SPIFavoritePanel()
	{

	}
}
