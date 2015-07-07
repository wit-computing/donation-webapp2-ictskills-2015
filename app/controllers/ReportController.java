package controllers;

import java.util.*;

import models.Candidate;
import models.Donation;
import models.User;
import play.*;
import play.mvc.*;

public class ReportController extends Controller
{
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
      if (don.from.candidate == candidate)
      {
         donations.add(don);
      }
    }
      renderTemplate("ReportController/index.html", user, donations, candidates);  
  } 
}