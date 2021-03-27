package main.java.InventoryManagement;

import java.util.Scanner;

public class AddUserCommand extends Command {

    public AddUserCommand(ProductCatalog productCatalog, User loggedOnUser)
    {
        super(productCatalog, loggedOnUser);
    }

    @Override
    public void Execute()
    {
        Scanner scanner = new Scanner(System.in);
        String username;
        String password;
        boolean isManager;
        String firstName, lastName;
        // prompt manager for info
        System.out.print("Enter first name: ");
        firstName = scanner.nextLine();
        System.out.print("Enter last name: ");
        lastName = scanner.nextLine();
        System.out.print("Enter username: ");
        username = scanner.nextLine();
        System.out.print("Enter password: ");
        password = scanner.nextLine();
        System.out.print("Enter true for manager, false otherwise: ");
        isManager = scanner.nextBoolean();

        // create user and pass that user to the AddNewUser method function in InventoryManagementSecurity class
        User user = new User(username, password, isManager);
        user.setFirstName(firstName);
        user.setLastName(lastName);

        InventoryManagementSecurity.AddNewUser(user);
    }
}

