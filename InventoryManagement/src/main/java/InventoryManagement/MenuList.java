package main.java.InventoryManagement;

import java.util.ArrayList;

/**
 * MenuList
 */
public class MenuList {

    // create ArrayList for the menuList
    private ArrayList<MenuItem> menuItemList = new ArrayList<>();

    public MenuList(String menuHeader)
    {
        menuHeader = "Inventory Management System Menu";
    }

    public boolean AddMenuItem(MenuItem menuItem)
    {
        //TODO Add menu item to the list.

        menuItemList.add(menuItem);
        return true;
    }

    public void StartMenu(User user)
    {
        //TODO Display menu items based on user type, prompt user for command, execute selected command.

        // use for loop to iterate over the menu list
        for (MenuItem menuItem: menuItemList) {
            if (user.getManager())
            {
                System.out.println(String.format("%d - %s", menuItem.getOptionNumber(), menuItem.getDescription()));
            } else if (!user.getManager()) // if user is not a manager, only show the menu options that are not restricted to them
            {
                if (!menuItem.getRestricted())
                {
                    System.out.println(String.format("%d - %s", menuItem.getOptionNumber(), menuItem.getDescription()));
                }
            }
        }
    }

    public MenuItem getMenuItemById(int option)
    {
        return menuItemList.get(option - 1);
    }
}