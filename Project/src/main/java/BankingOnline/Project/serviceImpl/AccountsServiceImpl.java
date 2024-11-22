package BankingOnline.Project.serviceImpl;

import BankingOnline.Project.exception.AccountsException;
import BankingOnline.Project.service.AccountsService;
import BankingOnline.Project.dao.AccountsDao;
import BankingOnline.Project.daoImpl.AccountsDaoImpl;


public class AccountsServiceImpl implements AccountsService {
    AccountsService dao = new AccountsServiceImpl();
	@Override
	public long open_account(String email) throws AccountsException {
		// TODO Auto-generated method stub
		return dao.open_account(email);
	}

	@Override
	public long getAccount_number(String email) throws AccountsException {
		// TODO Auto-generated method stub
		return dao.getAccount_number(email);
	}

	@Override
	public long generateAccountNumber() throws AccountsException {
		// TODO Auto-generated method stub
		return dao.generateAccountNumber();
	}

	@Override
	public boolean account_exist(String email) throws AccountsException {
		// TODO Auto-generated method stub
		return dao.account_exist(email);
	}

}
