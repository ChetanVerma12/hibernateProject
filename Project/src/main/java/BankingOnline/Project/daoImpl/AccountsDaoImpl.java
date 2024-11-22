//package BankingOnline.Project.daoImpl;
//
//import BankingOnline.Project.dao.AccountsDao;
//import BankingOnline.Project.exception.AccountsException;
//
//public class AccountsDaoImpl implements AccountsDao{
//
//	@Override
//	public long open_account(String email) throws AccountsException {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public long getAccount_number(String email) throws AccountsException {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public long generateAccountNumber() throws AccountsException {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public boolean account_exist(String email) throws AccountsException {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//}


package BankingOnline.Project.daoImpl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import BankingOnline.Project.dao.AccountsDao;
import BankingOnline.Project.exception.AccountsException;
import BankingOnline.Project.model.Accounts;

@Transactional
public class AccountsDaoImpl implements AccountsDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public long open_account(String email) throws AccountsException {
        try {
            // Check if account already exists
            if (account_exist(email)) {
                throw new AccountsException("Account already exists for email: " + email);
            }

            // Generate a unique account number
            long accountNumber = generateAccountNumber();

            // Create a new account entity
            Accounts newAccount = new Accounts();
            newAccount.setEmail(email);
            newAccount.setAccountNumber(accountNumber);
            newAccount.setBalance(0.0);

            // Persist the account
            entityManager.persist(newAccount);

            return accountNumber;
        } catch (Exception e) {
            throw new AccountsException("Error while opening account: " + e.getMessage());
        }
    }

    @Override
    public long getAccount_number(String email) throws AccountsException {
        try {
            Accounts account = entityManager
                .createQuery("SELECT a FROM Accounts a WHERE a.email = :email", Accounts.class)
                .setParameter("email", email)
                .getSingleResult();

            if (account == null) {
                throw new AccountsException("No account found for email: " + email);
            }

            return account.getAccountNumber();
        } catch (Exception e) {
            throw new AccountsException("Error while retrieving account number: " + e.getMessage());
        }
    }

    @Override
    public long generateAccountNumber() throws AccountsException {
        try {
            // Generate a random 10-digit account number
            return (long) (Math.random() * 1_000_000_0000L) + 1_000_000_0000L;
        } catch (Exception e) {
            throw new AccountsException("Error while generating account number: " + e.getMessage());
        }
    }

    @Override
    public boolean account_exist(String email) throws AccountsException {
        try {
            Long count = entityManager
                .createQuery("SELECT COUNT(a) FROM Accounts a WHERE a.email = :email", Long.class)
                .setParameter("email", email)
                .getSingleResult();

            return count > 0;
        } catch (Exception e) {
            throw new AccountsException("Error while checking account existence: " + e.getMessage());
        }
    }
}

