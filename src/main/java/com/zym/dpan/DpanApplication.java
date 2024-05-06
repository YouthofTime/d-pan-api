package com.zym.dpan;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;


@MapperScan({"com.zym.dpan.dao","com.zym.dpan.user.dao"})
@SpringBootApplication
@ServletComponentScan("com.zym.dpan.filter")
public class DpanApplication {

	public static void main(String[] args) {
		SpringApplication.run(DpanApplication.class, args);
	}

}
