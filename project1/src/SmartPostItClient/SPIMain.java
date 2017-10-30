package SmartPostItClient;

import java.util.Vector;
import javax.swing.JFrame;
import org.apache.log4j.Logger;

/**
 * SmartPostIt 메인
 * 모든 포스트잇의 생성/삭제를 관리한다.
 * 또한 로깅, 파일관리, 네트워크 기능을 스레드로 구현.
 * 
 * @author		sam
 * @Version		0.1
 */
public class SPIMain
{
	//Variables
	private final transient static Logger logger = Logger.getLogger(SPIMain.class);
	//Post It Frames
	Vector<JFrame> postIt;
	
	public static void main(String[] args)
	{
		//Variables
		
		// Logger Test
		logger.info("안녕하세요! Test입니다");

		
		
		//Serving
		while (true) {
			System.exit(0);
		}
	}

}
