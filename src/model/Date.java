package model;

import java.io.Serializable;
import java.time.LocalDate;

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
    int currentday = currentDate.getDayOfMonth();
    int currentmonth = currentDate.getMonthValue();
    int currentyear = currentDate.getYear();
    return new Date(currentday, currentmonth, currentyear);
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
    //checking if the start date is after than end date, else returns error message
    if(endDate.isBefore(startDate))
    {
      throw new IllegalArgumentException("Check-out date must be after check-in date.");
    }
    //total days is the days between the start-end date, start value is the days in first month
    int totalDays= startDate.daysInMonth() - startDate.getDay();
    //if year is the same
    if(startDate.getYear() == endDate.getYear()){
      if (startDate.getMonth() == endDate.getMonth())
      {
        //month and day is the same == same day
        if (startDate.getDay() == endDate.getDay()){
          return 0;
        }
        //the month is the same, return just the days between
        return endDate.getDay() - startDate.getDay();
      }
      //month are not the same, goes trough every month between, plus add days in first and last month
      int checkMonth = startDate.getMonth() + 1;
      for (int i = endDate.getMonth() - startDate.getMonth()-1; i>0;i--)
      {
        totalDays+=daysInMonth(checkMonth,startDate.getYear());
        checkMonth++;
      }
      return totalDays+endDate.getDay();
    }
    //if year is not the same
    //start with first year, where the startdate is in
    for (int i=startDate.getMonth()+1;i<=12;i++)
    {
      totalDays+=daysInMonth(i, startDate.getYear());
    }
    //if there is more than 1 years we count the whole year
    int difference = endDate.getYear() - startDate.getYear();
    if (difference>1)
    {
      for (int i = startDate.getYear()+1; i < endDate.getYear(); i++)
      {
        if(isLeapYear(i))
        {
          totalDays+=366;
        }
        totalDays+=365;
      }
    }
    // the last year with months and days added together
    for (int i=1; i<endDate.getMonth();i++)
    {
      totalDays+=daysInMonth(i, endDate.getYear());
    }
    //lastly adding the last day
    return totalDays+endDate.getDay();

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
    return day+":" + month + ":" + year + ":" + hour + ":" + minute;
  }

}
