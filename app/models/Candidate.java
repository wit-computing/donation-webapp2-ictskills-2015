package models;

import java.util.*;

import javax.persistence.*;

import play.db.jpa.*;

@Entity
public class Candidate extends Model
{
  public String firstName;
  public String lastName;

  public String email;
  public String password;

  public long donationTarget;

  @ManyToOne
  public Office running;

  @OneToMany(mappedBy = "to", cascade = CascadeType.ALL)
  List<Donation> donations = new ArrayList<Donation>();

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

  /*
   * Allow office to be added to candidates registration
   */
  public void addOffice(Office running)
  {
    this.running = running;
  }
}