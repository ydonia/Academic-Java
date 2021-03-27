package main.java.InventoryManagement;

/**
 * User
 */
public class User 
{
    // TODO: add necessary fields to your program

    private String firstName;
    private String lastName;
    private String username;
    private String hashedPassword;
    private boolean isManager;

    public User(String username, String hashedPassword, boolean isManager)
    {        
        this.username = username;
        this.hashedPassword = hashedPassword;
        this.isManager = isManager;
    }

    public boolean setFirstName(String firstName)
    {
        if (firstName == null)
        {
            return false;
        } else
        {
            this.firstName = firstName;
            return true;
        }
    }

    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public boolean setLastName(String lastName)
    {
        if (lastName == null)
        {
            return false;
        } else
        {
            this.lastName = lastName;
            return true;
        }
    }

    public boolean setUserName(String username) {
        if (username == null)
        {
            return false;
        } else {
            this.username = username;
            return true;
        }
    }

    public boolean setUserPassword(String hashedPassword) {
        if (hashedPassword == null)
        {
            return false;
        } else {
            this.hashedPassword = hashedPassword;
            return true;
        }
    }

    public void setManager(boolean isManager) {
        this.isManager = isManager;
    }

    public String getUsername()
    {
        return username;
    }

    public String getHashedPassword()
    {
        return hashedPassword;
    }

    public boolean getManager()
    {
        return isManager;
    }

}