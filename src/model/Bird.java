package model;

public class Bird extends Pet
{
  private String species;
  private String preferredFood;

  public Bird(double price, String color, int age, String species)
  {
    super(3, price, color, age);
    setSpecies(species);
    preferredFood = "NoData";
  }

  @Override public boolean hasAccessToKennel()
  {
    return true;
  }

  public String getSpecies()
  {
    return species;
  }

  public String getPreferredFood()
  {
    return preferredFood;
  }

  public void setSpecies(String species)
  {
    if (species.matches("[a-zA-Z ]+"))
    {
      this.species = species;
    }
    else
      throw new IllegalBreedSpecException();
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
    return super.toString() + "\n" + species + "\n" + preferredFood;
  }
}

