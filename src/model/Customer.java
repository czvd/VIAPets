package model;

import java.io.Serializable;

/**
 *
 * @author Patrik Golian
 * @version 1.0
 */
public class Customer implements Serializable
{

  private String firstName;
  private String lastName;
  private String phoneNumber;
  private String emailAddress;

  /**
   *
   * @param firstName
   * @param lastName
   * @param phoneNumber
   * @param emailAddress
   */
  public Customer(String firstName, String lastName, String phoneNumber, String emailAddress) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.phoneNumber = phoneNumber;
    this.emailAddress = emailAddress;
  }

  public String getFirstName()
  {
    return firstName;
  }

  public String getLastName()
  {
    return lastName;
  }

  public String getName()
  {
    return firstName + " " + lastName;
  }

  public boolean isEqual(String name)
  {
    if(name.equals(getName()))
    {
      return true;
    }
    return false;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }
  public String getEmailAddress() {
    return emailAddress;
  }


  public void setFirstName(String firstName) {
    if (firstName == null || firstName.length() < 3) {
      throw new InvalidNameException("First name must be at least 3 characters long.");
    }
    this.firstName = firstName;
  }
  public void setLastName(String lastName)
  {
    this.lastName = lastName;
  }

  /**
   *
   * @param phoneNumber
   */
  public void setPhoneNumber(String phoneNumber) {
    if (!phoneNumber.matches("\\d+")) {
      throw new IllegalArgumentException("Phone number can only contain numbers.");
    }
    this.phoneNumber = phoneNumber;
  }






  public void setEmailAddress(String emailAddress) {
    if (!emailAddress.matches("^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
      throw new IllegalArgumentException("Invalid email format. Expected format: user@host.domain.");
    }

    this.emailAddress = emailAddress;
  }

  public boolean equals(Object obj)
  {
    if(obj==null || getClass()!= obj.getClass())
    {
      return false;
    }

    Customer other = (Customer)obj;
    return firstName.equals(other.firstName) && lastName.equals(other.lastName) && phoneNumber.equals(other.phoneNumber) && emailAddress.equals(other.emailAddress);
  }

  public String toString()
  {
    return firstName + " " + lastName + " " + phoneNumber + " " + emailAddress;
  }


}


