package model;

public class Cat extends Pet
{
  private String breed;
  private String nameOfBreeder;

  public Cat(double price, String color, int age, String breed)
  {
    super(2, price, color, age);
    setBreed(breed);
    nameOfBreeder = "NoName";
  }

  public String getBreed()
  {
    return breed;
  }

  public String getNameOfBreeder()
  {
    return nameOfBreeder;
  }

  public void setBreed(String breed)
  {
    if (breed.matches("[a-zA-z ]+"))
    {
      this.breed = breed;
    }
    else
      throw new IllegalBreedSpecException();
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
    if (super.equals(obj))
    {
      Cat temp = (Cat) obj;
      return breed.equals(temp.getBreed()) && nameOfBreeder.equals(
          temp.getNameOfBreeder());
    }
    return false;
  }

  public String toString()
  {
    return super.toString() + "\n" + breed + "\n" + nameOfBreeder;
  }
}

