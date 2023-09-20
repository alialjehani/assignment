package com.tree.assignment;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
@Slf4j
public class AssignmentApplication {
	@Autowired
	private JdbcTemplate template;

	public static void main(String[] args) {
		SpringApplication.run(AssignmentApplication.class, args);
	}

//	@Override
//	public void run(String... args) throws Exception {
//
//	}
}
