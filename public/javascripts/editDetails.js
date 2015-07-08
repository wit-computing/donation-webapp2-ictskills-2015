$('.dropdown')
  .dropdown();

$('.ui.form')
.form({
	'firstName': {
		identifier  : 'firstName',
		rules: [
		        {
		        	type   : 'empty',
		        	prompt : 'Please enter your First Name'
		        }
		        ]
	},
	'lastName': {
		identifier  : 'lastName',
		rules: [
		        {
		        	type   : 'empty',
		        	prompt : 'Please enter your Last Name'
		        }
		        ]
	},
	'address1': {
		identifier  : 'address1',
		rules: [
		        {
		        	type   : 'empty',
		        	prompt : 'Please enter your address'
		        }
		        ]
	},
	'address2': {
		identifier  : 'address2',
		rules: [
		        {
		        	type   : 'empty',
		        	prompt : 'Please enter your address'
		        }
		        ]
	},
	'city': {
		identifier  : 'city',
		rules: [
		        {
		        	type   : 'empty',
		        	prompt : 'Please enter your City'
		        }
		        ]
	},
	'zipCode': {
		identifier  : 'zipCode',
		rules: [
		        {
		        	type   : 'empty',
		        	prompt : 'Please enter your Zip Code'
		        }
		        ]
	},
	'age': {
		identifier  : 'age',
		rules: [
		        {
		        	type   : 'empty',
		        	prompt : 'Please enter your age'
		        }
		        ]
	}
}); 