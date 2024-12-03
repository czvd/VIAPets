package model;

public class IllegalTypeException extends RuntimeException
{
  public IllegalTypeException()
  {
    super("Invalid type");
  }
}
