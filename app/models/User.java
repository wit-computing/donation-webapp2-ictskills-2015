package models;


import java.util.*;

import javax.persistence.*;

import play.db.jpa.*;

@Entity
public class User extends Model
{
  public boolean usaCitizen;
  public int age;
  public String	firstName;
  public String	lastName;
  public String email;
  public String password;
  public String address1;
  public String address2;
  public String city;
  public String state;
  public String zipCode;

  @OneToMany(mappedBy = "from", cascade = CascadeType.ALL)
  List<Donation> donations = new ArrayList<Donation>();

  public User(String firstName, String lastName, int age, String email, String password, boolean usaCitizen, String address1, String address2, String city, String state, String zipCode)
  {
    this.firstName 	= firstName;
    this.lastName 	= lastName;
    this.age 				= age;
    this.email 			= email;
    this.password 	= password;
    this.usaCitizen = usaCitizen;
    this.address1   = address1;
    this.address2   = address2;
    this.city       = city;
    this.state      = state;
    this.zipCode    = zipCode;
  }
  
  public static User findByEmail(String email)
  {
    return find("email", email).first();
  }

  public boolean checkPassword(String password)
  {
    return this.password.equals(password);
  }
}