package main;

import model.*;

import utils.FileHandler;

import java.io.FileNotFoundException;
import java.io.IOException;

public class LoadInitialData
{
  public static void main(String[] args)
  {
    VIAPets viaPets = new VIAPets();
    try
    {
      FileHandler.writeToBinaryFile("VIAPets.bin", viaPets);
    }
    catch (FileNotFoundException e)
    {
      System.out.println("Error opening file ");
    }
    catch (IOException e)
    {
      System.out.println("IO Error writing to file ");
    }
    System.out.println("Done");
  }
}
