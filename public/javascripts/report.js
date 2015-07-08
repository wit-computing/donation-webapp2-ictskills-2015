$('.dropdown')
.dropdown()
;

$('.ui.form')
.form({
	'email': {
		identifier  : 'email',
		rules: [
		        {
		        	type   : 'empty',
		        	prompt : 'Please select a Candidate'
		        }
		        ]
	}
});