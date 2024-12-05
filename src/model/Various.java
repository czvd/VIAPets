package model;

import java.io.Serializable;

public class Various extends Pet implements Serializable
{

  public Various(double price, String color, int age, String species)
  {
    super(6,price,color,age,species);
  }
  //constuctor for easy checking used at Kennel reservation and sales reservation
  public Various(Various various)
  {
    super(various.getType(), various.getPrice(), various.getColor(), various.getAge(), various.getSpecies());

    if(various.getGender().equals("Male")){
      isMale();
    }
    else if (various.getGender().equals("Female"))
    {
      isFemale();
    }
    setComment(various.getComment());
    setName(various.getName());
    if(various.getInKennel()){
      isInKennel();
    }else {
      isNotInKennel();
    }

  }

  @Override
  public boolean hasAccessToKennel()
  {
    return getInKennel();
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
