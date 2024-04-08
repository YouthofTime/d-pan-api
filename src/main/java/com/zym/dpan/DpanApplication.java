package com.zym.dpan;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.zym.dpan.dao")
public class DpanApplication {

	public static void main(String[] args) {
		SpringApplication.run(DpanApplication.class, args);
	}

}
