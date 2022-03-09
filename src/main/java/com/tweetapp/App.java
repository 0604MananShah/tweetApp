package com.tweetapp;

import java.util.Map;
import java.util.Scanner;

import com.tweetapp.utils.RegisterUtil;
import static com.tweetapp.utils.TweetAppConstant.INVALID_EMAIL_USER_PROVIDED;

/**
 * @author Manan Shah
 *
 */
public class App {
	private static final String INVALID_OPTION = "Invalid option. Please try again.";
	public static boolean loggedInStatus = false;
	public static int userId;
	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		RegisterUtil userUtil = new RegisterUtil();

		if (!loggedInStatus) {
			System.out.println("Welcome. Please choose from the options below:");
			System.out.println("_____________________________________________");
			System.out.println("1.Register\n2.Login\n3.Forgot password");

			int option = scanner.nextInt();
			switch (option) {
			case 1:
				userUtil.registerUser();
				main(args);
				break;
			case 2:
				Map<String, Integer> res = userUtil.login();
				if (res.get("status") == 1) {
					loggedInStatus = true;
					userId = res.get("userId");
				} else {
					System.out.println(INVALID_EMAIL_USER_PROVIDED);
				}
				main(args);
				break;

			case 3:
				userUtil.forgotPassword();
				main(args);
				break;
			default:
				System.out.println(INVALID_OPTION);
				main(args);

			}
		} else {
			System.out.println("Logged in successfully.. Please choose from the options below:\n");
			System.out.println("____________________________________________");
			System.out.println(
					"1.Post a tweet\n2.View my tweets\n3.View all tweets\n4.View all users\n5.Reset password\n6.Logout");

			int option = scanner.nextInt();
			switch (option) {
			case 1:
				userUtil.createTweet(userId);
				main(args);
				break;
			case 2:
				userUtil.getMyTweets(userId);
				main(args);
				break;
			case 3:
				userUtil.getAllTweets();
				main(args);
				break;
			case 4:
				userUtil.getAllUsers();
				main(args);
				break;
			case 5:
				userUtil.updateUser(userId);
				main(args);
				break;
			case 6:
				if (userUtil.logout(userId)) {
					loggedInStatus = false;
				}
				main(args);
				break;
			default:
				System.out.println(INVALID_OPTION);
				main(args);
			}
		}
	}
}
