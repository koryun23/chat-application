package com.chat.helper;

public final class Utils {

    /**
     * @param chatName The name of the private chat. Must be of a format user1 - user2,
     *                 where user1 and user2 are the members of the chat.
     * @param username Represents one of the users in the chat.
     * @return The user other than the one specified as an argument is returned.
     */
    public static String getOtherUserFromPrivateChat(String chatName, String username) {
        int hyphenIndex = chatName.indexOf("-");
        String firstUsername = chatName.substring(0, hyphenIndex - 1);
        if(firstUsername.equals(username)) {
            return chatName.substring(hyphenIndex + 2);
        }
        return firstUsername;
    }
}
