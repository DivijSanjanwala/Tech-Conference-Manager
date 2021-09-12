package entities;

public class VIP extends User{
    /**
     * Create a new User and generate its base available menu options
     *
     * @param username  the username of this user
     * @param password  the password of this user
     * @param ID        the unique ID of this user
     * @param firstName the first name of the user.
     */
    public VIP(String username, String password, int ID, String firstName) {
        super(username, password, ID, firstName);

        this.messageOptions.add("Message an attendee");
        this.messageOptions.add("Message a speaker");
        this.messageOptions.add("Join a VIP event");

    }

    @Override
    public String toString() {
        String delimiter = Character.toString((char) 31);
        return super.toString() + delimiter + "VIP";
    }

}
