package JavaKodlama.northwind.Business.Abstract;

import java.util.List;


import JavaKodlama.northwind.Core.utilities.results.DataResult;
import JavaKodlama.northwind.Core.utilities.results.result;
import JavaKodlama.northwind.Entities.Concretes.Customer;

public interface CustomerService {
	DataResult <List< Customer>>getAll();
	DataResult <List< Customer>>getAllSorted();
	DataResult <List< Customer>>getAll(int pageNo, int pageSize);

	result add(Customer customer);
	result update(Customer customer);
	DataResult<Customer> getByCustomerName(String customerName);
	

	


	

}
