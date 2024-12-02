package model;

import java.util.ArrayList;

/**
 * A class containing the list of KennelReservation objects,
 * also value for maximum limit in a timeline
 * @author Richard Vegh
 * @version 1.0
 */
public class KennelReservationList
{
  private ArrayList<KennelReservation> kennelReservations;
  private int maxlimit = 10;

  /**
   * no-argument constructor used for setting the Kennel reservations over time not when created
   */
  public KennelReservationList()
  {
    kennelReservations = new ArrayList<KennelReservation>();
  }
  /**
   * one-argument constructor initializing KennelReservationList,
   * used when maximum limit of reservations in a time period is 10
   * @param kennelReservations Array list of KennelReservation objects
   */
  public KennelReservationList(ArrayList<KennelReservation> kennelReservations)
  {
    this.kennelReservations = kennelReservations;
  }
  /**
   * two-argument constructor initializing KennelReservationList,
   * used when maximum limit is known and not initial for a time period
   * @param maxlimit value for maximum limit of stored pets in a timeline
   * @param kennelReservations Array list of KennelReservation objects
   */
  public KennelReservationList(int maxlimit,ArrayList<KennelReservation> kennelReservations)
  {
    this.kennelReservations = kennelReservations;
    this.maxlimit = maxlimit;
  }

  /**
   * method for adding a KennelReservation object to the list
   * @param kennelReservation a KennelReservation object
   */
  public void addReservation(KennelReservation kennelReservation){ //adding a reservation
    kennelReservations.add(kennelReservation);
  }

  /**
   * method for removing KennelReservation object from the list
   * @param kennelReservation a KennelReservation object
   */
  public void removeReservation(KennelReservation kennelReservation)
  {
    kennelReservations.remove(kennelReservation);
  }

  /**
   * method used for returning all the KennelReservation object in one ArrayList
   * @return an array list of KennelReservation objects
   */
  public ArrayList<KennelReservation> getAllReservations()
  {
    return kennelReservations;
  }
  /**
   * method for calculating the number of reservations available in the given time
   * @param startDate Date object, with data for year, month, name, storing the start date
   * @param endDate Date object, with value for the date of finishing the booking(year,month,day)
   * @return number of available places for reservation during the given timeline
   */
  public int howMuchIsAvailable(Date startDate, Date endDate)
  {
    int howMuchIsInThatTime=0;
    for (int i = 0; i <kennelReservations.size() ; i++)
    {
      if (!(kennelReservations.get(i).getEndDate().isBefore(startDate))&& //if endDate(of reservation i) is not before startDate(of preferred value)
          kennelReservations.get(i).getStartDate().isBefore(endDate)) //and startDate(of reservation i) is before endDate(of preferred value)
      {
        howMuchIsInThatTime++;
      }
    }
    return maxlimit-howMuchIsInThatTime;
  }
  /**
   * method for checking if a reservation is available during the given timeline
   * @param startDate Date object, with data for year, month, name, storing the start date
   * @param endDate Date object, with value for the date of finishing the booking(year,month,day)
   * @return reservation available or not
   */
  public boolean isReservationAvailable(Date startDate, Date endDate)
  {
    return howMuchIsAvailable(startDate,endDate)>0;
  }
  /**
   * method for checking if a reservation is available during the given timeline,
   * using given max limit and not initial
   * @param startDate Date object, with data for year, month, name, storing the start date
   * @param endDate Date object, with value for the date of finishing the booking(year,month,day)
   * @param maxlimit value for maximum limit of stored pets
   * @return reservation available or not based on a maxlimit, we check.
   */
  public boolean isReservationAvailable(Date startDate, Date endDate, int maxlimit)
  {
    return (maxlimit-howMuchIsAvailable(startDate,endDate))>0;
  }
  /**
   * method for selecting all reservations with the specific customer in it.
   * @param name
   * @return  a reservationList if found else a (null)
   */
  public KennelReservation getReservationByCustomerName(String name)
  {
    for (int i = 0; i < kennelReservations.size(); i++)
    {
      if (kennelReservations.get(i).getCustomer().isEqual(name))
      {
        return kennelReservations.get(i);
      }
    }
    return null;
  }
  /**
   * Returning reservationList, based on pets in petList and searches trough color.
   * @param color
   * @return
   */
  public KennelReservationList getReservationByPetColor(String color)
  {
    KennelReservationList result = new KennelReservationList();
    for (KennelReservation kennelReservation : kennelReservations)
    {
      if (kennelReservation.getPet().getColor().equals(color))
      {
        result.addReservation(kennelReservation);
      }
    }
    return result;
  }
  /**
   * Returning reservationList, based on pets in petList and searches trough species.
   * @param speciesOrBreed
   * @return
   */
  public KennelReservationList getReservationByPetSpecies(String speciesOrBreed)
  {
    KennelReservationList result = new KennelReservationList();
    for (KennelReservation kennelReservation : kennelReservations)
    {
      int type = kennelReservation.getPet().getType();

      switch (type)
      {
        case 1:
        case 2:
          if (kennelReservation.getPet().getBreed().equals(speciesOrBreed))
          {
            result.addReservation(kennelReservation);
          }
          break;
        case 3:
        case 4:
        case 5:
        case 6:
          if (kennelReservation.getPet().getSpecies().equals(speciesOrBreed))
          {
            result.addReservation(kennelReservation);
          }
          break;
        default:
        default:
          throw new UnknownPetTypeException("Pet type cannot be "+type);
      }
    }
    return result;
  }

  /**
   * Returning all reservations as a String
   * @return
   */
  public String toString() {
    return kennelReservations.toString();
  }

}
