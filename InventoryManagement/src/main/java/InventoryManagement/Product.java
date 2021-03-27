package main.java.InventoryManagement;

/**
 * This class represent a line in Inventory.dat file
 */
public class Product 
{
    //TODO Add the following fields and a constructor to set the fields:
    //                          Id (int, this is the unique product id)
    //                          Name (string)
    //                          Cost(double)
    //                          Quantity(int)
    //                          Margin(int as a percentage) 

    private int id; // id unique to every product
    private String name;
    private double cost;
    private int quantity;
    private int margin; // going to be displayed as a percentage

    public Product(int id, String name, double cost, int quantity, int margin)
    {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.quantity = quantity;
        this.margin = margin;
    }

    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public double getCost()
    {
        return cost;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public int getMargin()
    {
        return margin;
    }
}