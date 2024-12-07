package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A class containing a list of Customer objects
 * @author Patrik Golian
 * @version 1.0
 */

public class CustomerList implements Serializable
{
  private ArrayList<Customer> customers;

  /**
   * No-argument constructor initializing the CustomerList.
   */
  public CustomerList()
  {
    customers = new ArrayList<Customer>();
  }

  /**
   * Adds a Customer to the list after validating that the email address and phone number are unique.
   * If either is already in the system, an exception is thrown
   * @param customer the Customer object to add to the list
   * @throws IllegalEmailException if the email address already exists in the system
   * @throws IllegalPhoneNumberException if the phone number already exists in the system
   */
  public void addCustomer(Customer customer)
  {
    for(int i=0; i<customers.size(); i++)
    {
      Customer customer1 = customers.get(i);
      if(customer1.getEmailAddress().equals(customer.getEmailAddress()))
      {
        throw new IllegalEmailException("The email address matches other email address in the system, enter other email address.");
      }
      else if(customer1.getPhoneNumber().equals(customer.getPhoneNumber()))
      {
        throw new IllegalPhoneNumberException("The phone number matches other phone number in the system, enter other phone number.");
      }
    }
    customers.add(customer);
  }
  /**
   * Removes a specified Customer from the list, if there is one.
   * @param customer the Customer object to remove from the list
   */
  public void removeCustomer(Customer customer)
  {
    for(int i =0; i<customers.size(); i++)
    {
      Customer temp = customers.get(i);
      if(temp.equals(customer))
      {
        customers.remove(customer);
      }
    }

  }


  /**
   * Returns the number of Customer objects in the list.
   * @return the size of the customer list
   */
  public int size()
  {
    return customers.size();
  }

  /**
   * Searches for customers in the list by their first or last name.
   * @param name the name that is searched for(can match either first name or last name)
   * @return A CustomerList containing all customers with a matching first or last name
   */
//  public CustomerList getCustomerByName(String name)
//  {
//    // Declares a temporary variable to hold a Customer during iteration.
//    Customer temp;
//
//    // Creates a new, empty CustomerList to store matching customers.
//    CustomerList customers = new CustomerList();
//
//    // Loops through the customers list.
//    for(int i = 0; i < customers.size(); i++)
//    {
//      // Retrieves the customer at the current index of the list.
//      temp = customers.get(i);
//
//      // Checks if the first or last name of the customer matches the given name.
//      if(temp.getFirstName().equals(name) || temp.getLastName().equals(name))
//      {
//        // Adds the matching customer to the customers list.
//        customers.addCustomer(temp);
//      }
//    }
//    return customers;
//  }

  /**
   *Finds the index of a Customer in the list based on their first and last names.
   * @param firstName the first name of the customer to find
   * @param lastName the last name of the customer to find
   * @return the index of the customer if found, or -1 if not found
   */
  public int getIndex(String firstName, String lastName)
  {
    for(int i = 0; i<customers.size(); i++)
    {
      Customer temp = customers.get(i);

      if(temp.getFirstName().equals(firstName) && temp.getLastName().equals(lastName))
      {
        return i;
      }
    }
    return -1;
  }

  /**
   * Gets a Customer object from position index from the list.
   * @param index  the position in the list of the Customer object
   * @return the Customer object at position index if one exists, else null
   */
  public Customer get(int index)
  {
    if(index<customers.size())
    {
      return customers.get(index);
    }
    else
    {
      return null;
    }
  }

  /**
   * Returns a string representation of the CustomerList.
   * @return the list of Customer objects as String
   */
  public String toString()
  {
    String returnStr = "";

    for(int i = 0; i<customers.size(); i++)
    {
      Customer temp = customers.get(i);

      returnStr += temp +"\n";
    }
    return returnStr;
  }
}
