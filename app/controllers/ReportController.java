package controllers;

import java.util.*;

import models.Candidate;
import models.Donation;
import models.User;
import play.*;
import play.mvc.*;

public class ReportController extends Controller
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
    List<Donation> donations = Donation.findAll();
    List<Candidate> candidates = Candidate.findAll();
    List<User> users = User.findAll();
    
    render(user, users, donations, candidates);
  }
  
  public static void filterCandidate(String email)
  {
    User user = Accounts.getCurrentUser();
    List<User> users = User.findAll();
    List<Donation> donations = new ArrayList<>();
    List<Donation> allDonations = Donation.findAll();
    List<Candidate> candidates = Candidate.findAll();
    
    Candidate candidate = Candidate.findByEmail(email);
      
    for (Donation don : allDonations)
    {      
      if (don.to == candidate)
      {
         donations.add(don);
      }
    }
      renderTemplate("ReportController/index.html", user, users, donations, candidates);  
  } 
  
  public static void filterDonor(String donorEmail)
  {
    User user = Accounts.getCurrentUser();
    List<Donation> donations = new ArrayList<Donation>();
    List <User> users = User.findAll();
    List<Candidate> candidates = Candidate.findAll();
    List <Donation> allDonations = Donation.findAll();
    
    User donor = User.findByEmail(donorEmail);
    for (Donation don : allDonations)
    {
      if (don.from.email == donor.email)
      {
        donations.add(don);
      }
    }
    renderTemplate("ReportController/index.html", user, users, donations, candidates);  
  }
  
  /*
  static List<Donation> getDonors()
  {
    Set<String> emailSet = new HashSet<String>();
    List<Donation> allDonations = Donation.findAll();
    List<Donation> donorDonations = new ArrayList<>();
        
    for (Donation don : allDonations)
    {
      if (emailSet.add(don.from.email) && )
      {
        donorDonations.add(don);
      }
    }
    return donorDonations;
  }

  public static void filterDonor(String donorEmail)
  {
    User user = User.findByEmail(donorEmail);
    List<User> users = User.findAll();
  
    List<Donation> donorDonations = getDonors();

    List<Candidate> candidates = Candidate.findAll();
    List<Donation> donations = new ArrayList<>();
      
    for (Donation don : donorDonations)
      {
        if(don.from == user)
        {
          donations.add(don);
        }
      }
    renderTemplate("ReportController/index.html", user, users, donations, candidates);  
    }*/
}