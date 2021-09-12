package userInterfaces;

import entities.User;

import presenters.MessagePresenter;

import java.util.Scanner;

import static controllers.MessageController.*;

/** A user interface for getting the message content from the sender */

public class MessageUserInterface {

    /**
     * Load the message interface
     */
    public static void loadMessageMenu(User user) {
        System.out.println(MessagePresenter.formatOptions(user.getMessageOptions())); //retrieve message options for the current user
        Scanner s = new Scanner(System.in);
        System.out.println("Enter message option:");

        String messageChoice = s.nextLine();
        if (user.getMessageOptions().contains(messageChoice)) {
            switch (messageChoice) {
                case "Back":
                    MenuUserInterface.loadMenu(user);
                    break;
                case "Display all messages":
                    System.out.println(MessagePresenter.displayReceivedMessagesOfUser(user));
                    break;
                case "Message all attendees of the talk":
                    System.out.println("Please enter the title of the talk you " +
                            "would like to message the attendees of:");
                    String eventTitle = s.nextLine();
                    String contentSpecificEvent = getMessageContent();
                    //method to message all the attendees of a talk
                    messageAllAttendeesOfTalk(user.getUsername(), eventTitle, contentSpecificEvent);
                    break;
                case "Respond to an attendee":
                case "Message a specific speaker" : //for organizers
                case "Message an attendee": //for attendees
                case "Message a specific attendee":
                case "Message a speaker":     //for attendee
                    System.out.println("Please enter the receiver's username");
                    String receiverUsername = s.nextLine();
                    System.out.println("Please enter the ID of the message you're replying to " +
                            "or enter -1 if you're starting a new message");
                    int replyToID = s.nextInt();
                    String content = getMessageContent();

                    createNewMessage(user.getUsername(), receiverUsername, replyToID, content);
                    break;
                case "Message all speakers":
                    String contentAllSpeakers = getMessageContent();
                    messageAllSpeakers(user.getUsername(), contentAllSpeakers);
                    break;
                case "Message all attendees":
                    String contentAllAttendees = getMessageContent();
                    messageAllAttendees(user.getUsername(), contentAllAttendees);
                    break;

            }
        } else {
            System.out.println("Menu option was invalid, please try again");
            loadMessageMenu(user);
        }}

    /**
     * Display all messages sent or received by this user
     */
    public static void displayMessages(User user) {
        System.out.println(MessagePresenter.displayReceivedMessagesOfUser(user)); //retrieve all messages received by the current user
    }

    /**
     * Get message content from the user
     */
    public static String getMessageContent() {
        Scanner s = new Scanner(System.in);
        System.out.println("Please enter the message content:");
        return s.nextLine();
        }

}



