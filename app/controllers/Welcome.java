package controllers;

import models.User;
import play.Logger;
import play.mvc.Controller;

public class Welcome extends Controller
{
  public static void index()
  {
    User user = Accounts.getCurrentUser();
    Logger.info("Landed in Welcome class");
    render(user);
  }
}
