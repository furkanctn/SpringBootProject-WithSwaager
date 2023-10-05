package JavaKodlama.northwind.DataAccess.Abstract;

import org.springframework.data.jpa.repository.JpaRepository;
import JavaKodlama.northwind.Entities.Concretes.BankAccount;
import JavaKodlama.northwind.Entities.Concretes.Customer;

public interface AccountDao extends JpaRepository<BankAccount, Integer> {
    BankAccount getBankAccountsByCustomerId(int  customerId);

}