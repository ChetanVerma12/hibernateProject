package BankingOnline.Project.serviceImpl;

import BankingOnline.Project.exception.UserException;
import BankingOnline.Project.service.UserService;
import BankingOnline.Project.dao.UserDao;
import BankingOnline.Project.daoImpl.UserDaoImpl;

public class UserServiceImpl implements UserService{
	UserDao dao= new UserDaoImpl();

	@Override
	public void register() throws UserException {
		// TODO Auto-generated method stub
		dao.register();
	}

	@Override
	public String login() throws UserException {
		// TODO Auto-generated method stub
		return dao.login();
	}

	@Override
	public boolean user_exist(String email) throws UserException {
		// TODO Auto-generated method stub
		return dao.user_exist(email);
	}

}
