package com.tweetapp.service;

import static com.tweetapp.utils.TweetAppConstant.PLEASE_TRY_AGAIN;

import java.sql.ResultSet;

import com.tweetapp.dao.TweetDao;
import com.tweetapp.entity.Tweet;

/**
 * @author Manan Shah
 *
 */
public class TweetService {

	private static final String PIPE_DELIMETER = " | ";
	private static final String DASH_FORMAT = "-----------------------------------------------";
	private static final String NO_TWEETS_FOUND = "No tweets found..";
	private static final String FIRSTNAME = "FirstName";
	private static final String CREATED = "Created";
	private static final String TWEET = "Tweet";
	TweetDao tweetDao = new TweetDao();

	public boolean createTweet(Tweet tweet) {
		java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
		tweet.setCreated(date);
		tweetDao.createTweet(tweet);
		return true;
	}

	public boolean getAllTweets() {

		ResultSet rs = tweetDao.getAllTweets();
		try {
			if (rs.next()) {
				System.out.println(DASH_FORMAT);
				System.out.printf("%1s %13s %14s \n", CREATED, FIRSTNAME, TWEET);
				System.out.println(DASH_FORMAT);
			} else {
				System.out.println(NO_TWEETS_FOUND);
			}
			do {
				System.out.println(rs.getDate(CREATED) + PIPE_DELIMETER + rs.getString(FIRSTNAME) + PIPE_DELIMETER
						+ rs.getString(TWEET));
			} while (rs.next());
			System.out.println(DASH_FORMAT);
		} catch (Exception e) {
			System.out.println(PLEASE_TRY_AGAIN);
		}
		return true;
	}

	public boolean getTweetsByUserId(int userId) {

		ResultSet rs = tweetDao.getTweetsByUserId(userId);
		try {
			if (rs.next()) {
				System.out.println(DASH_FORMAT);
				System.out.printf("%1s %13s %14s \n", CREATED, FIRSTNAME, TWEET);
				System.out.println(DASH_FORMAT);
			} else {
				System.out.println(NO_TWEETS_FOUND);
			}
			do {
				System.out.println(rs.getDate(CREATED) + PIPE_DELIMETER + rs.getString(FIRSTNAME) + PIPE_DELIMETER
						+ rs.getString(TWEET));
			} while (rs.next());
			System.out.println(DASH_FORMAT);
		} catch (Exception e) {
			System.out.println(PLEASE_TRY_AGAIN);
		}
		return true;
	}
}
