package model;

import ModelManager.VIAPetsModelManager;
import parser.ParserException;
import parser.XmlJsonParser;

public class XMLConvertor {
  public static void main(String[] args)
  {
    VIAPetsModelManager modelManager = new VIAPetsModelManager("VIAPets.bin");;
    PetList petList = modelManager.getAllPets();

    XmlJsonParser parser = new XmlJsonParser();

    //write the XML file
    try
    {
      parser.toXml(petList, "list.xml");
    }
    catch (ParserException e)
    {
      e.printStackTrace();
    }

    //read the XML file
    try
    {
      petList = parser.fromXml("list.xml", PetList.class);
      System.out.println(petList);
    }
    catch (ParserException e)
    {
      e.printStackTrace();
    }
  }
}
