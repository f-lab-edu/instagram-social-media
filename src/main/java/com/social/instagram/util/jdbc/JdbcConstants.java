package com.social.instagram.util.jdbc;

public class JdbcConstants {

    public static final String POST_NICE_CLICK_QUERY =
            "INSERT post_nice_click (`post_id`, `nice_click_user_id`) VALUES (:postId, :niceClickUserId)";
}