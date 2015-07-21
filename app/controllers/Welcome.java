package controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
