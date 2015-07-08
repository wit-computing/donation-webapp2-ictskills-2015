/*   package controllers;

import java.util.*;

import models.Candidate;
import models.Donation;
import models.User;
import play.*;
import play.mvc.*;

public class NEWdONATION extends Controller
{
  @Before
  static void checkAuthentification()
  {
    if(session.contains("logged_in_userid") == false)
      Accounts.login();
  }
  
  public static void index()
  {  
    User user = Accounts.getCurrentUser();
    List<Candidate> candidates = Candidate.findAll();
    String prog = getPercentTargetAchieved();
    String progress = prog + "%";
    Logger.info("Donation ctrler : user is " + user.email);
    Logger.info("Donation ctrler : percent target achieved " + progress);
    render(user, candidates);
  }

  public static void donate(long amountDonated, String methodDonated)
  {
    User user = Accounts.getCurrentUser();
    Logger.info("amount donated " + amountDonated + " " + "method donated " + methodDonated);
    addDonation(user, amountDonated, methodDonated);
    index();
  }

  private static void addDonation(User user, long amountDonated, String methodDonated)
  {
    Donation bal = new Donation(user, amountDonated, methodDonated);
    bal.save();
  }

  private static long getDonationTarget()
  {
    return 20000;
  }

  public static String getPercentTargetAchieved()
  {
    List<Donation> allDonations = Donation.findAll();
    long total = 0;

    for (Donation donation : allDonations)
    {
      total += donation.received;
    }

    long target = getDonationTarget();
    long percentachieved = (total * 100 / target);
    String progress = String.valueOf(percentachieved);
    Logger.info("Percent of target achieved (string) " + progress + 
        "percentachieved (long)= " + percentachieved);

    return progress;
  }
}*/