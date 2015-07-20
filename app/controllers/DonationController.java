package controllers;

import java.util.*;

import org.json.simple.JSONObject;

import models.Candidate;
import models.Donation;
import models.User;
import play.*;
import play.mvc.*;

public class DonationController extends Controller
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
    
    String progress = "0%";
    String candProgress = "";
    
    Logger.info("Donation ctrler : user is " + user.email);
    Logger.info("Donation ctrler : percent target achieved " + progress);
    
    render(user, candidates, progress, candProgress);
  }


  public static void donate(long amountDonated, String methodDonated, String email)
  {
    User user = Accounts.getCurrentUser();
    List<Candidate> candidates = Candidate.findAll();
    Candidate candidate = Candidate.findByEmail(email);
      
    addDonation(user, candidate, amountDonated, methodDonated);
    
    String prog = getPercentTargetAchieved(candidate);
    
    String progress = "";
    String candProgress = "";
    
    if(Integer.parseInt(prog) >= 100)
    {
      progress = "100%";
      candProgress = "Target Achieved for " + candidate;
    }
    else
    {
      progress = prog + "%";
      candProgress= prog + "% of Target Achieved to date for " + candidate;    
    }
    
    Logger.info("amount donated " + amountDonated + " " + "method donated " + methodDonated);

    renderTemplate("DonationController/index.html", user, candidates, progress, candProgress);
  }

  private static void addDonation(User user, Candidate candidate, long amountDonated, String methodDonated)
  {
    Donation bal = new Donation(user, candidate, amountDonated, methodDonated);
    Logger.info("donation added " + bal + user + candidate + amountDonated + methodDonated);
    bal.save();
  }

  private static long getDonationTarget(Candidate candidate)
  {
    long target = candidate.donationTarget;
    return target;
  }

  public static String getPercentTargetAchieved(Candidate candidate)
  {
    List<Donation> allDonations = Donation.findAll();

    long total = 0;

    for (Donation donation : allDonations)
    {
      if (donation.to == candidate)
      {
        Logger.info("donation added " + donation.toString() + " for cand " + candidate);
        total += donation.received;
      }
    }

    long target = getDonationTarget(candidate);
    long percentachieved = (total * 100 / target);
    String progress = String.valueOf(percentachieved);
    Logger.info("Percent of target achieved (string) " + progress + "percentachieved (long)= " + percentachieved);

    return progress;
  }
}