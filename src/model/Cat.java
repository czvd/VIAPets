package model;

import java.io.Serializable;

public class Cat extends Pet implements Serializable
{
  private String nameOfBreeder;

  public Cat(double price, String color, int age, String species)
  {
    super(2, price, color, age, species);
    nameOfBreeder = "NoName";
  }

  public String getNameOfBreeder()
  {
    return nameOfBreeder;
  }



  public void setNameOfBreeder(String nameOfBreeder)
  {
    if (nameOfBreeder.matches("[a-zA-z ]+"))
    {
      this.nameOfBreeder = nameOfBreeder;
    }
    else
      throw new IllegalNameException();
  }

  @Override
  public boolean hasAccessToKennel()
  {
    return true;
  }

  @Override
  public boolean equals(Object obj)
  {
    if(obj == null || getClass() != obj.getClass())
    {
      return false;
    }

    Cat temp = (Cat)obj;
    return super.equals(temp) && nameOfBreeder.equals(temp.getNameOfBreeder());
  }

  public String toString()
  {
    return super.toString() + "\n" + nameOfBreeder;
  }
}
