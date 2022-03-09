/**
 * @author Manan Shah
 *
 */
package com.tweetapp.utils;

public class TweetAppConstant {
	//Create database as tweetapp and change id and password for same.
	public static final String MYSQL_LOGIN = "jdbc:mysql://localhost/tweetapp?user=root&password=root123";
	public static final String COM_MYSQL_CJ_JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String SOMETHING_WENT_WRONG = "Something went wrong! ";
	public static final String UNABLE_TO_CREATE_TABLE = SOMETHING_WENT_WRONG + "Unable to create table ";
	public static final String PLEASE_TRY_AGAIN = SOMETHING_WENT_WRONG + "Please try again..";
	public static final String TWEET_NOT_UPLOADED = SOMETHING_WENT_WRONG + "Tweet not uploaded..";
	public static final String INVALID_EMAIL_USER_PROVIDED = "Invalid email/user provided";
}
