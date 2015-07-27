package Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LatLng
{
  public double latitude;
  public double longitude;

  public LatLng(double latitude, double longitude)
  {
    this.latitude = latitude;
    this.longitude = longitude;
  }

  public static LatLng toLatLng(String latlng)
  {
    String[] latLng = latlng.split(",");
    return new LatLng(Double.parseDouble(latLng[0]),
        Double.parseDouble(latLng[1]));
  }

  public static String fromLatLng(double latitude, double longitude)
  {
    return "" + latitude + "," + longitude;
  }

  public double getLatitude()
  {
    return latitude;
  }

  public double getLongitude()
  {
    return longitude;
  }

  public String toString()
  {
    return latitude + "," + longitude;
  }
}
