package model;

import java.io.Serializable;

public class Sale implements Serializable
{
  private Customer customer;
  private Pet pet;
  private double finalPrice = 20;
  private Date dateOfSale;

  public Sale(Customer customer, Pet pet)
  {
    this.customer = customer;
    this.pet = pet;
    dateOfSale = Date.today();
  }
  public Sale(Customer customer, Pet pet, double finalPrice)
  {
    this.customer = customer;
    this.pet = pet;
    this.finalPrice = finalPrice;
    dateOfSale = Date.today();
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
}
