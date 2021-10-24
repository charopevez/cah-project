package it.theboys.project0002api;

import it.theboys.project0002api.enums.cah.CahCardAction;
import it.theboys.project0002api.enums.cah.CahCardType;
import it.theboys.project0002api.model.base.CahCard;
import it.theboys.project0002api.repository.CahCardRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.AbstractMap;
import java.util.stream.Stream;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "it.theboys.project0002api.repository")
public class Project0002apiApplication {

	public static void main(String[] args) {

		SpringApplication.run(Project0002apiApplication.class, args);
	}

}
