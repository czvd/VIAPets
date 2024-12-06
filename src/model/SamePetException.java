package model;

public class SamePetException extends IllegalArgumentException
{
  public SamePetException()
  {
    super("Same pet cannot be selected");
  }
}
