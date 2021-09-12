package entities;

public class VIP extends User{

    private String type = "VIP";

    /**
     * Create a new VIP user that's able to join VIP events
     *
     * @param username  the username of this user
     * @param password  the password of this user
     * @param ID        the unique ID of this user
     * @param firstName the first name of the user.
     */

    public VIP(String username, String password, int ID, String firstName, String dietaryRestrictions, String accessibilityRequirements) {
        super(username, password, ID, firstName, dietaryRestrictions, accessibilityRequirements);

        this.messageOptions.add("Message an attendee");
        this.messageOptions.add("Message a speaker");
        this.eventOptions.add("Sign up for an event");
        this.eventOptions.add("Cancel sign up for an event");

    }

    @Override
    public String toString() {
        String delimiter = Character.toString((char) 31);
        return super.toString() + delimiter + "VIP";
    }

    @Override
    public String getType() {
        return type;
    }

}
