package common;

import org.apache.log4j.*;


public class Log4jClass {
	
	private static Logger log = Logger.getLogger(Log4jClass.class.getName());
	
	private Log4jClass(){
	}
	
	public static void info(String message)
	{
		System.out.println(message);
		String strCallerClass = new Exception().getStackTrace()[1].toString();
		strCallerClass = strCallerClass.substring(strCallerClass.indexOf('(') + 1, strCallerClass.indexOf(')'))
				.replace(".java", "");
		log.info(strCallerClass + "\t-\t" + message);
	}
	
}
