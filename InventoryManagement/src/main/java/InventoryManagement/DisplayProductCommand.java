package main.java.InventoryManagement;

import java.util.Scanner;

public class DisplayProductCommand extends Command {

    public DisplayProductCommand(ProductCatalog productCatalog, User loggedOnUser)
    {
        super(productCatalog, loggedOnUser);
    }

    @Override
    public void Execute()
    {
        // prompt user for product ID
        int productId;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter product ID: ");
        productId = scanner.nextInt();

        // create instance of ProductCatalog to be able to call the static method
        ProductCatalog productCatalog = new ProductCatalog();

        // call the PrintProductInformation method in ProductCatalog class
        // String string = productCatalog.PrintProductInformation(productId);
        //System.out.println(string);
        getProductCatalog().PrintProductInformation(productId);
    }
}
