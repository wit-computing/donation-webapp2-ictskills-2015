package controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import models.*;
import play.*;
import play.mvc.*;

public class Administrator extends Controller
{  
  public static void index()
  {
    render();
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
  
  public static void authenticate(String userName, String password)
  {
    Admin admin = Admin.findByUserName(userName);
    
    if ((admin != null) && (admin.checkPassword(password) == true))
    {
      session.put("logged_in_adminid", admin.id);
      session.put("logged_status", "logged_in");
      Logger.info("Admin Logged in successfully");
      report();
    }
    else
    {
      Logger.info("Authentication failed");
      login();
    }
  }
  
  public static Admin getCurrentAdmin()
  {
    Admin admin = null;
    if (session.contains("logged_in_adminid"))
    {
      String adminId = session.get("logged_in_adminid");
      admin = Admin.findById(Long.parseLong(adminId));
    }
    return admin;
  }
  
  public static void report()
  {
    Admin admin = getCurrentAdmin();
    List<User> users = User.findAll();
    List<Candidate> candidates = Candidate.findAll();
    render(admin, users, candidates);
  }
}