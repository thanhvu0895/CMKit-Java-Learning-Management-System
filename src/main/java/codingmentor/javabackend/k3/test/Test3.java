package codingmentor.javabackend.k3.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Test3 {
	private static Logger logger = LogManager.getLogger("codingmentor");
	
	public static void main(String[] args)
	{	
		//logger.debug("Testing logger");
		logger.error("Testing logger");
	}
}