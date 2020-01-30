package Scripts;

import java.io.File;

public class Rough {
	public static void main(String args[]){
		String projPath = System.getProperty("user.dir");
		String filePath = projPath+File.separator+"Screenshot"+File.separator+"test.png";
		
		System.out.println(filePath);
	}
}
