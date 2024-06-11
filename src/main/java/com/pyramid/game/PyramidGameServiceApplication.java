package com.pyramid.game;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@SpringBootApplication
public class PyramidGameServiceApplication {

	public static void main(String[] args) throws IOException {

		SpringApplication.run(PyramidGameServiceApplication.class, args);

		File resource = new ClassPathResource(
				"pari.json").getFile();

		String pari = new String(
				Files.readAllBytes(resource.toPath()));
		//System.out.println(pari);
	}



}
