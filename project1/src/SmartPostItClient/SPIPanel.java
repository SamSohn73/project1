package SmartPostItClient;

import java.io.Serializable;


/**
 * Interface for the various JPanel connection with JFrame
 * 
 * @author		sam
 * @Version		0.1
 * @serial		SmartPostIt 의 저장 및 전송을 위한 Strategy 패턴 연결자.
 */
interface SPIPanel extends Serializable
{
/*	JPanel getPanel();
	void setPanel(JPanel panel);*/
	SPIMemoPopup getPopup();
	void setPopup(SPIMemoPopup popup);
}
