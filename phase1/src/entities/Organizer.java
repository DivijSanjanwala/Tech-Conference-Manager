package entities;

import java.util.ArrayList;

/** An organizer user with management level capabilities */
public class Organizer extends User{

    /**
     * Create new Organizer, initialize a list of this Organizer's events
     * based on ID, and add available menu options
     *
     * @param username the user name of this organizer
     * @param password the password of this organizer
     * @param ID the unique ID of this organizer
     */
    public Organizer(String username, String password, int ID, String name) {
        super(username, password, ID, name);
        this.menuOptions.add("events");
        this.menuOptions.add("organize event");
        this.menuOptions.add("create user");

        this.messageOptions.add("Message all speakers");
        this.messageOptions.add("Message a specific speaker");
        this.messageOptions.add("Message all attendees");
        this.messageOptions.add("Message a specific attendee");

    }

    @Override
    public String toString() {
        String delimiter = Character.toString((char) 31);
        return super.toString() + delimiter + "organizer";
    }
}
