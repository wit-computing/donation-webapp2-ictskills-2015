package controllers;

import java.util.*;

import org.json.simple.*;

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
    if (session.contains("logged_in_userid") == false)
      Accounts.login();
  }

  public static void index()
  {
    User user = Accounts.getCurrentUser();
    List<Candidate> candidates = Candidate.findAll();

    Logger.info("Donation ctrler : user is " + user.email);

    render(user, candidates);
  }

  /*
   * Runs when donate button clicked; Donation added to candidate, percent
   * target achieved for candidate found and stored in prog, candidate name
   * stored in candidate2 to allow render to page using ajax. String prog is
   * parsed to Integer to allow If statement to check if target reached or not,
   * alters message accordingly. New JSON object created to allow progress,
   * candidate and message re target be rendered to page using ajax in donate.js
   */
  public static void donate(long amountDonated, String methodDonated,
      String candidateEmail)
  {
    User user = Accounts.getCurrentUser();
    Candidate candidate = Candidate.findByEmail(candidateEmail);

    addDonation(user, candidate, amountDonated, methodDonated);

    String progress = "";
    String prog = getPercentTargetAchieved(candidate);
    String candidate2 = candidate.toString();
    String progressLabel = "";

    if (Integer.parseInt(prog) >= 100)
    {
      progress = "100%";
      progressLabel = "Target Achieved for " + candidate;
    }
    else
    {
      progress = prog + "%";
      progressLabel = prog + "% of Target Achieved to date for " + candidate;
    }

    JSONObject obj = new JSONObject();
    obj.put("progress", progress);
    obj.put("candidate2", candidate2);
    obj.put("progressLabel", progressLabel);
    renderJSON(obj);
  }

  private static void addDonation(User user, Candidate candidate,
      long amountDonated, String methodDonated)
  {
    Donation bal = new Donation(user, candidate, amountDonated, methodDonated);
    Logger.info("donation added " + bal + user + candidate + amountDonated
        + methodDonated);
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
        Logger.info("donation added " + donation.toString() + " for cand "
            + candidate);
        total += donation.received;
      }
    }

    long target = getDonationTarget(candidate);
    long percentachieved = (total * 100 / target);
    String progress = String.valueOf(percentachieved);
    Logger.info("Percent of target achieved (string) " + progress
        + "percentachieved (long)= " + percentachieved);

    return progress;
  }
}
