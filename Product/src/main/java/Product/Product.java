package Product;

import org.springframework.web.client.RestTemplate;

public class Product{
	
	private int creditId;
	private String productName;
	private int value;
	
	public Product(int creditId, String productName, int value){
		this.creditId = creditId;
		this.productName = productName;
		this.value = value;
	}
	
	@Override
	public String toString() {
		return String.format(
				"Product[creditId=%d, productName='%s', value=%d]",
				creditId, productName, value);
	}
	
	public int getCreditId(){
		return creditId;
	}
	
	public String getProductName(){
		return productName;
	}
	
	public int getValue(){
		return value;
	}
	
	public void createProduct(){
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getForObject("http://localhost:8010/createProduct?CreditId="+creditId+"&ProductName="+productName+"&Value="+value+"", Integer.class);
	}

}