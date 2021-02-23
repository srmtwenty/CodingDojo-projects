package io.github.jyotinaruka.friendbook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

@SpringBootApplication
public class FriendbookApplication {

	public static void main(String[] args) {
		SpringApplication.run(FriendbookApplication.class, args);
	}
	
	@Bean(name = "multipartResolver")
	public StandardServletMultipartResolver multipartResolver() {
	    return new StandardServletMultipartResolver();
	}
}
