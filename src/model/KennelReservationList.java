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
  //returning a value how much is available in the timeline(need start and end date)
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
  //check if during timeline reservation is available
  //Use this if there is a different limit than essential
  public boolean isReservationAvailable(Date startDate, Date endDate, int maxlimit)
  {
    return howMuchIsAvailable(startDate,endDate)<maxlimit;
  }
  //Searching trough the reservations and checking maching name
  //returning a reservationList if found else a         <!null!>
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
  //Returning reservationList, based on pets in petList and searches trough color.
  public KennelReservationList getReservationByPetColor(String color,PetList petList)
  {
    ArrayList<Pet> petsByColor;
    ArrayList<Pet> allPetsFromPetList = petList.getPetsByType().getPets();

    for (int i = 0; i < allPetsFromPetList.size(); i++)
    {
      if (allPetsFromPetList.get(i).getColor().equals(color))
      {
        petsByColor.add(allPetsFromPetList.get(i));
      }
    }
    if (petsByColor.isEmpty())
    {
      return null;
    }
    ArrayList<KennelReservation> reservationsByPet = new ArrayList<>();
    for (int i = 0; i < kennelReservations.size() ; i++)
    {
      KennelReservation temp = kennelReservations.get(i);
      for (int j = 0; j < petsByColor.size(); j++)
      {
        if (temp.getPet().equals(petsByColor.get(j)))
        {
          reservationsByPet.add(kennelReservations.get(i));
        }
      }
    }

    return new KennelReservationList(reservationsByPet);
  }
  //Returning reservationList, based on pets in petList and searches trough species.
  public KennelReservationList getReservationByPetSpecies(String species,PetList petList)
  {
    ArrayList<Pet> petsBySpecies;
    ArrayList<Pet> allPetsFromPetList = petList.getPetsByType().getPets();

    for (int i = 0; i < allPetsFromPetList.size(); i++)
    {
      if (allPetsFromPetList.get(i).getSpecies().equals(species))
      {
        petsBySpecies.add(allPetsFromPetList.get(i));
      }
    }
    if (petsBySpecies.isEmpty())
    {
      return null;
    }
    ArrayList<model.KennelReservation> reservationsByPet = new ArrayList<>();
    for (int i = 0; i < kennelReservations.size() ; i++)
    {
      model.KennelReservation temp = kennelReservations.get(i);
      for (int j = 0; j < petsBySpecies.size(); j++)
      {
        if (temp.getPet().equals(petsBySpecies.get(j)))
        {
          reservationsByPet.add(kennelReservations.get(i));
        }
      }
    }

    return new KennelReservationList(reservationsByPet);
  }
  //general toString method
  public String toString()
  {
    return kennelReservations.toString();
  }

}
