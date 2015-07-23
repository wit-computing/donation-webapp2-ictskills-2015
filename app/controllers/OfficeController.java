package controllers;

import java.util.*;

import models.Admin;
import models.Candidate;
import models.Donation;
import models.Office;
import models.User;
import play.*;
import play.mvc.*;

public class OfficeController extends Controller
{
  @Before
  static void checkAuthentification()
  {
    if(session.contains("logged_in_adminid") == false)
      Administrator.login();
  }
  
  public static void index()
  {
    Admin admin = Administrator.getCurrentAdmin();
    render(admin);
  }
  
  public static void newOffice(Office office)
  {
    office.save();
    Administrator.report();
  }
}