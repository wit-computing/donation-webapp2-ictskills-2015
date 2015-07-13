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
    
    List <String> stateList = getStates();
    
    render(user, users, donations, stateList, candidates);
  }
  
  public static void filterCandidate(String email)
  {
    User user = Accounts.getCurrentUser();
    
    List<User> users = User.findAll();
    List<Donation> allDonations = Donation.findAll();
    List<Candidate> candidates = Candidate.findAll();
    
    List <String> stateList = getStates();
    List<Donation> donations = new ArrayList<>();
    
    Candidate candidate = Candidate.findByEmail(email);
      
    for (Donation don : allDonations)
    {      
      if (don.to == candidate)
      {
        donations.add(don);
      }
    }
    
      renderTemplate("ReportController/index.html", user, users, donations, stateList, candidates);  
  } 
  
  public static void filterDonor(String donorEmail)
  {
    User user = Accounts.getCurrentUser();
    
    List <User> users = User.findAll();
    List<Candidate> candidates = Candidate.findAll();
    List <Donation> allDonations = Donation.findAll();
    
    List<Donation> donations = new ArrayList<Donation>();
    List <String> stateList = getStates();
    
    User donor = User.findByEmail(donorEmail);
    
    for (Donation don : allDonations)
    {
      if (don.from.email == donor.email)
      {
        donations.add(don);
      }
    }
    
    renderTemplate("ReportController/index.html", user, donations, users, stateList, candidates);  
  }

  static List<String> getStates()
  {
    List <User> users = User.findAll();
    
    Set<String> stateSet = new HashSet<String>();
    List<String> stateList = new ArrayList<String>();
    
    for (User user : users)
    {
      stateSet.add(user.state);
    }
    
    stateList.addAll(stateSet);
    Collections.sort(stateList, String.CASE_INSENSITIVE_ORDER);
    
    return stateList;
  }
  
  public static void filterState(String state)
  {
    User user = Accounts.getCurrentUser();
    
    List <User> users = User.findAll();
    List<Candidate> candidates = Candidate.findAll();
    List<Donation> allDonations = Donation.findAll();
    
    List<String> stateList = getStates();
    List<Donation> donations = new ArrayList<>();

    for (Donation don : allDonations)
    {
      if (don.from.state.equals(state))
      {
        donations.add(don);
      }
    }
    
    renderTemplate("ReportController/index.html", user, users, donations, stateList, candidates); 
  }
}