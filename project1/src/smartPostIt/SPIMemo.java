package smartPostIt;

import javax.swing.text.DefaultStyledDocument;

/**
 * SmartPostIt 타입 중 일반 메모의 저장 및 전송
 * Windows기본 스티키노트처럼 RTF타입 문서, HTTP 링크만 따라가는 정도로 할지
 * 기본적인 HTML 문서를 표현가능하게 해야할지?
 * 
 * @author		sam
 * @Version		0.1
 * @serial		SmartPostIt 중 즐겨찾기 정보의 저장 및 전송 
 * @serialField	contents	DefaultStyledDocument		RTF타입 문서.
 */
public class SPIMemo extends SmartPostIt implements SPIContent
{
	private static final long serialVersionUID = -4214733835693339513L;
	private		DefaultStyledDocument	contents;
	//private		HTMLDocument		contents;
}
