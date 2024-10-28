package com.file_sharing.app;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FileSharingApplication  {

	public static void main(String[] args) {
		SpringApplication.run(FileSharingApplication.class, args);
	}

}
