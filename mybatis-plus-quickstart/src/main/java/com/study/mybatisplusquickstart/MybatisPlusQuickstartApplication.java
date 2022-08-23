package com.study.mybatisplusquickstart;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author elijah
 */
@SpringBootApplication
@MapperScan("com.study.mybatisplusquickstart.mapper")
public class MybatisPlusQuickstartApplication {

	public static void main(String[] args) {
		SpringApplication.run(MybatisPlusQuickstartApplication.class, args);
	}

}
