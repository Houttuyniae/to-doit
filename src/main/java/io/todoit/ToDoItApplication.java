package io.todoit;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@EnableTransactionManagement
@SpringBootApplication
@ServletComponentScan
@MapperScan("io.todoit.modules.*.mapper")
public class ToDoItApplication {

	public static void main(String[] args) {
		SpringApplication.run(ToDoItApplication.class, args);
	}

}

