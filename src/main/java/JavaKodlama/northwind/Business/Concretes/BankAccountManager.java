package JavaKodlama.northwind.Business.Concretes;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import JavaKodlama.northwind.Business.Abstract.BankAccountService;
import JavaKodlama.northwind.Core.utilities.results.DataResult;
import JavaKodlama.northwind.Core.utilities.results.ErrorResult;
import JavaKodlama.northwind.Core.utilities.results.SuccessDataResult;
import JavaKodlama.northwind.Core.utilities.results.SuccessResult;
import JavaKodlama.northwind.Core.utilities.results.result;
import JavaKodlama.northwind.DataAccess.Abstract.AccountDao;
import JavaKodlama.northwind.DataAccess.Abstract.CustomerDao;
import JavaKodlama.northwind.Entities.Concretes.BankAccount;
import JavaKodlama.northwind.Entities.Concretes.Customer;



@Service
public class BankAccountManager implements BankAccountService {

    private AccountDao accountDao;
    private CustomerDao customerDao;

    @Autowired
    public BankAccountManager(AccountDao accountDao, CustomerDao customerDao) {
        this.accountDao = accountDao;
        this.customerDao = customerDao;
    }

    @Override
    public result createBankAccount(Double quantity, String currency, int customerId) {
        Customer customer = customerDao.findById(customerId).orElse(null);
        if (quantity == null) {
            return new ErrorResult("Başlangıç bakiyesi null olamaz");
        }

        if (customer == null) {
            return new ErrorResult("Müşteri bulunamadı");
        }

        if (quantity <= 0) {
            return new ErrorResult("Başlangıç bakiyesi sıfırdan büyük olmalıdır");
        }

        BankAccount newAccount = new BankAccount();
        newAccount.setQuantity(quantity);
        newAccount.setCurrency(currency);
        newAccount.setCustomer(customer);
        accountDao.save(newAccount);
        return new SuccessResult("Hesap oluşturuldu");
    }
	@Override
	public DataResult<BankAccount> getBankAccountsByCustomerId(int customerId) {
		return new SuccessDataResult<BankAccount>
		(this.accountDao.getBankAccountsByCustomerId(customerId),"Data listelendi");
	}
	
	@Override
    public result removeBankAccount(int accountId) {
        BankAccount bankAccount = accountDao.findById(accountId).orElse(null);
        if (bankAccount == null) {
            return new result(false, "Bank account not found.");
        }
        accountDao.delete(bankAccount);
        return new SuccessResult("Bank account has been successfully removed.");
    }
	@Override
    public result transferBalance(int sourceAccountId, int targetAccountId, Double quantity ) {
        BankAccount sourceAccount = accountDao.findById(sourceAccountId).orElse(null);
        BankAccount targetAccount = accountDao.findById(targetAccountId).orElse(null);

        if (sourceAccount == null || targetAccount == null) {
            return new ErrorResult("Kaynak veya hedef hesap bulunamadı");
        }

        if (!sourceAccount.getCurrency().equals(targetAccount.getCurrency())) {
            return new ErrorResult("Hesaplar farklı para birimlerine sahip, bakiye transferi yapılamaz");
        }

        if (quantity <= 0 || sourceAccount.getQuantity() < quantity) {
            return new ErrorResult("Geçersiz tutar veya yetersiz bakiye");
        }

        sourceAccount.setQuantity(sourceAccount.getQuantity() - quantity);
        targetAccount.setQuantity(targetAccount.getQuantity() + quantity);

        accountDao.save(sourceAccount);
        accountDao.save(targetAccount);

        String transferNote;
        if (sourceAccount.getCustomer().getId()!=(targetAccount.getCustomer().getId())) {
            transferNote = "Farklı kullanıcılar arasında bakiye transferi yapıldı: " ;
        } else {
            transferNote = "Aynı Kullanıcı Arasında Bakiye transferi yapıldı: " ;
        }

        return new SuccessResult(transferNote);
    }
	
    
}