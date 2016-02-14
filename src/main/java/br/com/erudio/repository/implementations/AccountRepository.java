package br.com.erudio.repository.implementations;

import javax.inject.Inject;
import javax.persistence.PersistenceException;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.erudio.account.Account;
import br.com.erudio.repository.generic.GenericRepository;
import br.com.erudio.repository.interfaces.IAccountRepository;

@Repository
@Transactional(readOnly = true)
public class AccountRepository extends GenericRepository<Account> implements IAccountRepository{

	private static final long serialVersionUID = 1L;

	public AccountRepository() {
		super(Account.class);
	}
	
	@Inject
	private PasswordEncoder passwordEncoder;
	
	@Override
	@Transactional
	public Account save(Account account) {
		account.setPassword(passwordEncoder.encode(account.getPassword()));
		entityManager.persist(account);
		return account;
	}
	
	@Override
	public Account findByEmail(String email) {
		try {
			return entityManager.createNamedQuery("select a from Account a where a.email = :email", Account.class)
					.setParameter("email", email)
					.getSingleResult();
		} catch (PersistenceException e) {
			return null;
		}
	}
}