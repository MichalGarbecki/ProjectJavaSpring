package Customer;

import org.springframework.web.client.RestTemplate;

public class Customer {
	
	private int creditId;
	private String firstName;
	private String pesel;
	private String surname;

	public Customer(int creditId, String firstName, String pesel, String surname) {
		this.creditId = creditId;
		this.firstName = firstName;
		this.pesel = pesel;
		this.surname = surname;
	}

	@Override
	public String toString() {
		return String.format(
				"Customer[creditId=%d, firstName='%s', pesel='%s', surname='%s']",
				creditId, firstName, pesel, surname);
	}
	
	public int getCreditId(){
		return creditId;
	}
	
	public String getFirstName(){
		return firstName;
	}
	
	public String getPesel(){
		return pesel;
	}
	
	public String getSurname(){
		return surname;
	}
	
	public void createCustomer(){
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getForObject("http://localhost:8010/createCustomer?CreditId="+creditId+"&FirstName="+firstName+"&Pesel="+pesel+"&Surname="+surname+"", Integer.class);
	}
	
}
