package utils;

import java.util.Comparator;

import models.*;

public class CandidateComparator implements Comparator<Candidate>
{

  @Override
  public int compare(Candidate o1, Candidate o2)
  {

    String o1Name = o1.firstName + o1.lastName;
    String o2Name = o2.firstName + o2.lastName;
    return o1Name.compareToIgnoreCase(o2Name);
  }
}
