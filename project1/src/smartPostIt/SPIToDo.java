package smartPostIt;

import java.io.Serializable;
import java.util.Vector;
import it.sauronsoftware.cron4j.Scheduler;


/**
 * SmartPostIt 타입 중 To Do List 저장 및 전송
 * 
 * @author		sam
 * @Version		0.1
 */
public class SPIToDo implements Serializable
{
	/**
	 * @serial		SmartPostIt 중 즐겨찾기 정보의 저장 및 전송 
	 * @serialField	subject		String		제목.
	 * @serialField	detail		String		상세.
	 * @serialField	schedule	Scheduler	Apache cron4j를 이용해 알람 및 다이얼로그 박스로 알림.
	 */
	private static final long serialVersionUID = 1278401597192074225L;
	private Vector<Task>	tasks;

	public class Task implements Serializable
	{
		private static final long serialVersionUID = 7791639926945970776L;
		private		String		subject;
		private		String		detail;
		private		Scheduler	schedule;
	}

}
