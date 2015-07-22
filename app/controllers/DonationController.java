package controllers;

import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import models.Candidate;
import models.Donation;
import models.GeoLocation;
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


  public static void donate(long amountDonated, String methodDonated, String candidateEmail)
  {
    User user = Accounts.getCurrentUser();
    Candidate candidate = Candidate.findByEmail(candidateEmail);
      
    addDonation(user, candidate, amountDonated, methodDonated);
    
    String progress = "";
    String prog = getPercentTargetAchieved(candidate);
    String candidate2 = candidate.toString();
    String progressLabel = "";
    
    if(Integer.parseInt(prog) >= 100)
    {
      progress = "100%";
      progressLabel = "Target Achieved for " + candidate;
    }
    else
    {
      progress = prog + "%";
      progressLabel = prog + "% of Target Achieved to date for " + candidate;    
    }
    
    Logger.info("amount donated " + amountDonated + " " + "method donated " + methodDonated);
    
    JSONObject obj = new JSONObject();
    obj.put("progress", progress);
    obj.put("candidate2", candidate2);
    obj.put("progressLabel", progressLabel);
    renderJSON(obj);
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
  
  public static void userLocation()
  {
    List<User> users = User.findAll();
    List<Donation> allDonations = Donation.findAll();
    
    JSONArray list = new JSONArray();
     
    for (User user : users)
    {
      for (Donation don : allDonations)
      {
        if (don.from == user)
        {
          list.add(Arrays.asList(user.toString(), user.located.getLat(), user.located.getLong()));
        }
      }
    }
    renderJSON(list);
  }
}

