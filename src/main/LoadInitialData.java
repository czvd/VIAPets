package main;

import ModelManager.VIAPetsModelManager;
import model.*;

import utils.FileHandler;

import java.io.FileNotFoundException;
import java.io.IOException;

public class LoadInitialData
{
  public static void main(String[] args)
  {

    VIAPetsModelManager modelManager = new VIAPetsModelManager("VIAPets.bin");

   // System.out.println(modelManager.getAllPets());
   // modelManager.addPet(new Dog(123, "grey", 12,"greatdane"));
    /*
    modelManager.addCostumer(new Customer("Richard", "Vegh","9999", "ricky@gmail.com"));
    modelManager.addCostumer(new Customer("Richad", "Vegh","999", "ricky3@gmail.com"));
    modelManager.addCostumer(new Customer("Ricard", "Vegh","999912", "ricky2@gmail.com"));
    modelManager.addCostumer(new Customer("Rihard", "Vegh","9999222", "ricky1@gmail.com"));
*/
    modelManager.addSale(new Sale(new Cat(200,"grey",23,"mastiff"),modelManager.getAllCustomers().getCustomerByName("Richard").get(0)));
    modelManager.addSale(new Sale(new Customer("Patyo", "Golian","4556", "patyo@gmail.com"),new Cat(200,"blue",23,"mastiff"),2000));
    /*
    VIAPets viaPets = new VIAPets();
    try
    {
      FileHandler.writeToBinaryFile("VIAPets.bin", viaPets);
    }
    catch (FileNotFoundException e)
    {
      System.out.println("Error opening file ");
    }
    catch (IOException e)
    {
      System.out.println("IO Error writing to file ");
    }
    System.out.println("Done");

     */
  }
}
