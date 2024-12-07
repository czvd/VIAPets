package model;

import java.io.Serializable;

public class Dog extends Pet implements Serializable
{
  private String nameOfBreeder;

  public Dog(double price, String color, int age, String species)
  {
    super(1, price, color, age, species);
    nameOfBreeder = "NoName";
  }
  //constuctor for easy checking used at Kennel reservation and sales reservation
  public Dog(Dog dog)
  {
    super(dog.getType(), dog.getPrice(), dog.getColor(), dog.getAge(), dog.getSpecies());

    if(dog.getGender().equals("Male")){
      isMale();
    }
    else if (dog.getGender().equals("Female"))
    {
      isFemale();
    }
    setComment(dog.getComment());
    setName(dog.getName());
    if(dog.getInKennel()){
      isInKennel();
    }else {
      isNotInKennel();
    }
    setNameOfBreeder(dog.getNameOfBreeder());

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

    Dog temp = (Dog)obj;
    return super.equals(temp) && nameOfBreeder.equals(temp.getNameOfBreeder());
  }

  public String toString()
  {
    return super.toString() + "\n" + nameOfBreeder;
  }
}
