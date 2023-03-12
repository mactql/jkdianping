package com.caiyiming.jkdianping;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.caiyiming.jkdianping"})
@MapperScan("com.caiyiming.jkdianping.dao")
public class JkdianpingApplication {

	public static void main(String[] args) {
		SpringApplication.run(JkdianpingApplication.class, args);
	}

}
