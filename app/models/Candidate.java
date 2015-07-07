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
  
  @OneToMany(mappedBy = "candidate", cascade = CascadeType.ALL)
  public List<User> users;
   
  public Candidate (String firstName, String lastName, String email, String password)
  {
    this.firstName  = firstName;
    this.lastName   = lastName;
    this.email      = email;
    this.password   = password;
  }
  
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
  
  public void addUser(User user)
  {
    users.add(user);
    user.save();
  }
}