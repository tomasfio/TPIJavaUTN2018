package Main.Util;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class LogException extends Exception{
	private String message;

	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String mes) {
		this.message = mes;
	}
	
	public LogException(Throwable e, String message){
		this.setMessage(message);
	}
	
	public LogException(Throwable e, String message, Level errorLevel){
		this(e,message);
		Logger logger = LogManager.getLogger(getClass());
		logger.log(errorLevel,message);
	}
}

