package Database;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.*;

import java.util.Arrays;
import java.util.List;
import java.util.*;
import java.util.stream.Collectors;

@Validated
@RestController
public class DatabaseController {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@GetMapping("/getCredits")
	public List<Map<String,Object>> getCredits() {
		
		String sql = "SELECT Id, CreditName FROM credit";
		List<Map<String,Object>> credits = jdbcTemplate.queryForList(sql);
		
		return credits;
	}
	
	@GetMapping("/getProducts")
	public List<Map<String,Object>> getProducts() {
		
		String sql = "SELECT CreditId, ProductName, Value FROM product";
		List<Map<String,Object>> products = jdbcTemplate.queryForList(sql);
		
		return products;
	}
	
	@GetMapping("/getCustomers")
	public List<Map<String,Object>> getCustomers() {
		
		String sql = "SELECT CreditId, FirstName, Pesel, Surname FROM customer";
		List<Map<String,Object>> customers = jdbcTemplate.queryForList(sql);
		
		return customers;
	}
	
	@GetMapping("/getLastCreditId")
	public int getLastCreditId() {
		
		int lastCreditId = 0;
		String sql = "SELECT max(Id) as maxId from credit";
		
		try{
			lastCreditId = jdbcTemplate.queryForObject(sql, Integer.class);
		} catch(NullPointerException e){
			lastCreditId = 0;
		}
		
		return lastCreditId;
	}
	
	@GetMapping("/createProduct")
	public int createProduct(
		@RequestParam(value = "CreditId") @NotNull int creditId,
		@RequestParam(value = "ProductName") @NotNull @Size(min = 3) String productName,
		@RequestParam(value = "Value") @NotNull @Min(1) int value
	){
		jdbcTemplate.batchUpdate("INSERT INTO product(CreditId, ProductName, Value) VALUES ("+creditId+",'"+productName+"',"+value+")");
		return creditId;
	}
	
	@GetMapping("/createCustomer")
	public int createCustomer(
		@RequestParam(value = "CreditId") @NotNull int creditId,
		@RequestParam(value = "FirstName") @NotNull @Size(min = 3) @Pattern(regexp="^([A-Z][a-z]*)") String firstName,
		@RequestParam(value = "Pesel") @NotNull @Size(min = 11, max =11) String pesel,
		@RequestParam(value = "Surname") @NotNull @Size(min = 3) @Pattern(regexp="^([A-Z][a-z]*)") String surname
	){
		jdbcTemplate.batchUpdate("INSERT INTO customer(CreditId, FirstName, Pesel, Surname) VALUES ("+creditId+",'"+firstName+"','"+pesel+"','"+surname+"')");
		return creditId;
	}
	
	@GetMapping("/createCredit")
	public int createCredit(
		@RequestParam(value = "Id") @NotNull int id,
		@RequestParam(value = "CreditName") @NotNull @Size(min = 3) String creditName
	){
		jdbcTemplate.batchUpdate("INSERT INTO credit(Id, CreditName) VALUES ("+id+",'"+creditName+"')");
		return id;
	}
	
}
