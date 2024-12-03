package model;

import java.io.Serializable;

public class Bird extends Pet implements Serializable
{
  private String preferredFood;

  public Bird(double price, String color, int age, String species)
  {
    super(3, price, color, age, species);
    preferredFood = "NoData";
  }

  @Override public boolean hasAccessToKennel()
  {
    return true;
  }

  public String getPreferredFood()
  {
    return preferredFood;
  }

  public void setPreferredFood(String preferredFood)
  {
    this.preferredFood = preferredFood;
  }

  public boolean equals(Object obj)
  {
    if (super.equals(obj))
    {
      Bird temp = (Bird) obj;
      return getSpecies().equals(temp.getSpecies())
          && getPreferredFood().equals(temp.getPreferredFood());
    }
    return false;
  }

  public String toString()
  {
    return super.toString() + "\n" + preferredFood;
  }
}
