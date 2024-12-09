package model;

import java.io.Serializable;

public class Rodent extends Pet implements Serializable
{
  private boolean bite =false;

  public Rodent(double price, String color, int age, String species)
  {
    super(4, price, color, age, species);
    bite = false;
  }
  public Rodent(Rodent rodent)
  {
    super(rodent.getType(), rodent.getPrice(), rodent.getColor(), rodent.getAge(), rodent.getSpecies());

    if(rodent.getGender().equals("Male")){
      isMale();
    }
    else if (rodent.getGender().equals("Female"))
    {
      isFemale();
    }
    setComment(rodent.getComment());
    setName(rodent.getName());
    if(rodent.getInKennel()){
      isInKennel();
    }else {
      isNotInKennel();
    }
    if (rodent.doesItBite())
    {
      isBiting();
    }
    else {
      isNotBiting();
    }

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
