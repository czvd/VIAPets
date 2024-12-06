package model;

public class UnavailableReservationException extends IllegalArgumentException
{
  public UnavailableReservationException()
  {
    super("There is no reservation available");
  }
}
