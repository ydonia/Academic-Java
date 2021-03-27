package test.java.InventoryManagement;

import main.java.InventoryManagement.*;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */

    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void AuthenticateUserTest(String username, String password)
    {
        // first, cover the case where the user is the admin user
        User expecteduser = new User("admin", "58c536ed8facc2c2a293a18a48e3e120", true);
        User actualUser = InventoryManagementSecurity.AuthenticateUser(username, password);
        if (!expecteduser.equals(actualUser))
        {
            assertTrue("The function did not return correct values", false);
        }

        // now cover the case where the user is not the admin user, use an example
        // 1. assume the user exists
        expecteduser = new User("username", "58c536ed8facc2c2a293a18a48e3e120", expecteduser.getManager());
        username = "username";
        password = "admin";
        actualUser = InventoryManagementSecurity.AuthenticateUser(username, password);
        if (!actualUser.equals(expecteduser))
        {
            assertTrue("The function did not return the correct value.", false);
        }

        // 2. assume that the user does not exist
        expecteduser = null;
        username = "falseUser";
        password = "falsePassword";
        actualUser = InventoryManagementSecurity.AuthenticateUser(username, password); // should be null if user does not exist
        if (actualUser != null)
        {
            assertTrue("The function did not return the correct value.", false);
        }

    }

    @Test
    public void AddNewUserTest(User user)
    {
        boolean expectedResult = true;
        boolean actualResult = InventoryManagementSecurity.AddNewUser(user);
        if (actualResult != expectedResult)
        {
            assertTrue("Function was unable to add user.", false);
        }
    }

    @Test
    public void RemoveUserTest(User user)
    {
        boolean expectedResult = true;
        boolean actualResult = InventoryManagementSecurity.AddNewUser(user);
        if (actualResult != expectedResult)
        {
            assertTrue("Function was unable to remove user.", false);
        }
    }

    @Test
    public void ChangePasswordTest(String username, String currentPassword, String newPassword)
    {
        boolean expectedResults = true;
        boolean actualResults = InventoryManagementSecurity.ChangePassword(username, currentPassword, newPassword);
        if (actualResults != expectedResults)
        {
            assertTrue("Function was unable to change the password.", false);
        }
    }

    @Test
    public void AddMenuItemTest(MenuItem menuItem)
    {
        boolean expectedResults = true;
        String string = "";
        MenuList obj = new MenuList(string);
        boolean actualResults = obj.AddMenuItem(menuItem);
        if (actualResults != expectedResults)
        {
            assertTrue("Function was unable to add menu item.", false);
        }
    }

    @Test
    public void AddUpdateProductTest(Product product)
    {
        boolean expectedResults = true;
        ProductCatalog obj = new ProductCatalog();
        boolean actualResults = obj.AddUpdateProduct(product);
        if (actualResults != expectedResults)
        {
            assertTrue("Function was unable to update or add product.", false);
        }

    }

    @Test
    public void RemoveProductTest(int productId)
    {
        boolean expectedResults = true;
        ProductCatalog obj = new ProductCatalog();
        boolean actualResults = obj.RemoveProduct(productId);
        if (actualResults != expectedResults)
        {
            assertTrue("Function was unable to remove product.", false);
        }
    }

    @Test
    public void FindProduct(int productId)
    {
        boolean expectedResults = true;
        ProductCatalog obj = new ProductCatalog();
        boolean actualResults = obj.FindProduct(productId);
        if (actualResults != expectedResults)
        {
            assertTrue("Function was unable to find product.", false);
        }
    }

    @Test
    public void PrintProductInformationTest(int productId)
    {
        productId = 1;
        ProductCatalog productCatalog = new ProductCatalog();
        Product product = new Product(productId, "name", 1.0, 1, 1);
        productCatalog.AddUpdateProduct(product);
        String expectedResults = "Id Name Cost Quantity Retail\n";
        expectedResults += "----------------------------------------------------\n";
        expectedResults += "1 name $1.0 1 1 $1.01";
        assertEquals(expectedResults, productCatalog.PrintProductInformation(productId));
    }

    @Test
    public void PrintInventoryListTest()
    {
        ProductCatalog inventoryList = new ProductCatalog();
        Product product1 = new Product(1, "name", 1.0, 1, 1);
        inventoryList.AddUpdateProduct(product1);
        String expectedResult = "Inventory:\n";
        expectedResult += "Product 1: 1 name $1.0 1 1 $1.01";

        assertEquals(expectedResult, inventoryList.PrintInventoryList());
    }
}
