package model;

public class IllegalNameException extends RuntimeException
{
  public IllegalNameException()
  {
    super("Name can't contain numbers");
  }
}


