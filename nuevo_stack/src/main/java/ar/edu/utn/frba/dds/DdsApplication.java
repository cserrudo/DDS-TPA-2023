package ar.edu.utn.frba.dds;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.sound.sampled.Port;
import java.util.Collections;

@SpringBootApplication
public class DdsApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(DdsApplication.class);
		app.setDefaultProperties(Collections
				.singletonMap("server.port", "8083"));
		app.run(args);

	}

}
