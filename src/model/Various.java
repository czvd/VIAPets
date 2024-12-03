package model;

import java.io.Serializable;

public class Various extends Pet implements Serializable
{

  public Various(double price, String color, int age, String species)
  {
    super(6,price,color,age,species);
  }

  @Override
  public boolean hasAccessToKennel()
  {
    return false;
  }

  @Override
  public boolean equals(Object obj)
  {
    if (obj == null || getClass() != obj.getClass())
      return false;
    if (!super.equals(obj))
      return false;
    Various temp = (Various) obj;
    return super.equals(temp);
  }

  public String toString()
  {
    return super.toString();
  }
}
