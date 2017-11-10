package project1;

import javax.swing.UIManager;

public class test1
{

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		System.out.println("Hello");
		System.out.println("Hi");
		
		
		
		//test
		System.out.println("go home");
		System.out.println("test");
		System.out.println("test2");
		
		UIManager.LookAndFeelInfo[] looks = UIManager.getInstalledLookAndFeels();
	    for (UIManager.LookAndFeelInfo look : looks) {
	      System.out.println(look.getClassName());
	    }
	}

}
