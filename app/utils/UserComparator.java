package utils;


import java.util.Comparator;

import models.*;

public class UserComparator implements Comparator<User>
{

  @Override
  public int compare(User one, User another)
  {
    return one.getName().compareTo(another.getName());
  }

}