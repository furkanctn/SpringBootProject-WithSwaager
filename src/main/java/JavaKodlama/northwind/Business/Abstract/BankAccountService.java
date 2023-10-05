package JavaKodlama.northwind.Business.Abstract;

import JavaKodlama.northwind.Core.utilities.results.DataResult;
import JavaKodlama.northwind.Core.utilities.results.result;
import JavaKodlama.northwind.Entities.Concretes.BankAccount;

public interface BankAccountService {
    result createBankAccount(Double quantity, String currency, int customerId);
    DataResult<BankAccount> getBankAccountsByCustomerId(int customerId);
    result removeBankAccount(int accountId);
    result transferBalance(int sourceAccountId, int targetAccountId, Double quantity);


}