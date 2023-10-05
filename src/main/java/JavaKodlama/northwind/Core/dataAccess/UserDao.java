package JavaKodlama.northwind.Core.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;

import JavaKodlama.northwind.Core.entities.User;

public interface UserDao extends JpaRepository< User, Integer> {
	User findByEmail(String email);
}
