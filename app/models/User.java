package models;


import java.util.*;

import javax.persistence.*;

import play.db.jpa.*;

@Entity
public class User extends Model
{
  public boolean usaCitizen;
  public Integer age;
 
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
  public List<Donation> donations = new ArrayList<Donation>();

  @ManyToOne
  public Candidate candidate;
  
  public static User findByEmail(String email)
  {
    return find("email", email).first();
  }

  public boolean checkPassword(String password)
  {
    return this.password.equals(password);
  }
  
  public void addCandidate(Candidate candidate)
  {
    this.candidate = candidate;
  } 
}