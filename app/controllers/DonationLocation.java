package controllers;

import java.util.Arrays;
import java.util.List;

import models.Admin;
import models.Candidate;
import models.Donation;
import models.User;

import org.json.simple.JSONArray;

import play.Logger;
import play.mvc.Controller;

public class DonationLocation extends Controller
{
  public static void index()
  {  
    Admin admin = Administrator.getCurrentAdmin();
    render(admin);
  }

  public static void donationLocations()
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
          if (!list.toString().contains(user.toString()))
          {
            list.add(Arrays.asList(user.toString(), user.located.getLat(), user.located.getLong()));
          }
        }
      }
    }
    renderJSON(list);
  }
}
