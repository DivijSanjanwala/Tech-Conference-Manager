package gateways;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import useCases.UserManager;

public class UserIO {
    //handles reading and writing from user file

    private static final String delimiter = Character.toString((char) 31);

    /**
     * reads in user file and passes user data to UserManager
     */
    public static void readFile(){
        try{
            String dir = "./phase1/src/data/users.txt";
            File file = new File(dir);
            Scanner fs = new Scanner(file);

            while(fs.hasNextLine()){
                //file format ID-Username-Password-Name-Type-eventIDs
                String[] user = fs.nextLine().split(delimiter);
                Integer ID = Integer.parseInt(user[0]);
                String username = user[1];
                String password = user[2];
                String name = user[3];
                String type = user[4];
                UserManager.makeUser(ID, username, password, name, type);
            }
        }
        catch(Exception e){
            System.out.println("An error has occurred.");
            e.printStackTrace();
        }
    }

    /**
     * writes all user data from UserManager to file
     */
    public static void writeFile(){
        try{
            String dir = "./phase1/src/data/users.txt";
            File file = new File(dir);
            boolean existing = file.createNewFile(); //attempt to create new file if not preexisting

            FileWriter writer = new FileWriter(dir);
            ArrayList<String> info = UserManager.getAllUsersInfo();
            info.forEach((String value) -> {
                try {
                    writer.write(value + "\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            writer.close();
        }
        catch(Exception e){
            System.out.println("An error has occurred.");
            e.printStackTrace();
        }
    }

}
