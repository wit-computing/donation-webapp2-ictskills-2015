$('.ui.dropdown')
.dropdown();

$('.ui.form')
.form({
	'candidate.firstName': {
		identifier  : 'candidate.firstName',
		rules: [{
			type   : 'empty',
			prompt : 'Please enter your First Name'
		}]
	},
	'candidate.lastName': {
		identifier  : 'candidate.lastName',
		rules: [{
			type   : 'empty',
			prompt : 'Please enter your Last Name'
		}]
	},
	'candidate.email': {
		identifier  : 'candidate.email',
		rules: [{
			type   : 'empty',
			prompt : 'Please enter your email'
		}]
	},
	'candidate.password': {
		identifier : 'candidate.password',
		rules: [{
			type   : 'empty',
			prompt : 'Please enter a password'
		},
		{
			type   : 'length[6]',
			prompt : 'Your password must be at least 6 characters'
		}]
	},
	'title': {
		identifier  : 'title',
		rules: [{
			type   : 'empty',
			prompt : 'Please select an Office'
		}]
	},
	'candidate.donationTarget': {
		identifier: 'candidate.donationTarget',
		rules: [{
			type: 'empty',
			prompt: 'Please select a Donation Target'
		}]
	}
}); 