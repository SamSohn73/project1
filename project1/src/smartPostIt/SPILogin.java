package smartPostIt;

import java.io.Serializable;
import java.util.Vector;

/**
 * SmartPostIt 네트워크 로그인 정보
 * 인터넷 접속 정보 필드가 필요할 것이다.
 * 
 * @author		sam
 * @Version		0.1
 */
public class SPILogin implements Serializable
{
	private static final long serialVersionUID = 216095542109627657L;
	private static String			username;
	private static String 			password;
	private static Vector<String>	group;
}
