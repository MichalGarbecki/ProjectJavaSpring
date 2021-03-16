package Credit;

public class CreditData {
	
	private int id;
	private String creditName;
	private String productName;
	private int value;
	private String firstName;
	private String pesel;
	private String surname;

	public CreditData(int id, String creditName, String productName, int value, String firstName, String pesel, String surname) {
		this.id = id;
		this.creditName = creditName;
		this.productName = productName;
		this.value = value;
		this.firstName = firstName;
		this.pesel = pesel;
		this.surname = surname;
	}
	
	public int getId(){
		return id;
	}
	
	public String getCreditName(){
		return creditName;
	}
	
	public String getProductName(){
		return productName;
	}
	
	public int getValue(){
		return value;
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

}
