/*package model;

public class AllansXML
{
  import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

  /******  Example using the built-in XML classes in Java ******/
  /*public class Example2
  {
    public static void main(String[] args)
    {
      StudentList studentList = new StudentList();

      Student s1 = new Student("Bob", "Bobson", "Denmark");
      Student s2 = new Student("Bob2", "Bobson", "Denmark");
      Student s3 = new Student("Bob3", "Bobson", "Denmark");

      studentList.add(s1);
      studentList.add(s2);
      studentList.add(s3);


      /************* Writing *************/

     /* DocumentBuilder builder = null;
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      try
      {
        builder = factory.newDocumentBuilder();
      }
      catch (ParserConfigurationException e)
      {
        System.out.println("Error");
        System.exit(1);
      }

      //create the XML document
      Document doc = builder.newDocument();

      Element rootElement = doc.createElement("students");

      //Add each Student object from the list as XML tags
      for (int i = 0; i < studentList.size(); i++)
      {
        Element subElement = doc.createElement("student");
        rootElement.appendChild(subElement);

        Element subSubElement = doc.createElement("firstname");
        subSubElement.appendChild(doc.createTextNode(studentList.get(i).getFirstName()));
        subElement.appendChild(subSubElement);

        subSubElement = doc.createElement("lastname");
        subSubElement.appendChild(doc.createTextNode(studentList.get(i).getLastName()));
        subElement.appendChild(subSubElement);

        subSubElement = doc.createElement("country");
        subSubElement.appendChild(doc.createTextNode(studentList.get(i).getCountry()));
        subElement.appendChild(subSubElement);
      }

      doc.appendChild(rootElement);

      //Format and save as XML file
      try
      {
        Transformer transformer = TransformerFactory.newInstance().newTransformer();

        //make new lines and indent the elements in the XML file
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        //write the XML file
        File file = new File("list2.xml");
        transformer.transform(new DOMSource(doc), new StreamResult(file));
      }
      catch (TransformerConfigurationException e)
      {
        System.out.println("Error configuring XML file");
      }
      catch (TransformerException e)
      {
        System.out.println("Error creating XML file");
      }


      /************* Reading *************/
     /* DocumentBuilder builder2 = null;
      DocumentBuilderFactory factory2 = DocumentBuilderFactory.newInstance();
      try
      {
        builder2 = factory2.newDocumentBuilder();
      }
      catch (ParserConfigurationException e)
      {
        System.out.println("Error");
        System.exit(1);
      }

      Document doc2 = null;
      try
      {
        doc2 = builder2.parse("list2.xml");
      }
      catch (SAXException e)
      {
        System.out.println("Error parsing");
        System.exit(1);
      }
      catch (IOException e)
      {
        System.out.println("Error reading file");
        System.exit(1);
      }

      Transformer transformer2 = null;
      try
      {
        transformer2 = TransformerFactory.newInstance().newTransformer();
        transformer2.setOutputProperty(OutputKeys.INDENT, "no");
      }
      catch (TransformerConfigurationException e)
      {
        System.out.println("Error");
      }

      NodeList rootList = doc2.getElementsByTagName("student");

      for (int i = 0; i < rootList.getLength(); i++)
      {
        Node rootNode = rootList.item(i);

        NodeList subNodes = rootNode.getChildNodes();

        String firstName = "";
        String lastName = "";
        String country = "";

        for (int j = 0; j < subNodes.getLength(); j++)
        {
          if(subNodes.item(j).getNodeName().equals("firstname"))
          {
            firstName = subNodes.item(j).getTextContent();
          }
          else if(subNodes.item(j).getNodeName().equals("lastname"))
          {
            lastName = subNodes.item(j).getTextContent();
          }
          else if(subNodes.item(j).getNodeName().equals("country"))
          {
            country = subNodes.item(j).getTextContent();
          }
        }
        System.out.println("-----------------");
        System.out.println(new Student(firstName, lastName, country));
      }
    }
  }

}
*/