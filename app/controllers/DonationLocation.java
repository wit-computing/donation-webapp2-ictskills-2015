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

  /*
   * Allow donation locations be rendered to map using ajax in
   * donationLocation.js. New JSON ArrayList created to contain info re user
   * (donor) and location. For every user, for every donation, if the donation
   * is from the current user, and the users details are not already contained
   * in the list it will add its values (name, latitude, longitude) to the list.
   * List is rendered
   */
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
            list.add(Arrays.asList(user.toString(), user.located.getLat(),
                user.located.getLong()));
          }
        }
      }
    }
    renderJSON(list);
  }
}
