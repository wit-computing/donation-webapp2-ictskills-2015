package controllers;

import java.util.Date;

import models.User;
import play.*;
import play.mvc.*;

public class EditDetails extends Controller
{
  public static void index()
  {
    User user = Accounts.getCurrentUser();
    render(user);
  }

  public static void changeDetails(String firstName, String lastName, Integer age, String address1, String address2, 
      String city, String state, String zipCode)
  {
    User user = Accounts.getCurrentUser();

    user.firstName = firstName;
    user.lastName = lastName;
    user.age = age;
    user.address1 = address1;
    user.address2 = address2;
    user.city = city;
    user.state = state;
    user.zipCode = zipCode;

    user.save();
    DonationController.index();
  }
}