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
    String userId = session.get("logged_in_userid");
    User user = User.findById(Long.parseLong(userId));

    if (!firstName.isEmpty())
      user.firstName = firstName;

    if (!lastName.isEmpty())
      user.lastName = lastName;

    if (age != null)
      user.age = age;

    if (!address1.isEmpty())
      user.address1 = address1;

    if (!address2.isEmpty())
      user.address2 = address2;

    if (!city.isEmpty())
      user.city = city;

    if (!state.isEmpty())
      user.state = state;

    if (!zipCode.isEmpty())
      user.zipCode = zipCode;

    user.save();
    DonationController.index();
  }
}