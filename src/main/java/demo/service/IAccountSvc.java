package demo.service;

import demo.entity.Account;

public interface IAccountSvc extends IBaseSvc<Account, Long> {
	Account findByUsername(String username);

}
