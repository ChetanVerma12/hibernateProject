package BankingOnline.Project.service;

import BankingOnline.Project.exception.AccountsException;

public interface AccountsService {
	long open_account(String email) throws AccountsException;
	long getAccount_number(String email) throws AccountsException;
    long generateAccountNumber() throws AccountsException;
	boolean account_exist(String email) throws AccountsException;
}
