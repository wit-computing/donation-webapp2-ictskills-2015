package controllers;

import java.util.*;

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
    
    if (Candidate.findByEmail(email) == null)
    
    String prog = 0;
    String progress = prog + "%";
    Logger.info("Donation ctrler : user is " + user.email);
    Logger.info("Donation ctrler : percent target achieved " + progress);
    render(user, candidates, progress);
  }

  public static void donate(long amountDonated, String methodDonated, String email)
  {
    User user = Accounts.getCurrentUser();
    Candidate candidate = Candidate.findByEmail(email);
    List<Candidate> candidates = Candidate.findAll();
       
    String prog = getPercentTargetAchieved(email);
    String progress =  candidate + "'s Target Achieved " + prog + "%";
    //Logger.info("amount donated " + amountDonated + " " + "method donated " + methodDonated);
    addDonation(user, candidate, amountDonated, methodDonated);
    renderTemplate("DonationController/index.html", user, candidates, progress);
  }

  private static void addDonation(User user, Candidate candidate, long amountDonated, String methodDonated)
  {
    Donation bal = new Donation(user, candidate, amountDonated, methodDonated);
    bal.save();
  }

  private static long getDonationTarget()
  {
    return 20000;
  }

  public static String getPercentTargetAchieved(String email)
  {
   
    List<Donation> allDonations = Donation.findAll();
    Candidate candidate = Candidate.findByEmail(email);
    
    long total = 0;
   
    for (Donation donation : allDonations)
    {
      if (donation.to == candidate)
      {
        //Logger.info("donation added " + donation.toString() + " for cand "+candidate);
        total += donation.received;
      }
    }

    long target = getDonationTarget();
    long percentachieved = (total * 100 / target);
    String progress = String.valueOf(percentachieved);
    //Logger.info("Percent of target achieved (string) " + progress + "percentachieved (long)= " + percentachieved);

    return progress;
  }
}