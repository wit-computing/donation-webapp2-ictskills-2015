package controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import models.*;
import play.*;
import play.mvc.*;

public class Accounts extends Controller
{
  public static void index()
  {
    render();
  }

  public static void signup()
  {
    List<Candidate> candidates = Candidate.findAll();
    render(candidates);
  }

  public static void register(User user)
  {
   
    user.save();
    login();
  }

  public static void login()
  {
    render();
  }

  public static void logout()
  {
    setSessionLogout();
    Welcome.index();
  }

  protected static void setSessionLogout()
  {
    if (session.get("logged_status") != null && session.get("logged_status").equals("logged_in"))
    {
      session.clear();
      session.put("logged_status", "logged_out");
    }
  }

  public static void authenticate(String email, String password)
  {
    Logger.info("Attempting to authenticate with " + email + ":" + password);
    User user = User.findByEmail(email);

    if ((user != null) && (user.checkPassword(password) == true))
    {
      Logger.info("Successfull authentication of " + user.firstName + " " + user.lastName);
      session.put("logged_in_userid", user.id);
      session.put("logged_status", "logged_in");
      DonationController.index();
    }
    else
    {
      Logger.info("Authentication failed");
      login();
    }
  }

  public static User getCurrentUser()
  {
    User user = null;
    if (session.contains("logged_in_userid"))
    {
      String userId = session.get("logged_in_userid");
      user = User.findById(Long.parseLong(userId));
    }
    return user;
  }
}