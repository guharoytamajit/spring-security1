package demo.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import demo.entity.Account;
import demo.exception.AccountAlreadyExists;
import demo.repo.AccountDao;

@Transactional
public class AccountSvcImpl implements IAccountSvc {

	@Autowired
	private AccountDao accountDao;

	public Account save(Account account) {
		return accountDao.save(account);
	}

	public Account update(Account account) {
		if (accountDao.exists(account.getId())) {
			new AccountAlreadyExists(account);
		}
		return accountDao.save(account);
	}

	public Account findOne(Long id) {
		return accountDao.findOne(id);
	}

	public Iterable<Account> findByAll() {
		return accountDao.findAll();
	}

	public void deleteAll() {
		accountDao.deleteAll();

	}

	public void delete(Long id) {
		accountDao.delete(id);
	}

	public Account findByUsername(String username) {
		return accountDao.findByUsername(username);
	}

}
