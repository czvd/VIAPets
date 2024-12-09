package model;

import java.io.Serializable;

public class Bird extends Pet implements Serializable
{
  private String preferredFood = "NoData";

  /**
   * Four-argument constructor initializing the preferredFood to NoData and type of bird to 3
   * @param price the bird's price
   * @param color the birds' color
   * @param age the bird's age
   * @param species the bird's species
   */
  public Bird(double price, String color, int age, String species)
  {
    super(3, price, color, age, species);
    preferredFood = "NoData";
  }
  //constuctor for easy checking used at Kennel reservation and sales reservation
  public Bird(Bird bird)
  {
    super(bird.getType(), bird.getPrice(), bird.getColor(), bird.getAge(), bird.getSpecies());

    if(bird.getGender().equals("Male")){
      isMale();
    }
    else if (bird.getGender().equals("Female"))
    {
      isFemale();
    }
    setComment(bird.getComment());
    setName(bird.getName());
    if(bird.getInKennel()){
      isInKennel();
    }else {
      isNotInKennel();
    }
    setPreferredFood(bird.getPreferredFood());

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
