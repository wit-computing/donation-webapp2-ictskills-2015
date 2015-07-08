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
    
    render(user, donations, candidates);
  }
  
  public static void filterCandidate(String email)
  {
    User user = Accounts.getCurrentUser();
    
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
      renderTemplate("ReportController/index.html", user, donations, candidates);  
  } 
  
}