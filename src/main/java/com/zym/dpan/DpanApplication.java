package com.zym.dpan;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zym.dpan.dao")
public class DpanApplication {

	public static void main(String[] args) {
		SpringApplication.run(DpanApplication.class, args);
	}

}
