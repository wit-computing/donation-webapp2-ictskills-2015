$('.dropdown')
.dropdown()
;

$('.ui.form')
.form({
	'email': {
		identifier  : 'email',
		rules: [{
			type   : 'empty',
			prompt : 'Please select a Candidate'
		}]
	},
	'donorEmail': {
		identifier  : 'donorEmail',
		rules: [{
			type   : 'empty',
			prompt : 'Please select a Donor'
		}]
	},
	'state': {
		identifier  : 'state',
		rules: [{
			type   : 'empty',
			prompt : 'Please select a State'
		}]
	}
});