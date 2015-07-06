package models;


import java.util.*;

import javax.persistence.*;

import play.db.jpa.*;

@Entity
public class Candidate extends Model
{
  public String	firstName;
  public String	lastName;
 
  public String email;
  public String password;
  
  @ManyToOne
  public User user;
  
  @ManyToOne
  public Donation donation;
  
  public static Candidate findByEmail(String email)
  {
    return find("email", email).first();
  }

  public boolean checkPassword(String password)
  {
    return this.password.equals(password);
  }
  
  public String toString()
  {
    return firstName + " " + lastName;
  }
}