package model;

import java.io.Serializable;

/**
 * A class representing a sale with a pet, a customer, a date of sale and a final price.
 * @author Carolina Valencia Zavidei
 * @version 1.0
 */
public class Sale implements Serializable
{
  private Pet pet;
  private Customer customer;
  private Date dateOfSale;
  private double finalPrice = 20;

  /**
   * Two-argument constructor with a given pet and customer as parameters.
   * Initializes the sale date to today's date.
   * @param pet the sale's pet.
   * @param customer the sale's customer.
   */
  public Sale(Pet pet, Customer customer)
  {
    this.pet = pet;
    this.customer = customer;
    dateOfSale = Date.today();
  }

  /**
   * Three-argument constructor with a given pet, customer and final price as parameters.
   * Initializes the sale date to today's date.
   * @param pet the sale's pet.
   * @param customer the sale's customer.
   * @param finalPrice the sale's final price.
   */
  public Sale(Pet pet, Customer customer, double finalPrice)
  {
    this.pet = pet;
    this.customer = customer;
    dateOfSale = Date.today();
    this.finalPrice = finalPrice;
  }

  /**
   * Four-arguments constructor with a given pet, customer, date and final price as parameters.
   * @param pet the sale's pet.
   * @param customer the sales's customer.
   * @param date the sale's date.
   * @param finalPrice the sale's final price.
   */
  public Sale(Pet pet, Customer customer, Date date, double finalPrice)
  {
    this.pet = pet;
    this.customer = customer;
    dateOfSale = date;
    this.finalPrice = finalPrice;
  }

  public Pet getPet()
  {
    return pet;
  }

  public Customer getCustomer()
  {
    return customer;
  }

  public Date getDateOfSale()
  {
    return dateOfSale;
  }
  public double getFinalPrice()
  {
    return finalPrice;
  }

  public void setFinalPrice(double finalPrice)
  {
    this.finalPrice = finalPrice;
  }

  public String toString()
  {
    return "Pet: " + pet + ", Customer:  " + customer + ", Date of sale: "
        + dateOfSale + "Final price: " + finalPrice;
  }

  public boolean equals(Object obj)
  {
    if (obj == null || getClass() != obj.getClass())
    {
      return false;
    }

    Sale other = (Sale) obj;

    return pet.equals(other.pet) && customer.equals(other.customer)
        && dateOfSale.equals(other.dateOfSale)
        && finalPrice == other.finalPrice;
  }

  public Object getDate()
  {
    return dateOfSale.copy();
  }
}
