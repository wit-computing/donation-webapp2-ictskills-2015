package controllers;

import java.util.*;

import models.Admin;
import models.Candidate;
import models.Donation;
import models.Office;
import models.User;
import play.*;
import play.mvc.*;

public class CandidateController extends Controller
{
  @Before
  static void checkAuthentification()
  {
    if (session.contains("logged_in_adminid") == false)
      Administrator.login();
  }

  /*
   * Render new candidate page
   */
  public static void index()
  {
    Admin admin = Administrator.getCurrentAdmin();
    List<Office> offices = Office.findAll();
    render(admin, offices);
  }

  /*
   * Save new candidate and relevant office
   */
  public static void newCandidate(Candidate candidate, String title)
  {
    Office running = Office.findByTitle(title);
    candidate.addOffice(running);
    candidate.save();
    Administrator.report();
  }
}