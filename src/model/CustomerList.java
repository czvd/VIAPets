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
   * No-argument constructor initializing the CustomerList
   */
  public CustomerList()
  {
    customers = new ArrayList<Customer>();
  }
  /**
   * Adds a Customer to the list after checking if the email address and phone number are already in the system or not. If they are the exception will be thrown.
   * @param customer the customer to add to the list
   */
  public void addCustomer(Customer customer)
  {
    for(int i=0; i<customers.size(); i++)
    {
      Customer customer1 = customers.get(i);
      if(customer1.getEmailAddress().equals(customer.getEmailAddress()))
      {
        throw new IllegalArgumentException("The email address matches other email address in the system, enter other email address.");
      }
      else if(customer1.getPhoneNumber().equals(customer.getPhoneNumber()))
      {
        throw new IllegalPhoneNumberException("The phone number matches other phone number in the system, enter other phone number.");
      }
    }
    customers.add(customer);
  }
  /**
   * Removes a Customer from the list
   * @param customer the customer is removed from the list, if he was there
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
   *
   * @return the size of the customer list
   */
  public int size()
  {
    return customers.size();
  }


  public CustomerList getCustomerByName(String name)
  {
    Customer temp;
    CustomerList customers = new CustomerList();
    for(int i = 0; i<customers.size(); i++)
    {
       temp = customers.get(i);

      if(temp.getFirstName().equals(name) || temp.getLastName().equals(name))
      {
        customers.addCustomer(temp);
      }
    }
    return customers;
  }

  /**
   *Gets a Customer index by the first name and last name
   * @param firstName the first name of the customer from whom we want the index
   * @param lastName the last name of the customer from whom we want the index
   * @return the index of the customer with given first name and last name if one exists, else -1
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
   *Gets a Customer from position index from the list
   * @param index the position in the list of the Customer object
   * @return the Customer at index if one exists, else null
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
   *A toString method for the Customer list
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
