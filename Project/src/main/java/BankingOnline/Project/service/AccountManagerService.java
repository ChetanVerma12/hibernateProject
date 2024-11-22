package BankingOnline.Project.service;

import BankingOnline.Project.exception.AccountManagerException;

public interface AccountManagerService {
	void credit_money(long account_number,double amount) throws AccountManagerException;
	void debit_money(long account_number,double amount) throws AccountManagerException;
	void transfer_money(long account_number,double amount) throws AccountManagerException;
	double getBalance(long account_number) throws AccountManagerException;
}
