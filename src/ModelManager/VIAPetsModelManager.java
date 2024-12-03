package ModelManager;

import model.*;
import utils.FileHandler;
import model.PetList;

import java.io.FileNotFoundException;

import java.io.IOException;

public class VIAPetsModelManager
{
  private String fileName;
  private VIAPets viaPets;

  public VIAPetsModelManager(String fileName)
  {
    viaPets = new VIAPets();
    this.fileName = fileName;
    try
    {
      viaPets = (VIAPets) FileHandler.readFromBinaryFile(fileName);
    }
    catch (FileNotFoundException e)
    {
      System.out.println("File not found");
    }
    catch (IOException e)
    {
      System.out.println("IO Error reading file");
    }
    catch (ClassNotFoundException e)
    {
      throw new RuntimeException(e);
    }
  }
  public void save()
  {
    try
    {
      FileHandler.writeToBinaryFile(fileName, viaPets);
    }
    catch (FileNotFoundException e)
    {
      System.out.println("File not found");
    }
    catch (IOException e)
    {
      System.out.println("IO Error writing to file");
    }
  }

  //Pet Model Manager
  public PetList getAllPets()
  {
    return viaPets.getPetList();
  }

  public void addPet(Pet newPet)
  {
    PetList allPets = getAllPets();
    allPets.addPet(newPet);
    save();
  }

  public void removePet(Pet deletePet)
  {
    PetList allPets = getAllPets();
    allPets.removePet(deletePet);
    save();
  }

  public void changePetName(String name, Pet pet)
  {
    PetList pets = getAllPets();
    save();
  }

  // Sales Model Manager
  public SaleList getAllSales()
  {
    return viaPets.getSaleList();
  }

  public void addSale(Sale newSale)
  {
    SaleList allSales = getAllSales();
    allSales.addSale(newSale);
    save();
  }

  public void removeSale(Sale deleteSale)
  {
    SaleList allSales = getAllSales();
    allSales.removeSale(deleteSale);
    save();
  }


  //CustomerModelManager

  public CustomerList getAllCustomers()
  {
    return viaPets.getCustomerList();
  }

  public void addCostumer(Customer newCustomer)
  {
    CustomerList allCostumer = getAllCustomers();
    allCostumer.addCustomer(newCustomer);
    save();
  }

  public void removeCustomer(Customer deleteCustomer)
  {
    CustomerList allCustomer = getAllCustomers();
    allCustomer.removeCustomer(deleteCustomer);
    save();
  }


  public void changeEmailAddress(String firstName, String lastName, String phoneNumber, String emailAddress)
  {
    CustomerList allCustomers = getAllCustomers();
    Customer change = new Customer(firstName, lastName, emailAddress, phoneNumber);

    for (int i = 0; i < allCustomers.size(); i++)
    {
      Customer customer = allCustomers.get(i);

      if (customer.equals(change))
      {
        customer.setEmailAddress(emailAddress);
      }
    }

    save();
  }
  public void changePhoneNumber(String firstName, String lastName, String emailAddress, String phoneNumber)
  {
    CustomerList allCustomers = getAllCustomers();
    Customer change = new Customer(firstName, lastName, emailAddress, phoneNumber);

    for (int i = 0; i < allCustomers.size(); i++)
    {
      Customer customer = allCustomers.get(i);

      if (customer.equals(change))
      {
        customer.setPhoneNumber(phoneNumber);
      }
    }

    save();
  }
  public void changeLastName(String firstName, String lastName, String phoneNumber, String emailAddress)
  {
    CustomerList allCustomers = getAllCustomers();
    Customer change = new Customer(firstName, lastName, emailAddress, phoneNumber);

    for (int i = 0; i < allCustomers.size(); i++)
    {
      Customer customer = allCustomers.get(i);

      if (customer.equals(change))
      {
        customer.setLastName(lastName);
      }
    }

    save();
  }
  public void changeFirstName(String firstName, String lastName, String phoneNumber, String emailAddress)
  {
    CustomerList allCustomers = getAllCustomers();
    Customer change = new Customer(firstName, lastName, emailAddress, phoneNumber);
    for (int i = 0; i < allCustomers.size(); i++)
    {
      Customer customer = allCustomers.get(i);

      if (customer.equals(change))
      {
        customer.setFirstName(firstName);
      }
    }

    save();
  }
  public CustomerList getCustomerByName(String name)
  {
    return getAllCustomers().getCustomerByName(name);
  }

  //Kennel Reservation Model manager
  public KennelReservationList getAllReservations()
  {
    return viaPets.getKennelReservationList();
  }
  public void addReservation(KennelReservation newReservation)
  {
    KennelReservationList allReservations = getAllReservations();
    allReservations.addReservation(newReservation);
    save();
  }
  public void removeReservation(KennelReservation deleteReservation)
  {
    KennelReservationList allReservation = getAllReservations();
    allReservation.removeReservation(deleteReservation);
    save();
  }



}
