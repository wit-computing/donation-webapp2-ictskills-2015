package controllers;

import java.util.*;

import models.Candidate;
import models.Donation;
import models.User;
import play.*;
import play.mvc.*;

public class DonationController extends Controller
{
  public static void index()
  {
    User user = Accounts.getCurrentUser();
    if (user == null)
    {
      Logger.info("Donation class : Unable to getCurrentuser");
      Accounts.login();
    }
    else
    {
      String prog = getPercentTargetAchieved();
      String progress = prog + "%";
      Logger.info("Donation ctrler : user is " + user.email);
      Logger.info("Donation ctrler : percent target achieved " + progress);
      render(user, progress);
    }
  }

  public static void donate(long amountDonated, String methodDonated)
  {
    User from = Accounts.getCurrentUser();
    Candidate to = 
    
    Logger.info("amount donated " + amountDonated + " " + "method donated " + methodDonated);
      
    addDonation(from, to, amountDonated, methodDonated);
    
    index();
  }

  private static void addDonation(User from, Candidate to,  long amountDonated, String methodDonated)
  {
    Donation bal = new Donation(from, to, amountDonated, methodDonated);
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

  public static void renderReport()
  {
    User user = Accounts.getCurrentUser();
    List<Donation> donations = Donation.findAll();
    render(user, donations);
  }
}