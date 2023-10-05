package JavaKodlama.northwind.Api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import JavaKodlama.northwind.Business.Abstract.BankAccountService;
import JavaKodlama.northwind.Business.Abstract.CustomerService;
import JavaKodlama.northwind.Core.utilities.results.DataResult;
import JavaKodlama.northwind.Core.utilities.results.SuccessDataResult;
import JavaKodlama.northwind.Core.utilities.results.result;
import JavaKodlama.northwind.Entities.Concretes.Customer;

@RestController
@RequestMapping("/api/Customers")
public class CustomersController {
	
	private CustomerService customerService;
    private BankAccountService bankAccountService;

	@Autowired
	public CustomersController(CustomerService customerService, BankAccountService bankAccountService) {
		super();
		this.customerService = customerService;
		this.bankAccountService=bankAccountService;
	}

	@GetMapping("/getall")
	public DataResult<List<Customer>> getAll(){
		return this.customerService.getAll();
	}
    @PostMapping("/add")
    public ResponseEntity<result> addCustomer(@RequestBody Customer customer) {
        result addResult = customerService.add(customer);
        return ResponseEntity.ok(addResult);
    }
	
	@GetMapping("/getByCustomerName")
	public DataResult<Customer>getByCustomerName(@RequestParam String CustomerName){
		return this.customerService.getByCustomerName(CustomerName);
	}

	@GetMapping("/getAllByPage")
	public DataResult<List<Customer>> getAll(int pageno,int pageSize) {
		return this.customerService.getAll(pageno,pageSize);

	}
	@GetMapping("/getAllSorted")
	public DataResult<List<Customer>> getAllSorted(){
		return this.customerService.getAllSorted(); 
	}
    @PutMapping("/update")
    public result updateCustomer(@RequestBody Customer customer) {
        return this.customerService.update(customer);
    }

    @PostMapping("/{customerId}/create-account")
    public result createBankAccount(
            @PathVariable int customerId,
            @RequestParam double quantity,
            @RequestParam String currency) {
        return bankAccountService.createBankAccount(quantity, currency, customerId);
    }
    

}
