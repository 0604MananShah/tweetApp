# tweetApp

Tweet App is app for registered users to post new tweets, view tweets by other users.
Guest users cannot see any tweets.
The core modules of tweet app are:
1. User registration and login
2. Post tweet
3. View all tweets (all from logged in users account)
4. View users and their respective tweets
5. Forgot password option to reset password.

![image](https://user-images.githubusercontent.com/32126724/157383292-d03fa4da-75b0-483b-bb6d-8dc7912e14d7.png)

#Notes:-
Create database as tweetapp and change mysql id and password for same.

CREATE TABLE `tweet` (
  `id` int NOT NULL AUTO_INCREMENT,
  `userid` int DEFAULT NULL,
  `tweet` varchar(100) DEFAULT NULL,
  `created` date DEFAULT NULL,
  PRIMARY KEY (`id`)
)

CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `firstname` varchar(20) DEFAULT NULL,
  `lastname` varchar(20) DEFAULT NULL,
  `gender` varchar(20) DEFAULT NULL,
  `dob` varchar(15) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `password` varchar(15) DEFAULT NULL,
  `status` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
)
