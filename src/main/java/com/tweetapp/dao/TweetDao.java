package com.tweetapp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.tweetapp.entity.Tweet;
import static com.tweetapp.utils.TweetAppConstant.COM_MYSQL_CJ_JDBC_DRIVER;
import static com.tweetapp.utils.TweetAppConstant.MYSQL_LOGIN;
import static com.tweetapp.utils.TweetAppConstant.UNABLE_TO_CREATE_TABLE;
import static com.tweetapp.utils.TweetAppConstant.PLEASE_TRY_AGAIN;
import static com.tweetapp.utils.TweetAppConstant.TWEET_NOT_UPLOADED;

/**
 * @author Manan Shah
 *
 */
public class TweetDao {

	private static final String TWEET_UPLOADED_SUCCESSFULLY = "Tweet uploaded successfully";
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	public TweetDao() {
		try {
			Class.forName(COM_MYSQL_CJ_JDBC_DRIVER).newInstance();
			connect = DriverManager.getConnection(MYSQL_LOGIN);
			String sqlCreate = "CREATE TABLE IF NOT EXISTS TWEET" + "   (id  INTEGER AUTO_INCREMENT PRIMARY KEY,"
					+ "   userid INTEGER," + "   tweet VARCHAR(100)," + "   created DATE)";
			Statement stmt = connect.createStatement();
			stmt.execute(sqlCreate);
		} catch (Exception e) {
			System.out.println(UNABLE_TO_CREATE_TABLE + "tweet");
		}
	}

	public boolean createTweet(Tweet tweet) {
		try {
			preparedStatement = connect.prepareStatement("insert into tweet(userid,tweet,created) values(?,?,?)");
			preparedStatement.setInt(1, tweet.getUserId());
			preparedStatement.setString(2, tweet.getTweet());
			preparedStatement.setDate(3, tweet.getCreated());
			preparedStatement.executeUpdate();
			System.out.println(TWEET_UPLOADED_SUCCESSFULLY);
		} catch (SQLException e) {
			System.out.println(TWEET_NOT_UPLOADED);
		}
		return true;
	}

	public ResultSet getAllTweets() {

		try {
			statement = connect.createStatement();
			String sql = "select tweet.created,user.firstname,tweet.tweet from tweet inner join user on user.id=tweet.userid";
			resultSet = statement.executeQuery(sql);
			return resultSet;
		} catch (Exception e) {
			System.out.println(PLEASE_TRY_AGAIN);
			return null;
		}
	}

	public ResultSet getTweetsByUserId(int userId) {

		try {
			statement = connect.createStatement();
			String sql = "select tweet.created,user.firstname,tweet.tweet from user left join tweet on user.id=tweet.userid where user.id="
					+ userId;
			resultSet = statement.executeQuery(sql);

			return resultSet;
		} catch (Exception e) {
			System.out.println(PLEASE_TRY_AGAIN);
			return null;
		}
	}
}
