package model;

public class IllegalBreedSpecException extends RuntimeException
{
  public IllegalBreedSpecException()
  {
    super("Breed/Species can't contain numbers");
  }
}

