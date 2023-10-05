package JavaKodlama.northwind.DataAccess.Abstract;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import JavaKodlama.northwind.Entities.Concretes.Customer;

public interface CustomerDao extends JpaRepository<Customer, Integer> {

    Customer getByCustomerName(String customerName);



}