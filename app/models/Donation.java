package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import play.db.jpa.*;

@Entity
public class Donation extends Model
{
  public long received;
  public String methodDonated;

  @ManyToOne
  public User from;
  
  @ManyToOne
  public Candidate to;
  
  public Donation(User from, Candidate to, long received, String methodDonated)
  {
    this.received 			= received;
    this.methodDonated 	= methodDonated;
    this.from 					= from;
    this.to             = to;
  }
}