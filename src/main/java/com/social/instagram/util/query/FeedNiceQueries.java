package com.social.instagram.util.query;

public class FeedNiceQueries {

    public static final String POST_NICE_CLICK_QUERY =
            "INSERT post_nice_click (`post_id`, `nice_click_user_id`) VALUES (:postId, :niceClickUserId)";

    public static final String POST_NICE_QUERY =
            "UPDATE post_nice SET nice = nice + :nice WHERE post_id = :postId";
}