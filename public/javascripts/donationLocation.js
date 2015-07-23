var map; // the google map
var latlng = []; // geolocation data later retrieved from server in func callback
var markers = []; // array of all markers (unfiltered)
var startAllowed; // boolean to enforce start() invocation once only between refreshes
var pos = []; // array of lat, lng representing the polyline start and endpoints created by clicking map
var posIndex = 0; // index variable associate with pos[]

function initialize() {
	rendermap();
	retrieveMarkerLocations();
}

function rendermap() {
	var mapProp = {
			mapTypeId:google.maps.MapTypeId.ROADMAP
	};
	//map = new google.maps.Map(document.getElementById("googleMap"), mapProp); // using vanilla js
	map = new google.maps.Map($("#googleMap")[0], mapProp); // using jQuery
}

/**
 * Use ajax call to get users and their geolocations
 * geoObj[0] is descripion.             
 * geoObj[1] is latitude                              
 * geoObj[2] is longitude  
 */
function retrieveMarkerLocations()
{
	$(function() {
		$.get("/donationLocation/geolocations", function(data) {
			$.each(data, function(index, geoObj) {
				console.log(geoObj[0] + " " + geoObj[1] + " " + geoObj[2]);
			});
			callback(data);
		});
	});
}

/**
 * data into an array, format is 'firstName, xx.xxxx, yy.yyyyy' -> 
 * (firstName, lat, lng) then invoke 'fitBounds' to render the markers, 
 * centre map and create infoWindow to display firstName
 */
function callback(data) {

	latlng = data; // store the array of data in a global for later use
	fitBounds(latlng); // then invoke fitBounds to zoom and display markers
	setInfoWindowListener(latlng);
	populateTable();
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
 * Convert the latlng string to individual numbers
 * and thence to a google.maps.LatLng object
 */
function getLatLng(str)
{ 

	var lat = Number(str[1]);
	var lon = Number(str[2]);
	return new google.maps.LatLng(lat, lon);
}

/** ***************************** filtering markers ************************** */
/**
 * registers click listener to capture lat,lng
 * clicked point data stored in array (pos[])
 */
function start() {
	if (startAllowed == false) {
		alert("Reset to Start");
		return;
	}
	$('#markertable').empty();
	listenerHandler = google.maps.event.addListener(map, 'click', function(e) {
		pos[posIndex] = e.latLng;
		if (posIndex > 0) {
			polyline(posIndex - 1, posIndex);
		}
		posIndex += 1;
	});

}

/**
 * Stop drawing the sequence of polylines 
 * Update listeners Invoke drawPolygon method
 */
function stop() {
	polyline(pos.length - 1, 0); 
	drawPolygon();
	google.maps.event.removeListener(listenerHandler);
	listenerHandler = null;
	startAllowed = false; 
}

/**
 * (re)initialize array of locations falling within poly overlay.
 */
function filter() {
	for (var i = 0; i < latlng.length; i += 1) {
		var point = new google.maps.LatLng(latlng[i][1], latlng[i][2]);
		if (google.maps.geometry.poly.containsLocation(point, polygon)) {
			markers[i].setVisible(true);
			populateTableRow(latlng[i]);
		} else {
			markers[i].setVisible(false);
		}
	}
}

/**
 * Clears table row data Restores table data with complete unfiltered user list
 */
function reset() {
	location.reload();
}

/**
 * create and render a polyline on map attaches beginning to end previous
 * polyline if such exists
 */
function polyline(prevIndex, index) {
	var coords = [
	              new google.maps.LatLng(pos[prevIndex].lat(), pos[prevIndex].lng()),
	              new google.maps.LatLng(pos[index].lat(), pos[index].lng())];

	var line = new google.maps.Polyline({
		path : coords,
		geodesic : true,
		strokeColor : '#FF0000',
		strokeOpacity : 1.0,
		strokeWeight : 2
	});
	line.setMap(map);
}

/**
 * Use data (pos[]) to draw polygon
 */
function drawPolygon() {
	var lineCoords = [];
	// log the coordinates
	// draw polygon defined by coordinates
	for (var j = 0; j < pos.length; j += 1) {
		console.log(pos[j].lat + " " + pos[j].lng);
		lineCoords[j] = new google.maps.LatLng(pos[j].lat(), pos[j].lng());
	}
	// make last point same as first to close the polygon
	lineCoords[pos.length] = new google.maps.LatLng(pos[0].lat(), pos[0].lng());

	polygon = new google.maps.Polyline({
		path : lineCoords,
		geodesic : true,
		strokeColor : '#FF0000',
		strokeOpacity : 1.0,
		strokeWeight : 2
	});

	polygon.setMap(map);
	google.maps.event.clearListeners(map, 'click');
}

/*******************************populating table with marker data*************************/
/**
 * Populates table with complete marker list + it's gps coords
 */
function populateTable()
{
	$.each(latlng, function(i, val) {
		populateTableRow(val);
	});
}

/**
 * renders table row comprising marker and its gps coordinates
 */
function populateTableRow(data)
{
	var description = "<td>" + data[0] + "</td>";
	var gps   = "<td>" + data[1] + " " + data[2] + "</td>";
	$('#markertable').append("<tr>" + description + gps + "</tr>");
}
google.maps.event.addDomListener(window, 'load', initialize);