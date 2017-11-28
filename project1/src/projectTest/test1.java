package projectTest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.UIManager;

public class test1
{

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		System.out.println("Hello");
		System.out.println("Hi");
		
		
		
		//test
		/*System.out.println("go home");
		System.out.println("test");
		System.out.println("test2");*/
		
		
		//FileOutputStream fos = null;
		//File path = new File("%appdata%/serial.obj");
		//System.out.println(path.getName());
		System.out.println(System.getenv("LOCALAPPDATA")); //local
		System.out.println(System.getenv("APPDATA")); //roaming
		
		String filePath =  System.getenv("APPDATA") + "\\spiData\\savedata.obj";
		System.out.println(filePath);
		
/*		try {
			fos = new FileOutputStream("%appdata%/serial.obj");
			System.out.println(fos.toString());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
		
		System.out.println();
		System.out.println();
		System.out.println();
		
		UIManager.LookAndFeelInfo[] looks = UIManager.getInstalledLookAndFeels();
	    for (UIManager.LookAndFeelInfo look : looks) {
	      System.out.println(look.getClassName());
	    }
	}

}
