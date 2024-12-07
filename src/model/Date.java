package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.*;

public class Date implements Serializable
{
  private int day;
  private int month;
  private int year;
  private int hour;
  private int minute;

  public Date()
  {
    LocalDate currentDate = LocalDate.now();
    this.day = currentDate.getDayOfMonth();
    this.month = currentDate.getMonthValue();
    this.year = currentDate.getYear();

  }
  public Date(int day, int month, int year)
  {
    this.day = day;
    this.month = month;
    this.year = year;
    minute = 0;
    hour = 0;
  }

  public Date(int day,int month,int year, int hour, int minute)
  {
    this.day = day;
    this.month = month;
    this.year = year;
    this.hour = hour;
    this.minute = minute;
  }

  public static Date today()
  {
    LocalDate currentDate = LocalDate.now();
    LocalTime now = LocalTime.now();
    int currentday = currentDate.getDayOfMonth();
    int currentmonth = currentDate.getMonthValue();
    int currentyear = currentDate.getYear();
    int currenthour = now.getHour();
    int currentminute = now.getMinute();
    return new Date(currentday, currentmonth, currentyear, currenthour,currentminute);
  }

  public int getDay()
  {
    return day;
  }

  public int getMonth()
  {
    return month;
  }

  public int getYear()
  {
    return year;
  }

  public int getHour()
  {
    return hour;
  }

  public int getMin()
  {
    return minute;
  }

  public void setHour(int hour)
  {
    this.hour = hour;
  }

  public void setMin(int minute)
  {
    this.minute = minute;
  }

  public void setDay(int day)
  {
    this.day = day;
  }

  public void setMonth(int month)
  {
    this.month = month;
  }

  public void setYear(int year)
  {
    this.year = year;
  }

  public static int calculatePeriod(Date startDate, Date endDate)
  {
    //Checking if the start date is after than end date, else returns error message
    if(endDate.isBefore(startDate)) //the comparison will be done once
    {
      throw new IllegalArgumentException("Check-out date must be after check-in date.");//this takes 1
    }
    //Total days is the days between the start-end date, start value is the days in first month
    int totalDays= startDate.daysInMonth() - startDate.getDay(); //this takes 2
    //If year is the same
    if(startDate.getYear() == endDate.getYear()){ //the comparison will be done once
      if (startDate.getMonth() == endDate.getMonth()) //the comparison will be done once
      {
        //month and day is the same == same day
        if (startDate.getDay() == endDate.getDay()){ //the comparison will be done once
          return 0; // 1 return
        }
        //the month is the same, return just the days between
        return endDate.getDay() - startDate.getDay(); //1 calculation 1 return
      }
      //month are not the same, goes through every month between, plus add days in first and last month
      int checkMonth = startDate.getMonth() + 1; // this takes 2
      for (int i = endDate.getMonth() - startDate.getMonth()-1; i>0;i--) //1 "=" 1 for calculation, 1 for comparison, and we loop n times
      {
        totalDays+=daysInMonth(checkMonth,startDate.getYear()); //for each iteration we have 1 "+" and 1 "=" and call the method daysInMonths worth
        checkMonth++; //this takes 1
      }
      return totalDays+endDate.getDay(); //1 calculation 1 return
    }
    //if year is not the same
    //start with first year, where the startdate is in
    int compare = startDate.getMonth()+1; // this cost 1
    for (int i=12;i>=compare;i--) //we loop n times 1 "=" and 1 for comparison
    {
      totalDays+=daysInMonth(i, startDate.getYear()); //for each iteration we have 1"+" and 1 "=" and call the method daysInMonths worth
    }
    //if there is more than 1 year we count the whole year
    int difference = endDate.getYear() - startDate.getYear(); //this takes 2
    if (difference>1) //1 for comparison
    {
      int compares =startDate.getYear()+1; // this is worth 3
      int getvalue = endDate.getYear(); // this is worth 2
      for (int i = compares; i < getvalue; i++) //we loop n times, 1 for "=", 1 for comparison
      {
        if(isLeapYear(i)) // this will be done n times
        {
          totalDays+=366; //this takes 2
        }
        totalDays+=365;//this takes 2
      }
    }
    // the last year with months and days added together
    int getendvalue = endDate.getMonth(); // this is worth 2
    for (int i=1; i<getendvalue;i++) // we loop n times, 1 for "=" and 1 for comparison
    {
      totalDays+=daysInMonth(i, endDate.getYear()); //for each iteration we have 1"+" and 1 "=" and call the method daysInMonths worth
    }
    //lastly adding the last day
    return totalDays+endDate.getDay(); // 1 for calculating 1 for return

  }
  private boolean isLeapYear()
  {
    if (year % 400 == 0 || (year % 100 != 0 && year % 4 == 0))
    {
      return true;
    }
    else
    {
      return false;
    }
  }

  public static boolean isLeapYear(int year)
  {
    return year % 400 == 0 || (year % 100 != 0 && year % 4 == 0);
  }

  public int daysInMonth()
  {
    if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8
        || month == 10 || month == 12)
    {
      return 31;
    }

    else if (month == 4 || month == 6 || month == 9 || month == 11)
    {
      return 30;
    }

    else if (month == 2)
    {
      if (isLeapYear())
      {
        return 29;
      }
      else
      {
        return 28;
      }
    }
    else
    {
      return 0;
    }
  }

  public static int daysInMonth(int month, int year)
  {
    if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8
        || month == 10 || month == 12)
    {
      return 31;
    }

    else if (month == 4 || month == 6 || month == 9 || month == 11)
    {
      return 30;
    }

    else if (month == 2)
    {
      if (isLeapYear(year))
      {
        return 29;
      }
      else
      {
        return 28;
      }
    }
    else
    {
      return 0;
    }
  }

  public boolean isBefore(Date date2)
  {
    if (year < date2.year)
    {
      return true;
    }
    else if (year == date2.year)
    {
      if (month < date2.month)
      {
        return true;
      }

      else if (month == date2.month)
      {
        if (day < date2.day)
        {
          return true;
        }
      }
    }
    return false;
  }

  public boolean equals(Object obj)
  {
    if(obj==null || getClass() != obj.getClass())
    {
      return false;
    }
    Date other = (Date)obj;
    return this.day== other.day
        && this.month == other.month
        && this.year == other.month
        && this.hour == other.hour
        && this.minute == other.minute;
  }


  public String toString()
  {
    return  month + "/"+day+"/" + year +" -- "+hour+":"+minute;
  }

  public Date copy()
  {
    return new Date(day, month, year, hour, minute);
  }
}
