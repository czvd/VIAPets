package model;

import java.util.Objects;

public class Rodent extends Pet
{
  private String species;
  private boolean bite;

  public Rodent(double price, String color, int age, String species)
  {
    super(4, price, color, age);
    setSpecies(species);
    bite = false;
  }

  @Override public boolean hasAccessToKennel()
  {
    return false;
  }

  public String getSpecies()
  {
    return species;
  }

  public boolean doesItBite()
  {
    return bite;
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
    return super.equals(obj) && getSpecies().equals(temp.getSpecies()) && doesItBite() == temp.doesItBite();
  }

  public String toString()
  {
    return super.toString() + "\n" + getSpecies() + doesItBite();
  }
}

