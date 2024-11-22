package BankingOnline.Project.service;

import BankingOnline.Project.exception.UserException;

public interface UserService {
	void register() throws UserException;
	   String login() throws UserException;
	   boolean user_exist(String email) throws UserException;
}

