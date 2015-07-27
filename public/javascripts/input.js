var geocoder;

function initialize () {
	geocoder = new google.maps.Geocoder();
}

function codeAddress () {
	var address = document.getElementById( "zipCode" ).value;
	geocoder.geocode( {
		'address' : address
	}, function ( results, status ) {
		if ( status == google.maps.GeocoderStatus.OK ) {
			var LatLng = [];
			$( '#latitude' ).val( results[ 0 ].geometry.location.lat() );
			$( '#longitude' ).val( results[ 0 ].geometry.location.lng() );
		} else {
			alert( "Geocode was not successful for the following reason: " + status );
		}
	} );
}
google.maps.event.addDomListener( window, 'load', initialize );