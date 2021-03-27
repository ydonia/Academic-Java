package main.java.InventoryManagement;

public class DisplayInventoryCommand extends Command {

    public DisplayInventoryCommand(ProductCatalog productCatalog, User loggedOnUser)
    {
        super(productCatalog, loggedOnUser);
    }

    @Override
    public void Execute()
    {
        // create instance of ProductCatalog
        ProductCatalog productCatalog = new ProductCatalog();

        // call the PrintInventoryList method function in ProductCatalog
        //String string = productCatalog.PrintInventoryList();
        //System.out.println(string);
        getProductCatalog().PrintInventoryList();
    }
}
