package com.jaty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.jaty.utils.BucketUtil;

@SpringBootApplication
public class JatyApplication {

	public static void main(String[] args) {
		SpringApplication.run(JatyApplication.class, args);
	}

	@Bean
	public Logger log() {
		Logger logger = LoggerFactory.getLogger(JatyApplication.class);
		return logger;
	}
	
	public BucketUtil bucket() {
		BucketUtil b = new BucketUtil();
		return b;
	}
}
