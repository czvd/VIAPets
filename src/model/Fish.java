package model;

public class Fish extends Pet
{
  private String species;
  private boolean predator;
  private boolean saltWater;

  public Fish(double price, String color, int age, String species,
      boolean predator, boolean saltWater)
  {
    super(5, price, color, age);
    setSpecies(species);
    isNotPredator();
    isNotSaltWater();
  }

  @Override
  public boolean hasAccessToKennel()
  {
    return false;
  }

  public String getSpecies()
  {
    return species;
  }

  public boolean isItPredator()
  {
    return predator;
  }

  public boolean isItSaltWater()
  {
    return saltWater;
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
    return super.equals(temp) && getSpecies().equals(temp.getSpecies())
        && predator == temp.isItPredator() && saltWater == temp.isItSaltWater();
  }

  public String toString()
  {
    return super.toString() + "\n" + species + "\n" + predator + "\n" + saltWater;
  }
}

