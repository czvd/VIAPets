package model;

import ModelManager.VIAPetsModelManager;
import parser.ParserException;
import parser.XmlJsonParser;

public class XMLConvertor {
  public static void write(VIAPetsModelManager manager)
  {
    PetList petList = manager.getAllPets();

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


  }
}
