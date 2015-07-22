
function initialize() {
    var map;
    var marker;
    var latlng = new google.maps.LatLng();

    var mapOptions = {
        zoom : 4,
        center : new google.maps.LatLng(35, -97),
        mapTypeId : google.maps.MapTypeId.ROADMAP
    };
    var mapDiv = document.getElementById('googleMap');
    map = new google.maps.Map(mapDiv,mapOptions);
    mapDiv.style.width = '100%';
    mapDiv.style.height = '400px';
   /* // place a marker
        marker = new google.maps.Marker({
        map : map,
        draggable : true,
        position : latlng,
        title : ""*/
    });
    // To add the marker to the map, call setMap();
    marker.setMap(map); 
    //marker listener populates hidden fields ondragend
    google.maps.event.addListener(marker, 'dragend', function() {
        var latLng = marker.getPosition();
        var latlong = latLng.lat().toString().substring(0,10) + ',' + latLng.lng().toString().substring(0,10);
        //publish lat long in geolocation control in html page
        $("#geolocation").val(latlong);
        //update the new marker position
        map.setCenter(latLng);
      });
}
google.maps.event.addDomListener(window, 'load', initialize);
google.maps.event.addListener(map, 'idle', showMarkers);

/*function initialize() {
    var map;
    var marker;
    var latlng = new google.maps.LatLng(53.347298, -6.268344);

    var mapOptions = {
        zoom : 8,
        center : new google.maps.LatLng(53.347298,-6.268344),
        mapTypeId : google.maps.MapTypeId.ROADMAP
    };
    var mapDiv = document.getElementById('map_canvas');
    map = new google.maps.Map(mapDiv,mapOptions);
    mapDiv.style.width = '500px';
    mapDiv.style.height = '400px';
    // place a marker
        marker = new google.maps.Marker({
        map : map,
        draggable : true,
        position : latlng,
        title : "Drag and drop on your property!"
    });
    // To add the marker to the map, call setMap();
    marker.setMap(map); 
    //marker listener populates hidden fields ondragend
    google.maps.event.addListener(marker, 'dragend', function() {
        var latLng = marker.getPosition();
        var latlong = latLng.lat().toString().substring(0,10) + ',' + latLng.lng().toString().substring(0,10);
        //publish lat long in geolocation control in html page
        $("#geolocation").val(latlong);
        //update the new marker position
        map.setCenter(latLng);
      });
}
google.maps.event.addDomListener(window, 'load', initialize);*/
 
/*function getLocation() {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(showPosition);
    } else {
        x.innerHTML = "Geolocation is not supported by this browser.";
    }
}
function initialize()
{
	
    var center =new google.maps.LatLng(53.347298,-6.268344);
    var initRadius = 10000;
    var mapProp = {
            center:center,
            zoom:7,
            mapTypeId:google.maps.MapTypeId.ROADMAP
    };
    var mapDiv = document.getElementById("map_canvas");
    var map = new google.maps.Map(mapDiv,mapProp);
    mapDiv.style.width = '500px';
    mapDiv.style.height = '400px';
    
    //postion
    var latlon = position.coords.latitude + "," + position.coords.longitude;
    
    
    */
   /* function showPosition(position) {
        x.innerHTML = "Latitude: " + position.coords.latitude +
        "<br>Longitude: " + position.coords.longitude;
    }*/
        /*circle = new google.maps.Circle({
        center:center,
        radius:initRadius,
        strokeColor:"#0000FF",
        strokeOpacity:0.4,
        strokeWeight:1,
        fillColor:"#0000FF",
        fillOpacity:0.4,
        draggable: true
        });
    circle.setEditable(true);//allows varying radius be dragging anchor point
    circle.setMap(map);*/
/*}
google.maps.event.addDomListener(window, 'load', initialize);*/