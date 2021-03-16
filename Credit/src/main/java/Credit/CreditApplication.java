package Credit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.web.client.RestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CreditApplication {

	public static void main(String[] args) {
		SpringApplication.run(CreditApplication.class, args);
	}

}
