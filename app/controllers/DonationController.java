package controllers;

import models.Donation;
import models.User;
import play.*;
import play.mvc.*;

public class DonationController extends Controller
{
  public static void index()
  {
    User user = Accounts.getCurrentUser();
    if (user == null)
    {
      Logger.info("Donation class : Unable to getCurrentuser");
      Accounts.login();
    }
    else
    {
      Logger.info("Donation ctrler : user is " + user.email);
      render(user);
    }
  }
  
  public static void donate(long amountDonated, String methodDonated)
  {
    User user = Accounts.getCurrentUser();
    if (user == null)
    {
      Logger.info("Donation class : Unable to getCurrentuser");
      Accounts.login();
    }
    else
    {
      Logger.info("amount donated " + amountDonated + " " + "method donated " + methodDonated);
      addDonation(user, amountDonated, methodDonated);
    }
    index();
  }
  
  private static void addDonation(User user, long amountDonated, String methodDonated)
  {
    Donation bal = new Donation(user, amountDonated, methodDonated);
    bal.save();
  }
}