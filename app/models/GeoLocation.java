package models;

import java.util.*;

import Utils.LatLng;

import javax.persistence.*;

import play.Logger;
import play.db.jpa.Model;

@Entity
public class GeoLocation extends Model
{
  public String latlng;
  public double lat;
  public double lng;

  public GeoLocation(double latitude, double longitude)
  {
    this.latlng = LatLng.fromLatLng(latitude, longitude);
    Logger.info("long "+longitude+ " lat"+latitude);
    this.lat=latitude;
    this.lng=longitude;
  }
  
  public double getLat() {
    LatLng tmp = LatLng.toLatLng(latlng);
    return tmp.latitude;
  }

  public double getLong() {
    LatLng tmp = LatLng.toLatLng(latlng);
    return tmp.longitude;
  }

  @Override
  public String toString()
  {
    return lat + ", " + lng;
  }

}
