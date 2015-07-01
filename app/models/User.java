package models;

import java.util.*;

import javax.persistence.*;

import play.db.jpa.*;

@Entity
public class User extends Model
{
  public boolean usaCitizen;
  public String	 firstName;
  public String	 lastName;
  public int		 age;
  public String	 state;
  public String  email;
  public String  password;

  @OneToMany(mappedBy = "from", cascade = CascadeType.ALL)
  List<Donation> donations = new ArrayList<Donation>();

  public User(boolean usaCitizen, String firstName, String lastName, int age, String state, String email, String password)
  {
    this.usaCitizen = usaCitizen;
    this.firstName 	= firstName;
    this.lastName 	= lastName;
    this.age 				= age;
    this.state			= state;
    this.email 			= email;
    this.password 	= password;
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