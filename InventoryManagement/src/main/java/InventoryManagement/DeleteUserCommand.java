package main.java.InventoryManagement;

import java.util.Scanner;

public class DeleteUserCommand extends Command{

    public DeleteUserCommand(ProductCatalog productCatalog, User loggedOnUser)
    {
        super(productCatalog, loggedOnUser);
    }

    @Override
    public void Execute()
    {
        Scanner scanner = new Scanner(System.in);
        String username;

        // prompt manager for info
        System.out.print("Enter username: ");
        username = scanner.nextLine();

        // pass the username to the RemoveUser method in InventoryManagementSecurity class
        InventoryManagementSecurity.RemoveUser(username);
    }
}
