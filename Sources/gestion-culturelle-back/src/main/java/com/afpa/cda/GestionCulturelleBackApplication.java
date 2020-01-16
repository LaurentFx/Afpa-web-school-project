package com.afpa.cda;

import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.afpa.cda.dao.SalleRepository;
import com.afpa.cda.dao.TypeSalleRepository;
import com.afpa.cda.entity.TypeSalle;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class GestionCulturelleBackApplication  implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(GestionCulturelleBackApplication.class, args);
	}

	@Bean
	public ModelMapper createModelMapper() {
		return new ModelMapper();
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedMethods("GET", "PUT", "POST", "DELETE", "PATCH");
	}
	
	@Bean
	public CommandLineRunner init(TypeSalleRepository typeSalleRepository) {
		return (String... args)->{
			typeSalleRepository.save(new TypeSalle(1,"Cinema"));
			typeSalleRepository.save(new TypeSalle(2,"Stade"));

			
		};
	}
	
	
}
