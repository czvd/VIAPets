package model;

import java.io.Serializable;

public class Sale implements Serializable
{
  private Pet pet;
  private Customer customer;
  private Date dateOfSale;
  private double finalPrice = 20;

  public Sale(Pet pet, Customer customer)
  {
    this.pet = pet;
    this.customer = customer;
    dateOfSale = Date.today();
  }
  public Sale(Pet pet, Customer customer, double finalPrice)
  {
    this.pet = pet;
    this.customer = customer;
    dateOfSale = Date.today();
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
}
