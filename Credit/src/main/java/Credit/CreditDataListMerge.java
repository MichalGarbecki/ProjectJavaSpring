package Credit;

import java.util.*;

public class CreditDataListMerge{
	
	List<Map<String,Object>> credits;
	List<Map<String,Object>> products;
	List<Map<String,Object>> customers;
	
	public CreditDataListMerge(List<Map<String,Object>> credits, List<Map<String,Object>> products, List<Map<String,Object>> customers){
		this.credits = credits;
		this.products = products;
		this.customers = customers;
	}
	
	public List<CreditData> getCreditDataList(){
		
		List<CreditData> creditDataList = new ArrayList<CreditData>();
		
		List<Integer> listOfIds = getListOfId(credits);
		
		for(int idFromList: listOfIds){
			
			String creditNameForList ="";
			String productNameForList = "";
			int valueForList = 0;
			String firstNameForList = "";
			String peselForList = "";
			String surnameForList = "";
			
			
			for(int i = 0; i < credits.size(); i++){
				
				Map<String, Object> map = credits.get(i);
				
				int id = (int)map.get("ID");
				String creditName = (String)map.get("CREDITNAME");
				
				if(idFromList == id){
					creditNameForList = creditName;
				}
			}
			
			for(int i = 0; i < products.size(); i++){
				
				Map<String, Object> map = products.get(i);
				
				int creditId = (int)map.get("CREDITID");
				String productName = (String)map.get("PRODUCTNAME");
				int value = (int)map.get("VALUE");
				
				if(idFromList == creditId){
					productNameForList = productName;
					valueForList = value;
				}
			}
			
			for(int i = 0; i < customers.size(); i++){
				
				Map<String, Object> map = customers.get(i);
				
				int creditId = (int)map.get("CREDITID");
				String firstName = (String)map.get("FIRSTNAME");
				String pesel = (String)map.get("PESEL");
				String surname = (String)map.get("SURNAME");
				
				if(idFromList == creditId){
					firstNameForList = firstName;
					peselForList = pesel;
					surnameForList = surname;
				}
			}
			
			CreditData creditData = new CreditData(
				idFromList,
				creditNameForList,
				productNameForList,
				valueForList,
				firstNameForList,
				peselForList,
				surnameForList
			);
			
			creditDataList.add(creditData);
		}
		
		return creditDataList;
	}
	
	private List<Integer> getListOfId(List<Map<String,Object>> list){
		int listSize = list.size();
		int id;
		
		List<Integer> listOfIds = new ArrayList<Integer>();
		
		for(int i = 0; i < listSize; i++){
			Map<String, Object> map = list.get(i);
			id = (int)map.get("ID");
			listOfIds.add(id);
		}
		return listOfIds;
	}
	
}