package br.com.erudio.repository.interfaces;

import br.com.erudio.account.Account;
import br.com.erudio.repository.generic.IGenericRepository;;


public interface IAccountRepository extends IGenericRepository<Account>{

	Account save(Account account);
	Account findByEmail(String email);
}