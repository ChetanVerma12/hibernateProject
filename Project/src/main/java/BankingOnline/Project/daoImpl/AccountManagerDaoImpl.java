//package BankingOnline.Project.daoImpl;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.transaction.Transactional;
//
//import BankingOnline.Project.dao.AccountManagerDao;
//import BankingOnline.Project.exception.AccountManagerException;
//import BankingOnline.Project.model.Accounts;
//
//public class AccountManagerDaoImpl implements AccountManagerDao{
//
//	@Override
//	public void credit_money(long account_number, double amount) throws AccountManagerException {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void debit_money(long account_number, double amount) throws AccountManagerException {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void transfer_money(long account_number, double amount) throws AccountManagerException {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public double getBalance(long account_number) throws AccountManagerException {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	
//
//}


package BankingOnline.Project.daoImpl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import BankingOnline.Project.dao.AccountManagerDao;
import BankingOnline.Project.exception.AccountManagerException;
import BankingOnline.Project.model.Accounts;

@Transactional
public class AccountManagerDaoImpl implements AccountManagerDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void credit_money(long account_number, double amount) throws AccountManagerException {
        try {
            Accounts account = entityManager.find(Accounts.class, account_number);
            if (account == null) {
                throw new AccountManagerException("Account not found for account number: " + account_number);
            }
            account.setBalance(account.getBalance() + amount);
            entityManager.merge(account);
        } catch (Exception e) {
            throw new AccountManagerException("Error while crediting money: " + e.getMessage());
        }
    }

    @Override
    public void debit_money(long account_number, double amount) throws AccountManagerException {
        try {
            Accounts account = entityManager.find(Accounts.class, account_number);
            if (account == null) {
                throw new AccountManagerException("Account not found for account number: " + account_number);
            }
            if (account.getBalance() < amount) {
                throw new AccountManagerException("Insufficient balance in account number: " + account_number);
            }
            account.setBalance(account.getBalance() - amount);
            entityManager.merge(account);
        } catch (Exception e) {
            throw new AccountManagerException("Error while debiting money: " + e.getMessage());
        }
    }

    @Override
    public void transfer_money(long source_account_number, long target_account_number, double amount) throws AccountManagerException {
        try {
            // Find source account
            Accounts sourceAccount = entityManager.find(Accounts.class, source_account_number);
            if (sourceAccount == null) {
                throw new AccountManagerException("Source account not found for account number: " + source_account_number);
            }
            if (sourceAccount.getBalance() < amount) {
                throw new AccountManagerException("Insufficient balance in source account: " + source_account_number);
            }

            // Find target account
            Accounts targetAccount = entityManager.find(Accounts.class, target_account_number);
            if (targetAccount == null) {
                throw new AccountManagerException("Target account not found for account number: " + target_account_number);
            }

            // Transfer money
            sourceAccount.setBalance(sourceAccount.getBalance() - amount);
            targetAccount.setBalance(targetAccount.getBalance() + amount);

            // Persist changes
            entityManager.merge(sourceAccount);
            entityManager.merge(targetAccount);
        } catch (Exception e) {
            throw new AccountManagerException("Error while transferring money: " + e.getMessage());
        }
    }

    @Override
    public double getBalance(long account_number) throws AccountManagerException {
        try {
            Accounts account = entityManager.find(Accounts.class, account_number);
            if (account == null) {
                throw new AccountManagerException("Account not found for account number: " + account_number);
            }
            return account.getBalance();
        } catch (Exception e) {
            throw new AccountManagerException("Error while retrieving balance: " + e.getMessage());
        }
    }
}

