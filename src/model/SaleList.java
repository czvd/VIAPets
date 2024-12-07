package model;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;

public class SaleList implements Serializable
{
  private ArrayList<Sale> sales;

  public SaleList()
  {
    sales = new ArrayList<>();
  }
  public SaleList(ArrayList<Sale> saleList)
  {
    sales = saleList;
  }

  public void addSale(Sale sale)
  {
    sales.add(sale);
  }

  public SaleList getSalesByCustomerName(String name)
  {
    SaleList result = new SaleList();

    for (Sale sale : sales)
    {
      if (sale.getCustomer().getName().equals(name))
      {
        result.addSale(sale);
      }
    }
    return result;
  }

  public SaleList getSalesByPetColor(String color)
  {
    SaleList result = new SaleList();

    for (Sale sale : sales)
    {
      if (sale.getPet().getColor().equals(color))
      {
        result.addSale(sale);
      }
    }
    return result;
  }

  public void removeSale(Sale sale) {
    for (int i = 0; i < sales.size(); i++)
    {
      Sale temp = sales.get(i);
      if (temp.equals(sale))
      {
        sales.remove(i); // Remove the sale by index
        break; // Exit the loop after removing the sale
      }
    }
  }

  /*  public SaleList getSalesByPetSpeciesOrBreed(String speciesOrBreed) {
    SaleList result = new SaleList();
    for (Sale sale : sales)
    {
     int type = sale.getPet().getType();

      if(sale.getPet() instanceof Dog || sale.getPet() instanceof Cat)
      switch (type)
      {
        case 1:
        {
          Dog temp = (Dog)sale.getPet();
          if (temp.getSpecies().equals(speciesOrBreed)) {
            result.addSale(sale);
          }
          break;
        }
        case 2:
        {
          Cat temp = (Cat)sale.getPet();
          if (temp.getSpecies().equals(speciesOrBreed)) {
            result.addSale(sale);
          }
        }
          break;
        case 3:
        {
          Bird temp = (Bird)sale.getPet();
          if (temp.getSpecies().equals(speciesOrBreed)) {
            result.addSale(sale);
          }
        }
          break;
        case 4:
        {
          Rodent temp = (Rodent)sale.getPet();
          if (temp.getSpecies().equals(speciesOrBreed)) {
            result.addSale(sale);
          }
        }
        break;
        case 5:
        {
          Fish temp = (Fish)sale.getPet();
          if (temp.getSpecies().equals(speciesOrBreed)) {
            result.addSale(sale);
          }
        }
        break;
        case 6:
        {
          Various temp = (Various)sale.getPet();
          if (temp.getSpecies().equals(speciesOrBreed)) {
            result.addSale(sale);
          }
        }
        break;
        default:
          throw new UnknownPetTypeException("Pet type cannot be "+ type);
      }
    }
    return result;
  }
*/
  public int size()
  {
    return sales.size();
  }

  public Sale get(int index)
  {
    if(index<sales.size())
    {
      return sales.get(index);
    }
    else
    {
      return null;
    }
  }
  public String toString()
  {
    return "All sales: " + sales;
  }
}
