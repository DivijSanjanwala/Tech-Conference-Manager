package GUIPresenters;

import GUI.mainView;
import useCases.*;

import java.util.ArrayList;
import java.util.HashMap;

public class MessagePresenter {
    /**
     * Performs actions based on the message option selected by the user
     * @param messagePanelChoice the message option selected by the user
     *
     */
    public static void nextMessagePanel(String messagePanelChoice){
        switch (messagePanelChoice){
            case "Display all messages":
                mainView.toPanel("Display Messages");
                break;
            case "Message a speaker":
                mainView.toMessageUserPanel("speaker");
                break;
            case "Message an attendee":
                mainView.toMessageUserPanel("attendee");
                break;
            case "Message all attendees":
                mainView.toMessageAllUsersPanel("attendee");
                break;

            case "Message all speakers":
                mainView.toMessageAllUsersPanel("speaker");
                break;

            case "Message all attendees of an event":
                mainView.toPanel("Message Attendees of An Event");
                break;

            case "Message all attendees of the talk":
                mainView.toPanel("Message Attendees of A Talk");
                break;
        }
    }

    /**
     * Adds the reply to the reply panel
     * @param content content of the reply
     * @param replyID replyID of the message that is to be replied to
     * @param recipientName name of the message reciever
     */
    public static void replyToMessage(String content, int replyID, String recipientName){
        mainView.toReplyPanel(content, replyID, recipientName);
    }

    /**
     * Makes a new reply to a message
     * @param recieverID ID of the message reciever
     * @param content content of the message reply
     * @param replyID replyID of the message
     */
    public static void reply(int recieverID, int replyID, String content){
        MessageManager.makeNewMessage(AuthManager.getLoggedInUserID(), recieverID, replyID, content);
    }
    /**
     * Makes a new message
     * @param receiver username of the message reciever
     * @param content content of the message
     */
    public static void sendMessage(String receiver, String content) {
        MessageManager.makeNewMessage(AuthManager.getLoggedInUserID(),
                UserManager.giveIDOfUsername(receiver), -1, content);
    }
    /**
     * Sends a message to multiple users of the same type
     * @param type type of the user
     * @param content content of the message
     */
    public static void sendMessageToAllType(String type, String content){
        ArrayList<String> users = UserTypeManager.getUsersByType(type);
        for (String u: users){
            sendMessage(u, content);
        }
    }

    /**
     * Sends a message to all attendees of a talk
     * @param talkID ID of the talk
     * @param content content of the message reply
     */
    public static void sendMessageToAllAttendeesOfTalk(int talkID, String content){
        ArrayList<Integer> attending = EventManager.getAttendingSpecificEvent(talkID);
        for (int i: attending){
            MessageManager.makeNewMessage(AuthManager.getLoggedInUserID(), i, -1, content);
        }
    }

    /**
     * Returns a hashmap of all users in the current users' inbox
     */
    public static HashMap<Integer, String> getUsernamesFromInbox(){
        HashMap<Integer, String> userHash = new HashMap<>();
        ArrayList<Integer> messages = MessageManager.getAllReceivedMessagesIDs(
                AuthManager.getLoggedInUserID());
        String ID = "";
        for (int id: messages){
            if (MessageStatusManager.getStatusOfMessage(id) == 0 || MessageStatusManager.getStatusOfMessage(id) == 1){
                ID = Integer.toString(id);
                userHash.put(id,
                        "ID: " + ID + " " + UserManager.giveUsername(MessageManager.getSendersID(id)));
            }

        }
        return userHash;
    }

    /**
     * Returns a hashmap of all users in the current users' archived messages
     */
    public static HashMap<Integer, String> getUsernamesFromArchived(){
        HashMap<Integer, String> userHash = new HashMap<>();
        ArrayList<Integer> messages = MessageManager.getAllReceivedMessagesIDs(
                AuthManager.getLoggedInUserID());
        String ID = "";
        for (int id: messages){
            if (MessageStatusManager.getStatusOfMessage(id) == -2){
                ID = Integer.toString(id);
                userHash.put(id,
                        "ID: " + ID + " " + UserManager.giveUsername(MessageManager.getSendersID(id)));
            }

        }
        return userHash;
    }

    /**
     * Returns the list of all talks by the speaker
     */
    public static String[] getTalksBySpeaker(){
        return EventManager.getAllEventsBySpeaker(AuthManager.getLoggedInUserID()).toArray(new String[0]);
    }

    /**
     * Returns a list of all talks
     */
    public static String[] getAllTalks(){
        return EventManager.getAllEventsTitles().toArray(new String[0]);
    }

    /**
     * Returns the ID of the talk using given title
     * @param title Title of the talk
     */
    public static int getIDOfTalkByTitle(String title){
        return EventManager.giveEventIDOfTitle(title);
    }

    /**
     * Returns the status of the message
     * @param ID ID of the message whose status is returned
     */
    public static String getMessageStatus(int ID){
        if(MessageStatusManager.getStatusOfMessage(ID) == 0) {
            return "Unread";
        } else if (MessageStatusManager.getStatusOfMessage(ID) == 1){
            return "Read";
        } else if (MessageStatusManager.getStatusOfMessage(ID) == -2){
            return "Archived";
        }
        return "";
    }
    /**
     * This function calls the method that marks the message as archived from the Manager.
     * @param messageID The ID of the message.
     */
    public static void markArchived(int messageID) {MessageStatusManager.markMessageAsArchived(messageID);}

    /**
     * Marks the message as read
     * @param ID ID of the message that is to be marked
     */
    public static void markRead(int ID) {MessageStatusManager.markMessageAsRead(ID);}

    /**
     * Marks the message as unread
     * @param ID ID of the message that is to be marked
     */
    public static void markUnread(int ID){MessageStatusManager.markMessageAsUnread(ID);}

    /**
     * Returns the message content
     * @param ID ID of the message whose contents are supposed to be returned
     */
    public static String getMessageContent(int ID){return MessageManager.getContent(ID);}

    /**
     * Returns the message options for the user who is currently logged in
     */
    public static String[] messageOptions(){
        return UserManager.getMessageOptionsList(AuthManager.getLoggedInUserID());
    }

    /**
     * Returns an array of the usernames based on type
     * @param type type of the user
     */
    public static String[] usersUsernames(String type) {
        ArrayList<String> users = UserTypeManager.getUsersByType(type);
        users.remove(UserManager.giveUsername(AuthManager.getLoggedInUserID()));
        return users.toArray(new String[0]);
    }
    /**
     * Returns the ID of the user using the given user's name
     * @param name the name of the user
     */
    public static int getUsersID(String name){
        return UserManager.giveIDOfUsername(name);
    }

    /**
     * Returns the senders username
     * @param ID ID of the message
     */
    public static String getSendersUsername(int ID){
        return UserManager.getUsernameFromID(MessageManager.getSendersID(ID));
    }

    /**
     * This function deleted the message from the program, by calling the method removeMessage from the Manager.
     * @param ID The ID of the message.
     */
    public static void deleteMessage(int ID) {
        MessageManager.removeMessage(ID);
    }


    /**
     * Displays the message
     * @param message the message being displayed
     */
    public static void createMessagePopUp(String message){
        mainView.createPopUp(message);
        mainView.toPanel("Messages");;
    }
}
