package JavaKodlama.northwind.Api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import JavaKodlama.northwind.Business.Abstract.BankAccountService;
import JavaKodlama.northwind.Core.utilities.results.DataResult;
import JavaKodlama.northwind.Core.utilities.results.SuccessDataResult;
import JavaKodlama.northwind.Core.utilities.results.result;
import JavaKodlama.northwind.Entities.Concretes.BankAccount;

@RestController
@RequestMapping("/api/bank-accounts")
public class BankAccountController {

    private final BankAccountService bankAccountService;

    @Autowired
    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }
    
	@GetMapping("/customer/{customerId}")
	public DataResult<BankAccount>getBankAccountsByCustomerId(@RequestParam int customerId){
		return this.bankAccountService.getBankAccountsByCustomerId(customerId);
	}
	@DeleteMapping("/{accountId}")
    public ResponseEntity<result> removeBankAccount(@PathVariable int accountId) {
        result result = bankAccountService.removeBankAccount(accountId);
        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }
    @PostMapping("/transfer-balance")
    public result transferBalance(
            @RequestParam int sourceAccountId,
            @RequestParam int targetAccountId,
            @RequestParam Double amount
    		) {
        return bankAccountService.transferBalance(sourceAccountId, targetAccountId, amount);
    }

}
