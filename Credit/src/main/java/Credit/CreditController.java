package Credit;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.client.RestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;

import javax.validation.Valid;
import javax.validation.constraints.*;
import org.springframework.validation.annotation.Validated;

import java.util.Arrays;
import java.util.List;
import java.util.*;
import java.util.stream.Collectors;

@Validated
@RestController
public class CreditController {
	
	@GetMapping("/getCredits")
	public List<CreditData> getCredits() {
		
		RestTemplate restTemplate = new RestTemplate();
		
		List<Map<String,Object>> credits = restTemplate.getForObject("http://localhost:8010/getCredits", List.class);
		List<Map<String,Object>> products = restTemplate.getForObject("http://localhost:8030/getProducts", List.class);
		List<Map<String,Object>> customers = restTemplate.getForObject("http://localhost:8040/getCustomers", List.class);
		
		List<CreditData> creditDataList = new CreditDataListMerge(credits, products, customers).getCreditDataList();
		
		return creditDataList;
	}
	
	@GetMapping("/createCredit")
	public int createCredit(
		@RequestParam(value = "FirstName") @NotNull @Size(min = 3) @Pattern(regexp="^([A-Z][a-z]*)") String firstName,
		@RequestParam(value = "Surname") @NotNull @Size(min = 3) @Pattern(regexp="^([A-Z][a-z]*)") String surname,
		@RequestParam(value = "Pesel") @NotNull @Size(min = 11, max =11) String pesel,
		@RequestParam(value = "ProductName") @NotNull @Size(min = 3) String productName,
		@RequestParam(value = "Value") @NotNull @Min(1) int value,
		@RequestParam(value = "CreditName") @NotNull @Size(min = 3) String creditName
	) {
		int creditId = getNextCreditId();
		
		RestTemplate restTemplate = new RestTemplate();
		
		restTemplate.getForObject("http://localhost:8030/createProduct?CreditId="+creditId+"&ProductName="+productName+"&Value="+value+"", Integer.class);
		restTemplate.getForObject("http://localhost:8040/createCustomer?CreditId="+creditId+"&FirstName="+firstName+"&Pesel="+pesel+"&Surname="+surname+"", Integer.class);
		new Credit(creditId, creditName).createCredit();
		
		return creditId;
	}
	
	public int getNextCreditId(){
		
		RestTemplate restTemplate = new RestTemplate();
		
		int lastCreditId = 0;
		int nextCreditId = 0;
		
		lastCreditId = restTemplate.getForObject("http://localhost:8010/getLastCreditId", Integer.class);
		
		nextCreditId = lastCreditId + 1;
		return nextCreditId;
	}
	
}
