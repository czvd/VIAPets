package model;
import java.io.Serializable;
import java.util.ArrayList;

public class PetList implements Serializable
{
  private ArrayList<Pet> pets;

  public PetList()
  {
    pets = new ArrayList<>();
  }

  public void addPet(Pet pet)
  {
    pets.add(pet);
  }

  public void removePet(Pet pet)
  {
    pets.remove(pet);
  }

  public PetList getPetsByType(int type)
  {
    PetList temp = new PetList();
    for (int i = 0; i < pets.size(); i++)
    {
      if (pets.get(i).getType() == type)
      {
        temp.pets.add(pets.get(i));
      }
    }
    return temp;
  }

  public PetList getPetsByColor(String color)
  {
    PetList temp = new PetList();
    for (int i = 0; i < pets.size(); i++)
    {
      if (pets.get(i).getColor().equals(color))
      {
        temp.pets.add(pets.get(i));
      }
    }
    return temp;
  }

  public PetList getPetsBySpecies(String species)
  {
    PetList temp = new PetList();
    for (Pet pet : pets)
    {
      if (pet.getSpecies().equals(species))
      {
        temp.addPet(pet);
      }
    }
    return temp;
  }

  public String toString()
  {
    String temp = "";
    for (Pet pet : pets)
    {
      temp += pet.toString() + "\n\n";
    }
    return temp;
  }
}
