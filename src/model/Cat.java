package model;

import java.io.Serializable;

public class Cat extends Pet implements Serializable
{
  private String nameOfBreeder = "NoName";

  /**
   * Four-argument constructor initializing the name of breeder to NoName.
   * @param price the cat's price
   * @param color the cat's color
   * @param age the cat's age
   * @param species the cat's breed
   */
  public Cat(double price, String color, int age, String species)
  {
    super(2, price, color, age, species);
    nameOfBreeder = "NoName";
  }
  //constuctor for easy checking used at Kennel reservation and sales reservation
  public Cat(Cat cat)
  {
    super(cat.getType(), cat.getPrice(), cat.getColor(), cat.getAge(), cat.getSpecies());

    if(cat.getGender().equals("Male")){
      isMale();
    }
    else if (cat.getGender().equals("Female"))
    {
      isFemale();
    }
    setComment(cat.getComment());
    setName(cat.getName());
    if(cat.getInKennel()){
      isInKennel();
    }else {
      isNotInKennel();
    }
    setNameOfBreeder(cat.getNameOfBreeder());

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
