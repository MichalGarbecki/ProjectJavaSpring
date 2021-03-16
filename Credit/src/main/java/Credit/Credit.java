package Credit;

import org.springframework.web.client.RestTemplate;

public class Credit {
	
	private int id;
	private String creditName;

	public Credit(int id, String creditName) {
		this.id = id;
		this.creditName = creditName;
	}

	@Override
	public String toString() {
		return String.format(
				"Credit[id=%d, creditName='%s']",
				id, creditName);
	}
	
	public String getCreditName(){
		return creditName;
	}
	
	public int getId(){
		return id;
	}
	
	public void createCredit(){
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getForObject("http://localhost:8010/createCredit?Id="+id+"&CreditName="+creditName+"", Integer.class);
	}
	
}
