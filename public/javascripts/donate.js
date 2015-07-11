$('.dropdown')
.dropdown();

$('.checkbox')
.checkbox();

$('.form')
.form({
	amoundDonated : {
		identifier : 'amountDonated',
		rules: [{
			type : 'empty',
			prompt: 'Please select an amount to donate'
		}]
	},
	email : {
		identifier: 'email',
		rules: [{
			type: 'empty',
			prompt: 'Please select a candidate'
		}]
	}

	/*},

{
	onSuccess : function() {
		submitForm();
		return false;
	} */
});

/*function submitForm() {
	  var formData = $('.ui.form.segment input').serialize(); 
	  $.ajax({
	    type: 'POST',
	    url: '/donation/donate',
	    data: formData,
		success: function(response) {            
			console.log("make donation page submitForm response: " + response.progress);
			$('.ui.indicating.progress').progress({
				  percent: response.progress
				});
			$('#progresslabel').text(response.progress + " % of target achieved to date for candidate " + response.candidate);
			
		}
	  });
}*/