package pet.store.controller.model;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;
import pet.store.entity.Customer;
import pet.store.entity.Employee;
import pet.store.entity.PetStore;

@Data
@NoArgsConstructor
public class PetStoreData {
	
  private Long petStoreId;
  private String storeName;
  private String storeCity;
  private String address;
  private String country;
  private Set<PetStoreCustomer> customers =new HashSet<>();
  private Set<PetStoreEmployee> employees = new HashSet<>();

  public PetStoreData(PetStore petStore) {
	  petStoreId = petStore.getPetStoreId();
	  storeName = petStore.getStoreName();
	  storeCity = petStore.getStoreCity();
	  address = petStore.getAddress();
	  country = petStore.getCountry();
	  
	for(Customer customer : petStore.getCustomers()) {
		PetStoreCustomer petStoreCustomer = new PetStoreCustomer(customer);
		customers.add(petStoreCustomer);
	}
	for(Employee employee : petStore.getEmployees()) {
		PetStoreEmployee petStoreEmployee = new PetStoreEmployee(employee);
		employees.add(petStoreEmployee);
	}
  }

  @Data
  @NoArgsConstructor
  static class PetStoreCustomer {
     private Long customerId;
     private String customerFirstName;
     private String customerLastName;
     private String customerEmail;
     
     public PetStoreCustomer(Customer customer) {
    	 customerId = customer.getCustomerId();
         customerFirstName = customer.getCustomerFirstName();
         customerLastName = customer.getCustomerLastName();
         customerEmail = customer.getCustomerEmail();
		
	}
  }
  @Data
  @NoArgsConstructor
  static class PetStoreEmployee {
     private Long employeeId;
     private String employeeName;
	 private String employeeEmail;
	 private String employeePhoneNumber;
	 
	 public PetStoreEmployee(Employee employee){
		 employeeId = employee.getEmployeeId();
	     employeeName = employee.getEmployeeName();
		 employeeEmail = employee.getEmployeeEmail();
		 employeePhoneNumber = employee.getEmployeePhoneNumber();
	 }
  }
}

