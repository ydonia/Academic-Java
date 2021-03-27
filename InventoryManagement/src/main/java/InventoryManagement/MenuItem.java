package main.java.InventoryManagement;

/**
 * MenuItem
 */
public class MenuItem
{
    private String description;
    private Command command;
    private boolean isRestricted;
    private int optionNumber;

    public MenuItem(Command command, int optionNumber, String description, boolean isRestricted)
    {
        //TODO Finish the implementation of this class
        this.description = description;
        this.command = command;
        this.isRestricted = isRestricted;
        this.optionNumber = optionNumber;
        System.out.println("Menu item created with command " + command.getClass().getSimpleName());
    }

    public void setOptionNumber(int optionNumber)
    {
        this.optionNumber = optionNumber;
    }

    public int getOptionNumber()
    {
        return optionNumber;
    }

    public String getDescription()
    {
        return description;
    }

    public void setRestricted(boolean isRestricted)
    {
        this.isRestricted = isRestricted;
    }
    public boolean getRestricted()
    {
        return isRestricted;
    }

    public void Execute()
    {
        this.command.Execute();
    }

}