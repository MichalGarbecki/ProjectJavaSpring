package Database;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.*;
import java.util.stream.Collectors;

public class DatabaseSchema {
	
	JdbcTemplate jdbcTemplate;
	
	public DatabaseSchema(JdbcTemplate jdbcTemplate){
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public void createSchema(){
		createCreditTable();
		createCustomerTable();
		createProductTable();
	}
	
	private void createCreditTable(){
		jdbcTemplate.execute("CREATE TABLE credit (Id INT, CreditName VARCHAR(255))");
	}
	
	private void createCustomerTable(){
		jdbcTemplate.execute("CREATE TABLE customer (CreditId INT, FirstName VARCHAR(255), Pesel VARCHAR(255), Surname VARCHAR(255))");
	}
	
	private void createProductTable(){
		jdbcTemplate.execute("CREATE TABLE product (CreditId INT, ProductName VARCHAR(255), Value INT)");
	}
}
