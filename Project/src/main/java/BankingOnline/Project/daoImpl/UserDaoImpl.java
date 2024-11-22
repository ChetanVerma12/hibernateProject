//package BankingOnline.Project.daoImpl;
//
//import BankingOnline.Project.dao.UserDao;
//import BankingOnline.Project.exception.UserException;
//
//public class UserDaoImpl implements UserDao {
//
//	@Override
//	public void register() throws UserException {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public String login() throws UserException {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public boolean user_exist(String email) throws UserException {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//}

package BankingOnline.Project.daoImpl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import BankingOnline.Project.dao.UserDao;
import BankingOnline.Project.exception.UserException;
import BankingOnline.Project.model.User;

@Transactional
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void register(User user) throws UserException {
        try {
            // Check if the user already exists
            if (user_exist(user.getEmail())) {
                throw new UserException("User already exists with email: " + user.getEmail());
            }

            // Persist the new user
            entityManager.persist(user);
        } catch (Exception e) {
            throw new UserException("Error while registering user: " + e.getMessage());
        }
    }

    @Override
    public String login(String email, String password) throws UserException {
        try {
            User user = entityManager
                .createQuery("SELECT u FROM User u WHERE u.email = :email AND u.password = :password", User.class)
                .setParameter("email", email)
                .setParameter("password", password)
                .getSingleResult();

            if (user == null) {
                throw new UserException("Invalid email or password.");
            }

            return "Login successful for user: " + email;
        } catch (Exception e) {
            throw new UserException("Error while logging in: " + e.getMessage());
        }
    }

    @Override
    public boolean user_exist(String email) throws UserException {
        try {
            Long count = entityManager
                .createQuery("SELECT COUNT(u) FROM User u WHERE u.email = :email", Long.class)
                .setParameter("email", email)
                .getSingleResult();

            return count > 0;
        } catch (Exception e) {
            throw new UserException("Error while checking user existence: " + e.getMessage());
        }
    }
}
