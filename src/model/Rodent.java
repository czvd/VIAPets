package model;

import java.io.Serializable;

public class Rodent extends Pet implements Serializable
{
  private boolean bite;

  public Rodent(double price, String color, int age, String species)
  {
    super(4, price, color, age, species);
    bite = false;
  }

  @Override public boolean hasAccessToKennel()
  {
    return false;
  }

  public boolean doesItBite()
  {
    return bite;
  }

  public void isBiting()
  {
    bite = true;
  }

  public void isNotBiting()
  {
    bite = false;
  }

  @Override
  public boolean equals(Object obj)
  {
    if (obj == null || getClass() != obj.getClass())
      return false;
    if (!super.equals(obj))
      return false;
    Rodent temp = (Rodent)obj;
    return super.equals(obj) && doesItBite() == temp.doesItBite();
  }

  public String toString()
  {
    return super.toString() + "\n" + doesItBite();
  }
}
