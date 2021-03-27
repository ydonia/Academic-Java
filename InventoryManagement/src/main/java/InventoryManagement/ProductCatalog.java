package main.java.InventoryManagement;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * ProductCatalog
 */
public class ProductCatalog {

    private final ArrayList<Product> productArrayList = new ArrayList<>();

    public ProductCatalog()
    {
        // create array list to store products in
        ArrayList<Product> productArrayList = new ArrayList<>();
        //TODO implement the ProductCatalog methods and fields. Add a collection to hold Product objects

    }

    //Add or update a product if already exists
    public boolean AddUpdateProduct(Product product)
    {
        boolean isSuccess = false;
        // if the product already exists, update it
        for (int i = 0; i < productArrayList.size(); i++)
        {
            if (product.getId() == productArrayList.get(i).getId())
            {
                // update the value of the product
                productArrayList.set(i, product);
                isSuccess = true;
                System.out.println("Product updated");
            }
        }

        // if product does not exist, add it to the list
        if (!isSuccess)
        {
            productArrayList.add(product);
            isSuccess = true;
            System.out.println("Product added to inventory");
        }

        // print arrayList to the file
        File inventoryFile = new File("Inventory.txt");
        try (PrintWriter fileWriter = new PrintWriter(inventoryFile))
        {
            for (Product product1: productArrayList) {
                fileWriter.print(product1.getId() + ", " + product1.getName() + ", " +
                        product1.getCost() + ", " + product1.getQuantity() + ", " + product1.getMargin() + "\n");
            }
        } catch (FileNotFoundException ex)
        {
            System.out.println("File not found");
        }
        // if method was not able to add the product, isSuccess stays false
        return isSuccess;
    }

    //TODO create an overload of the AddUpdateProduct method
    // public boolean AddUpdateProduct(....) 
    // {

    // }

    public boolean RemoveProduct(int productId){
        boolean isSuccess = false;
        // check if product exists, if yes, remove it from the list
        for (int i = 0; i < productArrayList.size(); i++)
        {
            if (productId == productArrayList.get(i).getId())
            {
                // remove product from list
                productArrayList.remove(productArrayList.get(i));
                isSuccess = true;
                System.out.println("Product removed");
            }
        }
        // print arrayList to the file
        File inventoryFile = new File("Inventory.txt");
        try (PrintWriter fileWriter = new PrintWriter(inventoryFile))
        {
            for (Product product1: productArrayList) {
                fileWriter.print(product1.getId() + ", " + product1.getName() + ", " +
                        product1.getCost() + ", " + product1.getQuantity() + ", " + product1.getMargin() + "\n");
            }
        } catch (FileNotFoundException ex)
        {
            System.out.println("File not found");
        }
        // if product was not found, isSuccess will stay false
        return isSuccess; 
    }

    //TODO create an overload of the RemoveProduct method to remove by product name
    public boolean RemoveProduct(String name)
    {
        boolean isSuccess = false;

        // check if product exists, if yes, remove it from the list
        for (int i = 0; i < productArrayList.size(); i++)
        {
            if (name.equals(productArrayList.get(i).getName()))
            {
                // remove product from list
                productArrayList.remove(productArrayList.get(i));
                isSuccess = true;
                System.out.println("Product removed");
            }
        }
        // print arrayList to the file
        File inventoryFile = new File("Inventory.txt");
        try (PrintWriter fileWriter = new PrintWriter(inventoryFile))
        {
            for (Product product1: productArrayList) {
                fileWriter.print(product1.getId() + ", " + product1.getName() + ", " +
                        product1.getCost() + ", " + product1.getQuantity() + ", " + product1.getMargin()+ "\n");
            }
        } catch (FileNotFoundException ex)
        {
            System.out.println("File not found");
        }
        // if product was not found, isSuccess will stay false
        return isSuccess;
    }

    public boolean FindProduct(int productId)
    {
        boolean isSuccess = false;
        // check if product exists using id
        for (int i = 0; i < productArrayList.size(); i++)
        {
            if (productId == (productArrayList.get(i).getId())) {
                // success
                isSuccess = true;
                break;
            }
        }
        return isSuccess; 
    }

    //TODO create an overload of the FindProduct method to find product by product name
    public boolean FindProduct(String productName)
    {
        boolean isSuccess = false;
        for (int i = 0; i < productArrayList.size(); i++)
        {
            if (productName.equals(productArrayList.get(i).getName())) {
                // success
                isSuccess = true;
                break;
            }
        }

        return isSuccess;
    }

    //Print information about a product including retail price (cost + (margin*cost/100))
    public String PrintProductInformation(int productId)
    {
        String productInformation = "";
        Double retail;
        boolean productFound = false;
        // check if product exists using id
        for (int i = 0; i < productArrayList.size(); i++)
        {
            if (productId == (productArrayList.get(i).getId()))
            {
                // set the retail price
                retail = (productArrayList.get(i).getCost() +
                        (productArrayList.get(i).getMargin()*productArrayList.get(i).getCost()/100));
                // add product details to productInformation string
                productInformation += "Id Name Cost Quantity Retail\n";
                productInformation += "----------------------------------------------------\n";
                productInformation += productArrayList.get(i).getId() + " " + productArrayList.get(i).getName()
                        + " $" + productArrayList.get(i).getCost() + " " + productArrayList.get(i).getQuantity() + " $"
                        + retail;
                break;
            } else
            {
                System.out.println("Product not found.");
            }
        }
        System.out.println(productInformation);
        return productInformation;
    }

    //Print all product information in the inventory
    public String PrintInventoryList()
    {
        String inventoryInformation =  "";

        System.out.println("Inventory: ");
        //You may print the inventory details here
        for (Product product: productArrayList) // foreach loop to iterate through the ArrayList
        {
            // TODO: check if this code is right
            inventoryInformation +=  product.getId() + " " + product.getName() + " " +
                    product.getCost() + " " + product.getQuantity() + " " + product.getMargin() + "\n";
        }
        System.out.println(inventoryInformation);
        return inventoryInformation;
    }
}