package main.java.InventoryManagement;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * InventoryManagementSecurity
 */
public class InventoryManagementSecurity 
{
    public static User AuthenticateUser(String username, String password)
    {
        // TODO: THIS METHOD MIGHT HAVE A PROBLEM WHERE IT KEEPS TELLING ME USERFILE NOT FOUND
        File userFile = new File("User.txt");
        User authenticatedUser = null;
        //Scanner scan = new Scanner(System.in);

        StringTokenizer tokenizer;
        String line, firstName = null, lastName = null, lineNoCommas = "";
        boolean userFound = false;
        // if the username and password are correct, create new manager user
        if((username.compareToIgnoreCase("admin") == 0) && 
           (GetPasswordHash(password).compareToIgnoreCase("58c536ed8facc2c2a293a18a48e3e120") == 0))
        {
            // new manager because the isManager boolean is true
            authenticatedUser = new User(username, GetPasswordHash(password), true);
            authenticatedUser.setFirstName("admin");
            System.out.println("This user is a manager");
        } else
        {
            // read from the user.dat file
            User user = new User("", "", false); // make empty user

            try (Scanner readFile = new Scanner(userFile))
            {
                while(readFile.hasNextLine() && !userFound) // loop over the whole file
                {
                    line = readFile.nextLine();
                    lineNoCommas = line. replaceAll(",", ""); // remove commas

                    // separate line into tokens
                    tokenizer = new StringTokenizer(lineNoCommas);

                    // put the info from the line into user
                    firstName = tokenizer.nextToken(); // first name
                    // if the last name exists
                    if (tokenizer.countTokens() == 4) // four tokens left, then last name exists
                        lastName = tokenizer.nextToken(); // adds last name

                    // set the username, password and isManager
                    user.setUserName(tokenizer.nextToken());
                    user.setUserPassword(GetPasswordHash(tokenizer.nextToken()));

                    user.setManager(Boolean.parseBoolean(tokenizer.nextToken()));

                    // check if user exists based on the info gathered in the line
                    if (line.contains(username) && line.contains(GetPasswordHash(password)))
                    {
                        // user.getUsername().equals(username) && user.getHashedPassword().equals(GetPasswordHash(password))
                        // if user exists
                        authenticatedUser = new User(username, GetPasswordHash(password), user.getManager());
                        authenticatedUser.setFirstName(firstName);
                        authenticatedUser.setLastName(lastName);
                        userFound = true;
                    }
                }
                if (!userFound)
                {
                    System.out.println("User cannot be authenticated.");
                }
            } catch(Exception ex)
            {
                // do what the instructions say to do if the file does not exist
                System.out.println("File does not exist");
            }

        }

        return authenticatedUser; // Change the return value based on authentication result
    }

    public static boolean AddNewUser(User newUser)
    {
        //TODO hash password and save user info to Users.dat

        // hash the password
        newUser.setUserPassword(GetPasswordHash(newUser.getHashedPassword()));

        // write in the username and hashed password to the user.dat file
        File userFile = new File("User.txt");
        try (PrintWriter fileWriter = new PrintWriter(new FileWriter(userFile, true)))
        {
            if (newUser.getLastName() != null)
            {
                fileWriter.append("\n").append(newUser.getFirstName()).append(", ").append(newUser.getLastName()).append(", ").append(newUser.getUsername()).append(", ").append(newUser.getHashedPassword()).append(", ").append(String.valueOf(newUser.getManager()));
                System.out.println("\nUser created.");
                fileWriter.close();
            } else {
                fileWriter.append("\n").append(newUser.getFirstName()).append(", ").append(newUser.getUsername()).append(", ").append(newUser.getHashedPassword()).append(", ").append(String.valueOf(newUser.getManager()));
                System.out.println("\nUser created.");
                fileWriter.close();
            }
        } catch (IOException ex)
        {
            System.out.println("File does not exist");
        }
        return true; // if writing to the file was successful
    }

    public static boolean RemoveUser(String userName)
    {
        //TODO remove username from Users.dat

        // check if user exists in Users.dat
        File userFile = new File("User.txt");
        Scanner readFile;
        String line = null;
        String updatedFile = null;
        boolean isDeleted;
        // if user exists, remove from users.dat: IM NOT SURE HOW TO DO THIS
        try {
            readFile = new Scanner(userFile);
            while (readFile.hasNextLine()) // loop over the whole file
            {
                line = readFile.nextLine();
                // add all the lines to the new string except for the one we want to delete
                if (!line.contains(userName))
                {
                   updatedFile +=  line + "\n";
                }
            }
            // write the updatedFile string into the file

                isDeleted = userFile.delete();
                if (!isDeleted)
                {
                    System.out.println("File could not be deleted");
                } else {
                    userFile = new File("User.txt");
                    try (PrintWriter fileWriter = new PrintWriter(userFile))
                    {
                        fileWriter.print(updatedFile);
                    }
                }
            System.out.println("\nUser deleted.");

        } catch(FileNotFoundException ex)
        {
            // do what the instructions say to do if the file does not exist
            System.out.println("File does not exist");
        }

        // if not, method will return false

        return true;
    }

    public static boolean ChangePassword(String username, String currentPassword, String newPassword)
    {
        //TODO change the password only if current password match what is on records
        File userFile = new File("User.txt");
        Scanner readFile;
        String line = null;
        String updatedFile = null;
        User user = new User("", "", false); // make empty user
        StringTokenizer  tokenizer = null;
        String lineNoCommas;
        boolean isDeleted;
        // check if the user exists in the file user.dat
        try {
            readFile = new Scanner(userFile);
            while (readFile.hasNextLine()) // loop over the whole file
            {
                line = readFile.nextLine();
                lineNoCommas = line. replaceAll(",", ""); // remove commas
                // add all the lines to the new string except for the one we want to delete
                if (!line.contains(username) && (!line.contains(currentPassword)))
                {
                    updatedFile +=  line + "\n";
                } else
                {
                    // change the line in order to change current password to new password
                    tokenizer = new StringTokenizer(lineNoCommas);
                    user.setFirstName(tokenizer.nextToken());
                    if (tokenizer.countTokens() == 4)
                    {
                        user.setLastName(tokenizer.nextToken());
                    }
                    user.setUserName(tokenizer.nextToken());
                    user.setUserPassword(newPassword);
                    user.setManager(Boolean.parseBoolean(tokenizer.nextToken()));
                    line = null;
                    if (user.getLastName() != null)
                    {
                        line = user.getFirstName() + ", " + user.getLastName() + ", " + user.getUsername() + ", "
                                + GetPasswordHash(user.getHashedPassword()) + ", " + user.getManager();
                        updatedFile += line + "\n";
                    } else
                    {
                        line = user.getFirstName() + ", " + user.getUsername() + ", "
                                + GetPasswordHash(user.getHashedPassword()) + ", " + user.getManager();
                        updatedFile += line + "\n";
                    }
                }
            }
            // write the updatedFile string into the file
            isDeleted = userFile.delete();
            if (!isDeleted)
            {
                System.out.println("File could not be deleted");
            } else {
                userFile = new File("User.txt");
                try (PrintWriter fileWriter = new PrintWriter(userFile))
                {
                    fileWriter.print(updatedFile);
                }
            }
            System.out.println("\nPassword changed.");

        } catch(FileNotFoundException ex)
        {
            // do what the instructions say to do if the file does not exist
            System.out.println("File does not exist");
        }
        return true;
    }

    public static String GetPasswordHash(String password) 
    {        
        String generatedPassword = null;
        
        try 
        {
            byte[] salt = new byte[] {12, -12, 65, 61, 
                                      2, -6, -90, 12, 
                                      4, -7, -87, 2, 
                                      34, -102, 3, 115};

            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            // Add password bytes to digest
            md.update(salt);
            // Get the hash's bytes
            byte[] bytes = md.digest(password.getBytes());
            // This bytes[] has bytes in decimal format;
            // Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            // Get complete hashed password in hex format
            generatedPassword = sb.toString();
        } 
        catch (NoSuchAlgorithmException e) 
        {
            e.printStackTrace();
        } 

        return generatedPassword;
    }
}