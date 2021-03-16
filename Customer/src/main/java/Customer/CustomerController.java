package Customer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.client.RestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;

import javax.validation.constraints.*;
import org.springframework.validation.annotation.Validated;

import java.util.Arrays;
import java.util.List;
import java.util.*;
import java.util.stream.Collectors;

@Validated
@RestController
public class CustomerController {
	
	@GetMapping("/createCustomer")
	public int createCustomer(
		@RequestParam(value = "CreditId", defaultValue = "") @NotNull int creditId,
		@RequestParam(value = "FirstName") @NotNull @Size(min = 3) @Pattern(regexp="^([A-Z][a-z]*)") String firstName,
		@RequestParam(value = "Surname") @NotNull @Size(min = 3) @Pattern(regexp="^([A-Z][a-z]*)") String surname,
		@RequestParam(value = "Pesel") @NotNull @Size(min = 11, max =11) String pesel
	) {
		new Customer(creditId, firstName, pesel, surname).createCustomer();
		return creditId;
	}
	
	@GetMapping("/getCustomers")
	public List<Map<String,Object>> getCustomers() {
		
		RestTemplate restTemplate = new RestTemplate();
		
		List<Map<String,Object>> customers = restTemplate.getForObject("http://localhost:8010/getCustomers", List.class);
		
		
		return customers;
	}
	
	
	
}
