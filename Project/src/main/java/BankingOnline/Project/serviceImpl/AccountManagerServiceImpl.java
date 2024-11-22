package BankingOnline.Project.serviceImpl;

import BankingOnline.Project.exception.AccountManagerException;
import BankingOnline.Project.service.AccountManagerService;
import BankingOnline.Project.dao.AccountManagerDao;
import BankingOnline.Project.daoImpl.AccountManagerDaoImpl;


public class AccountManagerServiceImpl implements AccountManagerService{
	AccountManagerDao dao = new AccountManagerDaoImpl();

	@Override
	public void credit_money(long account_number,double amount) throws AccountManagerException {
		dao.credit_money(account_number, amount);
		
	}

	@Override
	public void debit_money(long account_number,double amount) throws AccountManagerException {
	  dao.debit_money(account_number, amount);
		
	}

	@Override
	public void transfer_money(long account_number,double amount) throws AccountManagerException {
	 dao.transfer_money(account_number, amount);
		
	}

	@Override
	public double getBalance(long account_number) throws AccountManagerException {
		// TODO Auto-generated method stub
		return dao.getBalance(account_number);
	}

}
