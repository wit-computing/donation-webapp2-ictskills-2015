package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

@Entity
public class Office extends Model
{
  public String title;
  public String description;
  
  @OneToMany(mappedBy = "running", cascade = CascadeType.ALL)
  List<Candidate> candidates = new ArrayList<Candidate>();
  
  public static Office findByTitle(String title)
  {
    return find("title", title).first();
  }
  
  public String toString()
  {
    return title;
  }
  
  public void addCandidate(Candidate candidate)
  {
    candidates.add(candidate);
    candidate.save();
  } 
}
