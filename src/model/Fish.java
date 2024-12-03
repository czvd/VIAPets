package model;

import java.io.Serializable;

public class Fish extends Pet implements Serializable
{
  private boolean predator;
  private boolean saltWater;

  public Fish(double price, String color, int age, String species)
  {
    super(5, price, color, age,species);
    isNotPredator();
    isNotSaltWater();
  }

  @Override public boolean hasAccessToKennel()
  {
    return false;
  }

  public boolean isItPredator()
  {
    return predator;
  }

  public boolean isItSaltWater()
  {
    return saltWater;
  }

  public void isPredator()
  {
    predator = true;
  }

  public void isNotPredator()
  {
    predator = false;
  }

  public void isSaltWater()
  {
    saltWater = true;
  }

  public void isNotSaltWater()
  {
    saltWater = false;
  }

  public boolean equals(Object obj)
  {
    if (obj == null || getClass() != obj.getClass())
    {
      return false;
    }

    Fish temp = (Fish) obj;
    return super.equals(temp) && predator == temp.isItPredator()
        && saltWater == temp.isItSaltWater();
  }

  public String toString()
  {
    return super.toString() + "\n" + predator + "\n"
        + saltWater;
  }
}
