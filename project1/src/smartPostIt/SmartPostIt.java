package smartPostIt;

import java.awt.Color;
import java.awt.Dimension;
import java.io.Serializable;
import java.util.Vector;

import javax.swing.text.Document;
import javax.swing.text.html.HTMLDocument;

/**
 * Serializable 인터페이스를 구현하여 ;
 * 한 사용자의 SmartPostIt 정보 전체를 저장 및 전송하기 위한 클래스 ;
 * Strategy 패턴을 이용하여 추후에 추가될 다른 형태의 노트에 대한 확장성을 확보 ; 
 * 
 * @author		sam
 * @Version		0.1
 * @serial		SmartPostIt 의 저장 및 전송 
 * @serialField	frameStartX	int			포스트잇 윈도우 시작 x 위치
 * @serialField	frameStartY	int			포스트잇 윈도우 시작 y 위치
 * @serialField	frameSize	Dimension	포스트잇 윈도우 길이와 높이
 * @serialField	framecolor	Color		포스트잇 윈도우 색깔
 * @serialField	SPIType		int			포스트잇 종류. enum
 * @serialField	content		SPIContent	실제 사용자의 포스트잇 내용. 빈 인터페이스. Strategy 패턴
 */
public class SmartPostIt implements Serializable
{
	private static final long serialVersionUID = -3721857195630788085L;
	private int				frameStartX;
	private int				frameStartY;
	private Dimension		frameSize;
	private Color			framecolor;
	
	private SPIType			type;
	private SPIContent		content;
}
