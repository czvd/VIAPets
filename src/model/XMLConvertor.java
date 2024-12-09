package model;

public class XMLConvertor
{

  public static String petToXML(Pet pet)
  {
    String xmlFile =
        "<pet>\n"
            + " <name>" +pet.getName() + "<name>\n"
            + " <age>" + pet.getAge() + "<age>\n"
            +" <gender>" + pet.getGender() + "<gender\n"
            +" <price>" + pet.getPrice() + "<price\n"
            + "< color>" + pet.getColor() + "<color>\n"
            + "<type>" + pet.getType() + "<color>\n"
            + "<species>" + pet.getSpecies() + "<species>\n"
            + "<comment>" + pet.getComment() + "<comment>\n";

    return xmlFile;
  }

  public static String petsToXML(PetList pets)
  {
    String xmlFile = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
    xmlFile += "<petList>\n";
    for (int i = 0; i < pets.size(); i++)
    {
      xmlFile += petToXML(pets.get(i));
    }
    xmlFile += "</petList>\n";
    return xmlFile;
  }


  public static String dogToXML(Dog dog)
  {
    String xmlFile =
        "<dog>\n"
        + "<name>" + dog.getName() + "<name>\n"
        + "<color>" + dog.getColor() + "<color>\n"
        + "<age>" + dog.getAge() + "<age>\n";

    return xmlFile;
  }

//  public static String dogsToXML(PetList dogs)
//  {
//String xmlFile = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
  //    xmlFile += "<dogList>\n";
  //    for (int i = 0; i < dogs.size(); i++)
  //    {
  //      xmlFile += dogToXML(pets.get(i));
  //    }
  //    xmlFile += "</dogList>\n";
  //    return xmlFile;
//  }

}

