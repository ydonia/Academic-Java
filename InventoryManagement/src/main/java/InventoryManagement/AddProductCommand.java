package main.java.InventoryManagement;


import java.util.Scanner;

/**
 * AddCommand
 */
public class AddProductCommand extends Command
{
    // THIS IS THE COMMAND THAT THE AddMenuItem WILL USE

    //TODO: add necessary fields to this class

    public AddProductCommand(ProductCatalog productCatalog, User loggedOnUser)
    {
        super(productCatalog, loggedOnUser); // calls constructor of Command class
    }

    @Override
    public void Execute() {
        // TODO Add the code that will execute this command
        Scanner scanner = new Scanner(System.in);

        // prompt user for product ID
        int id = 0;

        // prompt user for product description
        String name = null;
        double cost = 0;
        int quantity = 0;
        int margin = 0;

        System.out.print("Enter product id: ");
        id = scanner.nextInt();
        System.out.print("Enter product name: ");
        name = scanner.nextLine();
        name = scanner.nextLine();
        System.out.print("Enter product cost: ");
        cost = scanner.nextDouble();
        System.out.print("Enter product quantity: ");
        quantity = scanner.nextInt();
        System.out.print("Enter product margin: ");
        margin = scanner.nextInt();

        // create new product
        Product product = new Product(id, name, cost, quantity, margin);

        // pass that product to the AddUpdateProduct method function
        getProductCatalog().AddUpdateProduct(product);
    }
}