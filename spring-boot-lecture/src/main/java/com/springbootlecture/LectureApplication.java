package com.springbootlecture;

import com.springbootlecture.entities.Animal;
import com.springbootlecture.entities.Dog;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

@SpringBootApplication
public class LectureApplication {

	public static void main(String[] args) {
		SpringApplication.run(LectureApplication.class, args);
	}

	@Bean
	@Scope("prototype")
	public Animal getDog(){
		return  new Dog(213123L,"sharooouoo");
	}
}
