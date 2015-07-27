$( '.ui.dropdown' ).dropdown();

$( '.ui.form' ).form( {
	candidateEmail : {
		identifier : 'candidateEmail',
		rules : [
			{
				type : 'empty',
				prompt : 'Please select a Candidate to whom you wish to make a donation'
			}
		]
	},

	amountDonated : {
		identifier : 'amountDonated',
		rules : [
			{
				type : 'empty',
				prompt : 'Please select an amount to donate'
			}
		]
	}
},

{
	onSuccess : function () {
		submitForm();
		retrieveMarkerLocations();
		return false;
	}
} );

/*
 * Function to allow form to submit donation, using ajax to remove flicker on reload
 */
function submitForm () {
	var formData = $( '.ui.form input' ).serialize();
	$.ajax( {
		type : 'POST',
		url : '/donation/donate',
		data : formData,
		success : function ( response ) {
			console.log( "make donation page submitForm response: " + response.progress );
			$( '.ui.indicating.progress' ).progress( {
				percent : response.progress
			} );
			$( '#progresslabel' ).text( response.progressLabel );
		}
	} );
}
