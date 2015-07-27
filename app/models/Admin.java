package models;

import java.util.*;

import javax.persistence.*;

import play.db.jpa.*;

@Entity
public class Admin extends Model
{
  public String userName;
  public String password;

  public static Admin findByUserName(String userName)
  {
    return find("userName", userName).first();
  }

  public boolean checkPassword(String password)
  {
    return this.password.equals(password);
  }
}