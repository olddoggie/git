package frameLib;

import java.io.*;
import java.sql.SQLException;
import java.util.*;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
 
public class MyLog{

	public static Logger qaLog = Logger.getLogger("1fb");
//	public static Logger qaLog = Logger.getLogger(methodCaller.getClassName());
//  public static Logger qaLog = getLogger();
//  
//  
//  public static Logger getLogger() {
//		final Throwable t = new Throwable();
//		final StackTraceElement methodCaller = t.getStackTrace()[1];
//		final Logger logger = Logger.getLogger(methodCaller.getClassName());
//		return logger;
//	}
  
  public static void main(String[] args)
                throws IOException,SQLException{
	  
	 // TT.test1();
	  //BasicConfigurator.configure();
	  System.out.println("tyest");
	  qaLog.trace("Trace Message!");
	  qaLog.debug("Debug Message!");
//      log.info("Info Message!");
//      log.warn("Warn Message!");
//      log.error("Error Message!");
//      log.fatal("Fatal Message!");
  }
}
