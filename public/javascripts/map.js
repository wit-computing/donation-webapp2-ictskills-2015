var map; // the google map
var latlng = []; // geolocation data later retrieved from server in func callback
var markers = []; // array of all markers (unfiltered)

/**
 * Render the basic google map
 */
function initialize() {
  rendermap();
  retrieveMarkerLocations();
}

/**
 * The basic map, no markers, no centre specified
 * Canvas id on html is 'googleMap'
 */
function rendermap() {
  var mapProp = {
    mapTypeId:google.maps.MapTypeId.ROADMAP
    
  };
  //map = new google.maps.Map(document.getElementById("googleMap"), mapProp); // using vanilla js
  map = new google.maps.Map($("#googleMap")[0], mapProp); // using jQuery
}

/**
 * Use ajax call to get users and their geolocations
 * pass returned array marker locations to callback method
 * Here is the format in which marker data stored
 * geoObj[0] is descripion.             
 * geoObj[1] is latitude                              
 * geoObj[2] is longitude  
 * We use geoObj[0] in the infoWindow. Click marker to reveal description.
 */
function retrieveMarkerLocations()
{
  $(function() {
    $.get("/donationController/userLocation", function(data) {
      $.each(data, function(index, geoObj) {
        console.log(geoObj[0] + " " + geoObj[1] + " " + geoObj[2]);
    });
      callback(data);
    });
  });
}

/**
 * we've got the marker location from data in ajax call
 * we now put data into an array
 * the format is 'firstName, xx.xxxx, yy.yyyyy' -> (firstName, lat, lng)
 * then invoke 'fitBounds' to render the markers, centre map and create infoWindow to display firstName
 */
function callback(data)
{

  latlng = data; // store the array of data in a global for later use
  fitBounds(latlng); // then invoke fitBounds to zoom and display markers within view
  setInfoWindowListener(latlng);
}

/**
 * creates and positions markers
 * sets zoom so that all markers visible
 */
function fitBounds(latlngStr)
{
  var bounds = new google.maps.LatLngBounds();
    for (i = 0; i < latlngStr.length; i++) 
    {
        marker = new google.maps.Marker({
            position: getLatLng(latlngStr[i]),
            map: map
        });
        markers[i] = marker;      
        bounds.extend(marker.position);
    }
    map.fitBounds(bounds);
}

function setInfoWindowListener(latlngStr)
{
    var infowindow = new google.maps.InfoWindow();
    for (i = 0; i < latlng.length; i++) 
    {
      /*respond to click on marker by displaying infowindow text*/
      var marker = markers[i];
      google.maps.event.addListener(marker, 'click', (function (marker, i) {
          return function () {
            infowindow.setContent(latlngStr[i][0]);
            infowindow.open(map, marker);
          }
      })(marker, i));
    }
}
/**
 * A helper function to convert the latlng string to individual numbers
 * and thence to a google.maps.LatLng object
 * @param str str is list of strings : username, lat, lon    
 * str[0] is description                
 * str[1] is latitude                              
 * str[2] is longitude                             
 * 
 * @param The object 'str' holding an individual marker data set
 * @return A google.maps.LatLng object containing the marker coordinates.
 */
function getLatLng(str)
{ 

  var lat = Number(str[1]);
  var lon = Number(str[2]);
  return new google.maps.LatLng(lat, lon);
}

google.maps.event.addDomListener(window, 'load', initialize);