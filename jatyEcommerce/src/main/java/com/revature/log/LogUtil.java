package com.revature.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtil {

	public static final Logger logger = LoggerFactory.getLogger(LogUtil.class);
	
	public static void main(String[] args) {
		logger.trace("asdf");

	}

}
