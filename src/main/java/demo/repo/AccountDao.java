package demo.repo;

import org.springframework.data.repository.CrudRepository;

import demo.entity.Account;

public interface AccountDao extends CrudRepository<Account, Long> {
	Account findByUsername(String username);
}
