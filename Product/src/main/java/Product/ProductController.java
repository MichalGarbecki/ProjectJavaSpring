package Product;

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
public class ProductController {
	
	@GetMapping("/createProduct")
	public int createProduct(
		@RequestParam(value = "CreditId") @NotNull int creditId,
		@RequestParam(value = "ProductName") @NotNull @Size(min = 3) String productName,
		@RequestParam(value = "Value") @NotNull @Min(1) int value
	) {
		new Product(creditId, productName, value).createProduct();
		return creditId;
	}
	
	@GetMapping("/getProducts")
	public List<Map<String,Object>> getProducts() {
		
		RestTemplate restTemplate = new RestTemplate();
		
		List<Map<String,Object>> products = restTemplate.getForObject("http://localhost:8010/getProducts", List.class);
		
		return products;
	}
	
}
