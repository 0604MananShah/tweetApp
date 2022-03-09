package com.tweetapp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tweetapp.entity.User;
import static com.tweetapp.utils.TweetAppConstant.COM_MYSQL_CJ_JDBC_DRIVER;
import static com.tweetapp.utils.TweetAppConstant.MYSQL_LOGIN;
import static com.tweetapp.utils.TweetAppConstant.UNABLE_TO_CREATE_TABLE;
import static com.tweetapp.utils.TweetAppConstant.PLEASE_TRY_AGAIN;
import static com.tweetapp.utils.TweetAppConstant.INVALID_EMAIL_USER_PROVIDED;

/**
 * @author Manan Shah
 *
 */
public class UserDao {

	private static final String UPDATE_USER_SET_STATUS_WHERE_ID = "UPDATE user SET status = ? where id = ?";
	private static final String INVALID_PASSWORD = "Invalid password...";
	private static final String LOGGED_OUT = "Logged out...";
	private static final String PASSWORD_CHANGED_SUCCESSFULLY = "Password changed successfully...";

	private static final String SOMETHING_WENT_WRONG_USER_NOT_REGISTERED = "Something went wrong! User not registered..";
	List<User> userList = new ArrayList<User>();
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	public UserDao() {
		try {
			Class.forName(COM_MYSQL_CJ_JDBC_DRIVER).newInstance();
			connect = DriverManager.getConnection(MYSQL_LOGIN);
			String sqlCreate = "CREATE TABLE IF NOT EXISTS USER"
					+ "   (id            INTEGER AUTO_INCREMENT PRIMARY KEY," + "   firstname          VARCHAR(20),"
					+ "   lastname           VARCHAR(20)," + "   gender           VARCHAR(20),"
					+ "   dob     VARCHAR(15)," + "   email VARCHAR(30)," + "	  password VARCHAR(15),"
					+ "	  status BOOLEAN)";

			Statement stmt = connect.createStatement();
			stmt.execute(sqlCreate);
		} catch (Exception e) {
			System.out.println(UNABLE_TO_CREATE_TABLE + "User");
		}
	}

	// Register user
	public Boolean registerUser(User user) {
		try {
			PreparedStatement pstmt = connect.prepareStatement(
					"insert into user(firstname,lastname,gender,dob,email,password,status) values (?,?,?,?,?,?,?)");
			pstmt.setString(1, user.getFirstName());
			pstmt.setString(2, user.getLastName());
			pstmt.setString(3, user.getGender());
			pstmt.setString(4, user.getDob());
			pstmt.setString(5, user.getEmail());
			pstmt.setString(6, user.getPassword());
			pstmt.setBoolean(7, false);
			pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println(SOMETHING_WENT_WRONG_USER_NOT_REGISTERED);
		}
		return true;
	}

	// Login
	public Map<String, Integer> login(String email, String password) {
		Map<String, Integer> res = new HashMap<String, Integer>();
		res.put("status", -1);
		try {
			statement = connect.createStatement();
			String sql = "SELECT id,email,password from user";
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				if (email.equals(resultSet.getString("email")) && password.equals(resultSet.getString("password"))) {
					res.put("userId", resultSet.getInt("id"));
					res.put("status", 1);
					preparedStatement = connect.prepareStatement(UPDATE_USER_SET_STATUS_WHERE_ID);
					preparedStatement.setBoolean(1, true);
					preparedStatement.setInt(2, resultSet.getInt("id"));
					preparedStatement.executeUpdate();
					return res;
				}
			}
		} catch (Exception e) {
			System.out.println(PLEASE_TRY_AGAIN);
		}
		return res;
	}

	// Forgot password
	public void forgotPassword(String email, String newPassword) {
		try {
			preparedStatement = connect.prepareStatement("UPDATE user SET password = ? where email = ?");
			preparedStatement.setString(1, newPassword);
			preparedStatement.setString(2, email);
			if (preparedStatement.executeUpdate() == 1) {
				System.out.println(PASSWORD_CHANGED_SUCCESSFULLY);
			} else {
				System.out.println(INVALID_EMAIL_USER_PROVIDED);
			}
		} catch (Exception e) {
			System.out.println(PLEASE_TRY_AGAIN);
		}

	}

	// Update password
	public Boolean updatePassword(int userId, String oldPassword, String newPassword) {
		try {
			preparedStatement = connect.prepareStatement("UPDATE user SET password = ? where id = ? AND password = ?");
			preparedStatement.setString(1, newPassword);
			preparedStatement.setInt(2, userId);
			preparedStatement.setString(3, oldPassword);
			if (preparedStatement.executeUpdate() == 1) {
				System.out.println(PASSWORD_CHANGED_SUCCESSFULLY);
			} else {
				System.out.println(INVALID_PASSWORD);
			}
		} catch (Exception e) {
			System.out.println(PLEASE_TRY_AGAIN);
		}
		return true;
	}

	// retrieve all users
	public ResultSet getAllUsers() {
		try {
			statement = connect.createStatement();
			String sql = "SELECT firstname, lastname FROM user";
			resultSet = statement.executeQuery(sql);
			return resultSet;
		} catch (Exception e) {
			System.out.println(PLEASE_TRY_AGAIN);
			return null;
		}
	}

	// Logout
	public boolean logout(int userId) {
		try {
			preparedStatement = connect.prepareStatement(UPDATE_USER_SET_STATUS_WHERE_ID);
			preparedStatement.setBoolean(1, false);
			preparedStatement.setInt(2, userId);
			if (preparedStatement.executeUpdate() == 1) {
				System.out.println(LOGGED_OUT);
				return true;
			} else {
				System.out.println(INVALID_EMAIL_USER_PROVIDED);
				return false;
			}
		} catch (Exception e) {
			System.out.println(PLEASE_TRY_AGAIN);
			return false;
		}
	}

	public ResultSet getEmail() {
		try {
			statement = connect.createStatement();
			String sql = "SELECT email FROM user";
			resultSet = statement.executeQuery(sql);
			return resultSet;
		} catch (Exception e) {
			System.out.println(PLEASE_TRY_AGAIN);
			return null;
		}
	}

}
