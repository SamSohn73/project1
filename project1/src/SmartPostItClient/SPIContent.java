package SmartPostItClient;

import java.io.Serializable;

import javax.swing.JPanel;

import org.apache.log4j.Logger;



/**
 * 다양한 SmartPostIt 실제 내용과 객체 윈도우 표현을 위한 정보와 연결
 * 
 * @author		sam
 * @Version		0.1
 * @serial		SmartPostIt 의 저장 및 전송을 위한 Strategy 패턴 연결자.
 */
interface SPIContent extends Serializable
{
	JPanel getPanel();
	void setPanel(JPanel panel);
	SPIMemoPopup getPopup();
	void setPopup(SPIMemoPopup popup);
}
