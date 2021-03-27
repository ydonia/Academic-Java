package main.java.InventoryManagement;

import main.java.InventoryManagement.*;
//import main.java.LinkedInventoryManagement.Security.SecurityOperations;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Main
 *
 */
public class Main 
{
    public static void main( String[] args )
    {
        // intialize all local variables
        String username= "";
        String password = "";
        String line;
        Scanner readFile;
        StringTokenizer tokenizer;
        String name;
        // read in information from the Users.Dat file
        User user = null;

        // while loop for username and password validation
        boolean isValidUser = false;
        Scanner userInput = new Scanner(System.in);
        while (!isValidUser)
        {
            // prompt user to add user info until the info is valid (using AuthenticateUser method)
            System.out.print("Enter username: ");
            username = userInput.nextLine();
            System.out.print("Enter password: ");
            password = userInput.nextLine();

            user = InventoryManagementSecurity.AuthenticateUser(username, password);

            // if valid user, exit the loop
            if (user != null)
            {
                isValidUser = true; // to leave the while loop
            }
        }

        // TODO: find a way to be able to print the user's name in the function. might need to change the AuthenticateUser method.

        if(user.getLastName() != null)
        {
            System.out.println("Welcome " + user.getFirstName() + " " + user.getLastName() + "!");
        } else
        {
            System.out.println("Welcome " + user.getFirstName() + "!");
        }
        // load the product catalog from the inventory.dat file
        File inventoryFile = new File("Inventory.txt");
        Scanner readInventory;
        int productId;
        int quantity, margin;
        String productName;
        double productCost;
        String inventoryLine = "";
        String inventoryLineNoCommas = "";
        tokenizer = null;
        ProductCatalog productCatalog = new ProductCatalog();
        try
        {
            readInventory = new Scanner(inventoryFile);
            while (readInventory.hasNextLine())
            {
                 inventoryLine = readInventory.nextLine();
                 inventoryLineNoCommas = inventoryLine. replaceAll(",", ""); // remove commas
                 tokenizer = new StringTokenizer(inventoryLineNoCommas);

                // fill the variables for a product from each line of the file
                //productId = Integer.parseInt(readInventory.next());
                //productName = readInventory.next();
                //productCost = Double.parseDouble(readInventory.next());
                //quantity = Integer.parseInt(readInventory.next());
                //margin = Integer.parseInt(readInventory.next());
                productId = Integer.parseInt(tokenizer.nextToken());
                productName = tokenizer.nextToken();
                while (tokenizer.countTokens() > 3)
                {
                    productName += " " + tokenizer.nextToken() + " ";
                }
                productCost = Double.parseDouble(tokenizer.nextToken());
                quantity = Integer.parseInt(tokenizer.nextToken());
                margin = Integer.parseInt(tokenizer.nextToken());

                // store into a product
                Product product = new Product(productId, productName, productCost, quantity, margin);

                // store that product into the ProductCatalog
                productCatalog.AddUpdateProduct(product);
            }
        } catch (FileNotFoundException ex)
        {
            // do what the instructions say to do if the file does not exist
            System.out.println("File does not exist");
        }

        // TODO: I MUST IMPLEMENT THE PART WHERE I RESTRICT THE USER IF THEY ARE NOT A MANAGER
        // read in information from MenuList.dat file and store in the variables below
        String commandClassName;
        int optionNumber = 1;
        String description;
        boolean isRestricted;
        String menuLine = "";
        tokenizer = null;
        MenuList menu = new MenuList("Inventory Management System Menu");
        // if the Menulist.dat file doesnt exist, still display the exit option, which will always be the last option in menu
        File menuListFile = new File("MenuList.txt");
        Scanner readMenu;
        String menuLineNoCommas  = "";
        try
        {
            readMenu = new Scanner(menuListFile);
            while (readMenu.hasNextLine())
            {
                menuLine = readMenu.nextLine();
                menuLineNoCommas = menuLine. replaceAll(",", ""); // remove commas
                tokenizer = new StringTokenizer(menuLineNoCommas);

                // read in the variables from the line
                description = tokenizer.nextToken();
                // if there are more tokens left in the line than isRestricted and commandCLassName
                while (tokenizer.countTokens() > 2)
                {
                    description += " " + tokenizer.nextToken();
                }
                isRestricted = Boolean.parseBoolean(tokenizer.nextToken());
                commandClassName = tokenizer.nextToken();

                // TODO: this part is probably wrong AND i need to find a way to access user
                // create dynamic command
                Command dynamicCommand = Command.CreateCommandDynamically(productCatalog, user, commandClassName);
                // create Menu Item based on the dynamic command
                MenuItem menuItem = new MenuItem(dynamicCommand, optionNumber, description, isRestricted);
                // add the Menu Item to a MenuList
                menu.AddMenuItem(menuItem);
                optionNumber++;
            }
        } catch(FileNotFoundException exception)
        {
            System.out.println("File does not exist");
        }


        // TODO: change the design of this part too
        // display the menu, prompt user for option
        menu.StartMenu(user);
        System.out.println("9 - Exit");
        Scanner userChoice = new Scanner(System.in);
        System.out.print("Enter your selection: ");
        int option = userChoice.nextInt();

        // TODO: delete teh code below because the number of options in menu depends on the file we read from
        // and use code such as below to analyze the user choice
        while(option != 9) {

            menu.getMenuItemById(option).Execute();
            menu.StartMenu(user);
            System.out.println("9 - Exit");
            System.out.print("Enter your selection: ");
            option = userChoice.nextInt();
        }
        if (option == 9)
        {
            System.out.println("Program Exited.");
            System.exit(0);
        }
        // exit option must always be last in the menu

    }
}

