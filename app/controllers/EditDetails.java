package controllers;

import java.util.Date;

import models.GeoLocation;
import models.User;
import play.*;
import play.mvc.*;

public class EditDetails extends Controller
{
  @Before
  static void checkAuthentification()
  {
    if(session.contains("logged_in_userid") == false)
      Accounts.login();
  }
  
  public static void index()
  {
    User user = Accounts.getCurrentUser();
    render(user);
  }

  public static void changeDetails(String firstName, String lastName, Integer age, String address1, String address2, 
      String city, String state, String zipCode, double latitude, double longitude)
  {
    User user = Accounts.getCurrentUser();

    user.firstName = firstName;
    user.lastName = lastName;
    user.age = age;
    user.address1 = address1;
    user.address2 = address2;
    user.city = city;
    
    if (!state.isEmpty())
    user.state = state;
    
    user.zipCode = zipCode;
    
    user.located.lat = latitude;
    user.located.lng = longitude;    
    
    user.located.save();
    
    user.save();
    DonationController.index();
  }
}