$('#search-select')
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
	'email': {
		identifier  : 'email',
		rules: [
		        {
		        	type   : 'empty',
		        	prompt : 'Please enter your email'
		        }
		        ]
	},
	'password': {
		identifier : 'password',
		rules: [
		        {
		        	type   : 'empty',
		        	prompt : 'Please enter a password'
		        },
		        {
		        	type   : 'length[6]',
		        	prompt : 'Your password must be at least 6 characters'
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
	},
	'usaCitizen': {
		identifier : 'usaCitizen',
		rules: [
		        {
		        	type   : 'checked',
		        	prompt : 'You must agree to the terms and conditions'
		        }
		        ]
	}
});       