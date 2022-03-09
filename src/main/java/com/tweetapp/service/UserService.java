package com.tweetapp.service;

import java.sql.ResultSet;
import java.util.Map;

import com.tweetapp.dao.UserDao;
import com.tweetapp.entity.User;
import static com.tweetapp.utils.TweetAppConstant.PLEASE_TRY_AGAIN;
/**
 * @author Manan Shah
 *
 */
public class UserService {

	UserDao userDao = new UserDao();

	public Boolean registerUser(User user) {
		boolean regStatus = userDao.registerUser(user);
		if (regStatus == true) {
			return regStatus;
		}
		return false;
	}

	// login
	public Map<String, Integer> login(String email, String password) {
		return userDao.login(email, password);
	}

	// logout
	public boolean logout(int userId) {
		return userDao.logout(userId);
	}

	// forgot password
	public void forgot(String email, String newPassword) {
		userDao.forgotPassword(email, newPassword);
	}

	// reset password
	public Boolean updatePassword(int userId, String oldPassword, String newPassword) {
		return userDao.updatePassword(userId, oldPassword, newPassword);
	}

	// get All users
	public boolean getAllUsers() {
		ResultSet rs = userDao.getAllUsers();
		try {
			while (rs.next()) {
				if (rs.getString("firstname") == null && rs.getString("lastname") == null) {
					System.out.println("No users found..");
					return true;
				}
				System.out.println(rs.getString("firstname") + " " + rs.getString("lastname"));
			}
		} catch (Exception e) {
			System.out.println(PLEASE_TRY_AGAIN);
		}
		return true;
	}

	public boolean getEmail(String email) {
		ResultSet rs = userDao.getEmail();
		boolean flag = false;
		try {
			while (rs.next()) {
				flag = rs.getString("email").equalsIgnoreCase(email) ? true : false;
				if (flag)
					System.out.println("User already Exist " + rs.getString("email"));
				break;
			}
		} catch (Exception e) {
			System.out.println(PLEASE_TRY_AGAIN);
		}
		return flag;
	}
}
