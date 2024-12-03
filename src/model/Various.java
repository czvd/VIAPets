package model;

import java.util.Objects;

public class Various extends Pet
{
  private String species;

  public Various(double price, String color, int age, String species)
  {
    super(6,price,color,age);
    setSpecies(species);
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

  public void setSpecies(String species)
  {
    if (species.matches("[a-zA-Z ]+"))
    {
      this.species = species;
    }
    else
      throw new IllegalBreedSpecException();
  }

  @Override public boolean equals(Object obj)
  {
    if (obj == null || getClass() != obj.getClass())
      return false;
    if (!super.equals(obj))
      return false;
    Various temp = (Various) obj;
    return super.equals(temp) && getSpecies().equals(temp.getSpecies());
  }
}

