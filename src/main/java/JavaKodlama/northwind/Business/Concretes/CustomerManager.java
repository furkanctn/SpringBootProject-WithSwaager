package JavaKodlama.northwind.Business.Concretes;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import JavaKodlama.northwind.Business.Abstract.CustomerService;
import JavaKodlama.northwind.Core.utilities.results.DataResult;
import JavaKodlama.northwind.Core.utilities.results.ErrorResult;
import JavaKodlama.northwind.Core.utilities.results.SuccessDataResult;
import JavaKodlama.northwind.Core.utilities.results.SuccessResult;
import JavaKodlama.northwind.Core.utilities.results.result;
import JavaKodlama.northwind.DataAccess.Abstract.CustomerDao;
import JavaKodlama.northwind.Entities.Concretes.Customer;

@Service
public class CustomerManager implements CustomerService {
	
	private CustomerDao customerDao;
	
	@Autowired   
	public CustomerManager(CustomerDao customerDao) {
		super();
		this.customerDao = customerDao;
	}
	@Override
	public DataResult<List<Customer>> getAll() {
		return new SuccessDataResult<List<Customer>>
			(this.customerDao.findAll(),"Data listelendi");
	}
	@Override
	 public result add(Customer customer) {
        try {
            Customer savedCustomer = customerDao.save(customer);
            return new result(true, "Customer added successfully. ID: " + savedCustomer.getId());
        }
        catch (Exception e) {
            return new result(false, "An error occurred while adding the customer.");
        }
	}
	
	@Override
	public DataResult<Customer> getByCustomerName(String customerName) {
		return new SuccessDataResult<Customer>
		(this.customerDao.getByCustomerName(customerName),"Data listelendi");
	}

	@Override
	public DataResult<List<Customer>> getAllSorted() {
		Sort sort = Sort.by(Sort.Direction.ASC,"customerName");
		return new SuccessDataResult<List<Customer>>
		(this.customerDao.findAll(sort)," Başarılı");
	}
	@Override
	public DataResult<List<Customer>> getAll(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo-1, pageSize);
		
		return new SuccessDataResult<List<Customer>>
		(this.customerDao.findAll(pageable).getContent());
	}
	@Override
	public result update(Customer customer) {
	        Customer existingCustomer = customerDao.findById(customer.getId()).orElse(null);
	        if (existingCustomer == null) {
	            return new ErrorResult("Müşteri bulunamadı");
	        }

	        existingCustomer.setCustomerName(customer.getCustomerName());
	        existingCustomer.setCustomerEmail(customer.getCustomerEmail());

	        customerDao.save(existingCustomer);
	        return new SuccessResult("Müşteri bilgileri güncellendi");
	    }


}
