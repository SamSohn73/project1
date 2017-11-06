package smartPostIt;

import javax.swing.JTree;
import javax.swing.text.html.HTMLDocument;

/**
 * SmartPostIt 타입 중 즐겨찾기 정보의 저장 및 전송
 * 일단 JTRee 에 정보를 저장해 볼 생각인데 자료구조를 바꿔야 할 수도 있다.
 * 
 * @author		sam
 * @Version		0.1
 * @serial		SmartPostIt 중 즐겨찾기 정보의 저장 및 전송 
 * @serialField	contents	JTree			즐겨찾기 내용을 JTree에 저장
 */
public class SPIFavorite extends SmartPostIt implements SPIPanel
{
	private static final long serialVersionUID = 61222214647227547L;
	private		JTree	contents;
}
