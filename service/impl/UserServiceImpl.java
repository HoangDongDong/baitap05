package service.impl;

import java.util.Random;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import model.User;
import service.UserService;

public class UserServiceImpl implements UserService {
	UserDao userDao = new UserDaoImpl();

	@Override
	public User login(String username, String password) {
		User user = this.get(username);
		if (user != null && password.equals(user.getPassWord())) {
			return user;
		}
		return null;
	}

	@Override
	public User get(String username) {
		return userDao.get(username);

	}

	@Override
	public void insert(User user) {
		userDao.insert(user);
	}

	@Override
	public boolean register(String email, String password, String username, String fullname, String phone) {
		if (userDao.checkExistUsername(username)) {
			return false;
		}
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);
		String strDate = date.toString();
		User us = new User();
		Random rand = new Random();
		us.setId(rand.nextInt(10000));
		us.setEmail(email);
		us.setUserName(username);
		us.setFullName(fullname);
		us.setPassWord(password);
		us.setAvatar("avt0");
		us.setRoleid(1);
		us.setPhone(phone);
		us.setCreatedDate(strDate);
		userDao.insert(us);
		return true;
	}

	@Override
	public boolean checkExistEmail(String email) {
		return userDao.checkExistEmail(email);
	}

	@Override
	public boolean checkExistUsername(String username) {
		return userDao.checkExistUsername(username);
	}

	@Override
	public boolean checkExistPhone(String phone) {
		return userDao.checkExistPhone(phone);
	}

	@Override
	public boolean updatePassword(String username, String newPassword) {
		User u = this.get(username);
		if (u == null)
			return false;
		userDao.updatePassword(username, newPassword);
		return true;
	}

}
